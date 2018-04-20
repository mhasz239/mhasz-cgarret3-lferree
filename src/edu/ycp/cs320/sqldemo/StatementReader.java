package edu.ycp.cs320.sqldemo;

import java.io.IOException;
import java.io.Reader;

/**
 * Read the text of SQL statements from a Reader.
 * Each SQL statement must be terminated by a semicolon.
 */
public class StatementReader {
	private Reader reader;

	/**
	 * Constructor.
	 * 
	 * @param reader the Reader to read SQL statements from.
	 */
	public StatementReader(Reader reader) {
		this.reader = reader;
	}
	
	private enum Mode { NORMAL, SINGLE_QUOTED_STRING, DOUBLE_QUOTED_STRING };
	
	/**
	 * Reader the next statement.
	 * 
	 * @return a String containing the next statement, or null if there are
	 *         no more statements to read
	 * @throws IOException
	 */
	public String nextStatement() throws IOException {
		StringBuilder buf = new StringBuilder();
		
		Mode mode = Mode.NORMAL;
		
		boolean done = false;
		while (!done) {
			int c = reader.read();
			
			if (c < 0) {
				break;
			}
			
			char cc = (char) c;
			buf.append(cc);
			
			switch (mode) {
			case NORMAL:
				if (cc == ';') {
					buf.deleteCharAt(buf.length() - 1); // get rid of the semicolon
					done = true;
				} else if (cc == '\'') {
					mode = Mode.SINGLE_QUOTED_STRING;
				} else if (cc == '"') {
					mode = Mode.DOUBLE_QUOTED_STRING;
				}
				break;
				
			case SINGLE_QUOTED_STRING:
				if (cc == '\'') {
					mode = Mode.NORMAL;
				}
				break;
				
			case DOUBLE_QUOTED_STRING:
				if (cc == '"') {
					mode = Mode.NORMAL;
				}
				break;
			}
		}
		
		if (buf.length() == 0) {
			return null;
		}
		
		return buf.toString();
	}
}
