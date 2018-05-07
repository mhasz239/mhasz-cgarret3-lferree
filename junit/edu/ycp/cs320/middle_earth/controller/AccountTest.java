package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest{
	private Account account;
	
	@Before
	public void setup(){
		account = new Account();
		account.setuser_token(-1);
		account.setgame_ids(null);
	}
	
	@Test
	public void testsetUser_Token(){
		// Set that token!
		account.setuser_token(507);
		
		// Check that token!
		assertEquals(507, account.getuser_token());
		
		// Set it again! (Checking against weird add stuff)
		account.setuser_token(42);
		
		// Check it again!
		assertEquals(42, account.getuser_token());
	}
	
	@Test
	public void testsetGame_ID(){
		// Set that ID!
		account.setgame_ids(new int[]{1029});
		
		// Check that ID!
		assertEquals(1, account.getgame_ids().length);
		assertEquals(1029, account.getgame_ids()[0]);
		
		// Set it again! (Checking against weird add stuff)
		account.setgame_ids(new int[]{2938, 5});
		
		// Check it again!
		assertEquals(2, account.getgame_ids().length);
		assertEquals(2938, account.getgame_ids()[0]);
		assertEquals(5, account.getgame_ids()[1]);
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
		//assertEquals(3, account.getuser_token());
		//assertEquals(1, account.getgame_id());
	}
	
	@Test
	public void testLoginUsernameDoesntExist(){
		// Run the login and get response
		String response = account.login("Derp Not Here", "anything_really");
		
		// Check that response is accurate
		assertEquals("Invalid Username or Password", response);
		
		// Check that user_token and game_id aren't set
		assertEquals(-1, account.getuser_token());
		assertEquals(null, account.getgame_ids());
	}
	
	@Test
	public void testLoginIncorrectPassword(){
		// Run the login and get response
		String response = account.login("lferree", "not_my_password");
		
		// Check that response is accurate
		assertEquals("Invalid Username or Password", response);
		
		// Check that user_token and game_id aren't set
		assertEquals(-1, account.getuser_token());
		assertEquals(null, account.getgame_ids());
	}
}
