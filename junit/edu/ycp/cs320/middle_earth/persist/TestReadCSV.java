package edu.ycp.cs320.middle_earth.persist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestReadCSV{
	private ReadCSV read;
	
	public void doConstructor(){
		try{
			read = new ReadCSV("test_read.csv");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void doClose(){
		try{
			read.close();;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConstructorDoesntFail(){
		try{
			read = new ReadCSV("test_read.csv");
			
			read.close();
		}catch(IOException e){
			assertEquals(0, 1);
		}
	}
	
	@Test
	public void testNextOnFirstLine(){
		doConstructor();
		
		try{
			ArrayList<String> actual = new ArrayList<String>(read.next());
			assertEquals(7, actual.size());
			assertEquals("1", actual.get(0));
			assertEquals("Test text", actual.get(1));
			assertEquals("blah blah", actual.get(2));
			assertEquals("idk what I'm doing", actual.get(3));
			assertEquals("just that it's something", actual.get(4));
			assertEquals("29384", actual.get(5));
			assertEquals("derp", actual.get(6));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		doClose();
	}
	
	@Test
	public void testNextOnSecondLine(){
		doConstructor();
		
		try{
			// Skip first line
			read.next();
			
			ArrayList<String> actual = new ArrayList<String>(read.next());
			
			assertEquals(12, actual.size());
			assertEquals("59", actual.get(0));
			assertEquals("eonfr9", actual.get(1));
			assertEquals("23jf", actual.get(2));
			assertEquals("of2jf943jf43", actual.get(3));
			assertEquals("this is garbage", actual.get(4));
			assertEquals("just a long line too", actual.get(5));
			assertEquals("whatever this is all", actual.get(6));
			assertEquals("2948", actual.get(7));
			assertEquals("3920", actual.get(8));
			assertEquals("203", actual.get(9));
			assertEquals("20193", actual.get(10));
			assertEquals("29384", actual.get(11));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		doClose();
	}
	
	@Test
	public void testNextOnNonExistentThirdLine(){
		doConstructor();
		
		try{
			// Skip first 2 lines (only actual lines)
			read.next();
			read.next();
			
			List<String> actual = read.next();
			
			assertEquals(null, actual);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
