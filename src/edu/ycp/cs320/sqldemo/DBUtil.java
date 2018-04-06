package edu.ycp.cs320.sqldemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Some database utility functions.
 */
public abstract class DBUtil {
	/**
	 * Attempt to close a Statement.
	 * 
	 * @param stmt the Statement to close
	 */
	public static void closeQuietly(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	/**
	 * Attempt to close a ResultSet.
	 * 
	 * @param resultSet the ResultSet to close
	 */
	public static void closeQuietly(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	/**
	 * Attempt to close a Connection.
	 * 
	 * @param conn the Connection to close
	 */
	public static void closeQuietly(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
}
