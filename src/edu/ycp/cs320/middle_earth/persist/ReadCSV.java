package edu.ycp.cs320.middle_earth.persist;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadCSV implements Closeable {
	private BufferedReader reader;
	
	public ReadCSV(String resourceName) throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("edu/ycp/cs320/middle_earth/persist/res/" + resourceName);
		if (in == null) {
			throw new IOException("Couldn't open " + resourceName);
		}
		this.reader = new BufferedReader(new InputStreamReader(in));
	}
	
	public List<String> next() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		List<String> tuple = new ArrayList<String>();
		StringTokenizer tok = new StringTokenizer(line, "|");
		while (tok.hasMoreTokens()) {
			tuple.add(tok.nextToken().trim());
		}
		return tuple;
	}
	
	@Override
	public void close() throws IOException {
		reader.close();
	}
}
