package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest{
	private Account account;
	
	@Before
	public void setup(){
		account = new Account();
		account.set_user_token(null);
		account.set_game_id(-1);
	}
	
	@Test
	public void testSet_User_Token(){
		// Set that token!
		account.set_user_token("39r3jff43r9");
		
		// Check that token!
		assertEquals("39r3jff43r9", account.get_user_token());
		
		// Set it again! (Checking against weird add stuff)
		account.set_user_token("dfi4f94jf");
		
		// Check it again!
		assertEquals("dfi4f94jf", account.get_user_token());
	}
	
	@Test
	public void testSet_Game_ID(){
		// Set that ID!
		account.set_game_id(1029);
		
		// Check that ID!
		assertEquals(1029, account.get_game_id());
		
		// Set it again! (Checking against weird add stuff)
		account.set_game_id(2938);
		
		// Check it again!
		assertEquals(2938, account.get_game_id());
	}
	
	@Test
	public void testCreate_Account(){
		account.create_account("New User", "New Password", "new_user@example.com");
		
		// TODO: JUNIT: Test that information is put into database (regardless of fake/real)
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testCreate_AccountUsernameTaken(){
		account.create_account("lferree", "Imma_steal_his_acc_;)", "theREALtadukoo@something.com");
		
		// TODO: JUNIT: Test that it fails somehow? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testCreate_AccountEmailTaken(){
		account.create_account("Totally New", "yeah_Im_new", "lferree@ycp.edu");
		
		// TODO: JUNIT: Test that it fails somehow? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testLogin(){
		// Run the login and get response
		String response = account.login("lferree", "password");
		
		// Check that response is accurate
		assertEquals("Success!", response);
		
		// TODO: JUNIT: Test that user_token and game_id are set
		//assertEquals(3, account.get_user_token());
		//assertEquals(1, account.get_game_id());
	}
	
	@Test
	public void testLoginUsernameDoesntExist(){
		// Run the login and get response
		String response = account.login("Derp Not Here", "anything_really");
		
		// Check that response is accurate
		assertEquals("Invalid Username or Password", response);
		
		// Check that user_token and game_id aren't set
		assertEquals(null, account.get_user_token());
		assertEquals(-1, account.get_game_id());
	}
	
	@Test
	public void testLoginIncorrectPassword(){
		// Run the login and get response
		String response = account.login("lferree", "not_my_password");
		
		// Check that response is accurate
		assertEquals("Invalid Username or Password", response);
		
		// Check that user_token and game_id aren't set
		assertEquals(null, account.get_user_token());
		assertEquals(-1, account.get_game_id());
	}
}
