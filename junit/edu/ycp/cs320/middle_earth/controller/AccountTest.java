package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest{
	private Account account;
	
	@Before
	public void setup(){
		account = new Account();
	}
	
	@Test
	public void testSet_User_Token(){
		account.set_user_token("39r3jff43r9");
		
		assertEquals("39r3jff43r9", account.get_user_token());
	}
	
	@Test
	public void testReset_User_Token(){
		account.set_user_token("39r3jff43r9");
		
		assertEquals("39r3jff43r9", account.get_user_token());
		
		account.set_user_token("dfi4f94jf");
		
		assertEquals("dfi4f94jf", account.get_user_token());
	}
	
	@Test
	public void testCreate_Account(){
		account.create_account("New User", "New Password", "new_user@example.com");
		
		// TODO: JUNIT: Test that information is put into database (regardless of fake/real)
		// TODO: JUNIT: Also test for issues (e.g. username is taken, email is taken, etc.)
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testLogin(){
		account.login("Tadukoo", "tadukoopassword");
		
		// TODO: JUNIT: Test that login passes (assuming above info is in database)
		// TODO: JUNIT: Also test for issues (e.g. username doesn't exist, password is wrong, etc.)
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
}
