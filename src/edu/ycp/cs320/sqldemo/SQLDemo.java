package edu.ycp.cs320.sqldemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interactive query tool for SQLite.
 */
public class SQLDemo {
	static class RowList extends ArrayList<List<String>> {
		private static final long serialVersionUID = 1L;
	}
	
	private static final String PAD =
		"                                                    " +
		"                                                    " +
		"                                                    " +
		"                                                    ";
	private static final String SEP =
		"----------------------------------------------------" +
		"----------------------------------------------------" +
		"----------------------------------------------------" +
		"----------------------------------------------------";

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
			conn.setAutoCommit(true);
	
			queryLoop(conn);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			DBUtil.closeQuietly(conn);
		}
}

	private static void queryLoop(Connection conn) throws IOException {
		StatementReader stmtReader = new StatementReader(new InputStreamReader(System.in));

		boolean done = false;
		while (!done) {
			System.out.print("SQL> ");
			String sql = stmtReader.nextStatement(); // keyboard.nextLine();

			if (sql == null) {
				done = true;
			} else {
				sql = sql.trim();
				if (sql.startsWith("quit")) {
					done = true;
				} else if (sql.startsWith("import ")) {
					String spec = sql.substring("import ".length()).trim();
					StringTokenizer tok = new StringTokenizer(spec, " \t");
					String tableName = tok.nextToken();
					String csvFile = tok.nextToken();
					if (csvFile.endsWith(";")) {
						csvFile = csvFile.substring(0, csvFile.length() - 1);
						csvFile = csvFile.trim();
					}
					try {
						importCSV(conn, tableName, csvFile);
					} catch (IOException e) {
						System.out.println("Error: " + e.getMessage());
					} catch (SQLException e) {
						System.out.println("Error: " + e.getMessage());
					}
				} else {
					try {
//						System.out.println("Executing SQL:");
//						System.out.println(sql);
						executeSQL(conn, sql);
					} catch (SQLException e) {
						System.out.println("Error: " + e.getMessage());
					}
				}
			}
		}
	}

	private static void executeSQL(Connection conn, String sql) throws SQLException {
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.createStatement();

			int rowCount = 0;
			if (stmt.execute(sql)) {
				resultSet = stmt.getResultSet();
				ResultSetMetaData schema = resultSet.getMetaData();

				List<String> colNames = new ArrayList<String>();
				for (int i = 1; i <= schema.getColumnCount(); i++) {
					colNames.add(schema.getColumnName(i));
				}

				RowList rowList = getRows(resultSet, schema.getColumnCount());
				rowCount = rowList.size();

				List<Integer> colWidths = getColumnWidths(colNames, rowList);

				printRow(colNames, colWidths);
				printSeparator(colWidths);
				for (List<String> row : rowList) {
					printRow(row, colWidths);
				}
			}
			System.out.println("OK (" + rowCount + " rows(s))");
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
	}

	private static void printRow(List<String> row, List<Integer> colWidths) {
		for (int i = 0; i < row.size(); i++) {
			if (i > 0) {
				System.out.print(" ");
			}
			String item = row.get(i);
			System.out.print(PAD.substring(0, colWidths.get(i) - item.length()));
			System.out.print(item);
		}
		System.out.println();
	}

	private static void printSeparator(List<Integer> colWidths) {
		List<String> sepRow = new ArrayList<String>();
		for (Integer w : colWidths) {
			sepRow.add(SEP.substring(0, w));
		}
		printRow(sepRow, colWidths);
	}

	private static RowList getRows(ResultSet resultSet, int numColumns) throws SQLException {
		RowList rowList = new RowList();
		while (resultSet.next()) {
			List<String> row = new ArrayList<String>();
			for (int i = 1; i <= numColumns; i++) {
				row.add(resultSet.getObject(i).toString());
			}
			rowList.add(row);
		}
		return rowList;
	}

	private static List<Integer> getColumnWidths(List<String> colNames, RowList rowList) {
		List<Integer> colWidths = new ArrayList<Integer>();
		for (String colName : colNames) {
			colWidths.add(colName.length());
		}
		for (List<String> row: rowList) {
			for (int i = 0; i < row.size(); i++) {
				colWidths.set(i, Math.max(colWidths.get(i), row.get(i).length()));
			}
		}
		return colWidths;
	}
	
	private static final Pattern INTEGER = Pattern.compile("\\d+");

	private static void importCSV(Connection conn, String tableName, String csvFile) throws IOException, SQLException {
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		try {
			readCSV(conn, tableName, reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * @param conn
	 * @param tableName
	 * @param reader
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void readCSV(Connection conn, String tableName,
			BufferedReader reader) throws IOException, SQLException {
		PreparedStatement stmt = null;
		
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			
			line = line.trim();
			if (line.equals("")) {
				continue;
			}
			
			List<String> row = new ArrayList<String>();
			// DJH2-3-12-17: changed tokenizer to '|' so that comma's can be embedded in titles
			StringTokenizer tok = new StringTokenizer(line, "|");
			while (tok.hasMoreTokens()) {
				row.add(tok.nextToken());
			}
			if (stmt == null) {
				StringBuilder buf = new StringBuilder();
// DJH2: Added the code below to specifically allow importCSV to use auto-increment primary keys for books and authors in Derby
// DJH2: Removed the primary key columns from books.csv and authors.csv, since they will now be auto-generated				
// DJH2: This is definitely a kludge to get importCSV to work with Derby for Lab04
// DJH2: What really needs to be done is that the column names should be read from the CSV file,
// DJH2: and then substituted into the 'insert' statement
// DJH2-3-12-17: Added 'published' as an attribute of books table
// DJH2-3-12-17: changed attributes to 'lastname' and 'firstname' for authors table
				System.out.println("Importing data for table: <" + tableName + ">");
				if (tableName.toLowerCase().equals("books"))
				{
					buf.append("insert into " + tableName + " (author_id, title, isbn, published) values (");
				}
				else if (tableName.toLowerCase().equals("authors"))
				{
					buf.append("insert into " + tableName + " (lastname, firstname) values (");
				}
// DJH2: this is the original code - it will not import into a table with an auto-incrementing primary key
// DJH2: The primary key values must be manually determined and included in the CSV file.
				else
				{
					buf.append("insert into " + tableName + " values (");					
				}
				for (int i = 0; i < row.size(); i++) {
					if (i > 0) {
						buf.append(", ");
					}
					buf.append("?");
				}
				buf.append(")");
				stmt = conn.prepareStatement(buf.toString());
			}
			for (int i = 0; i < row.size(); i++) {
				String value = row.get(i);
				Matcher m = INTEGER.matcher(value);
				if (m.matches()) {
					stmt.setInt(i+1, Integer.parseInt(value));
				} else {
					stmt.setString(i+1, row.get(i));
				}
			}
			stmt.addBatch();
		}
		
		conn.setAutoCommit(false);
		stmt.executeBatch();
		conn.setAutoCommit(true);
		
		System.out.println("Successful import");
	}
}
