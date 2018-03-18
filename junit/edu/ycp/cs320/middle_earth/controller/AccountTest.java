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
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testCreate_AccountUsernameTaken(){
		account.create_account("Tadukoo", "Imma_steal_his_acc_;)", "theREALtadukoo@something.com");
		
		// TODO: JUNIT: Test that it fails somehow? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testCreate_AccountEmailTaken(){
		account.create_account("Totally New", "yeah_Im_new", "realtadukoo@gmail.com");
		
		// TODO: JUNIT: Test that it fails somehow? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testLogin(){
		account.login("Tadukoo", "tadukoopassword");
		
		// TODO: JUNIT: Test that login passes (assuming above info is in database)
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testLoginUsernameDoesntExist(){
		account.login("Derp Not Here", "anything_really");
		
		// TODO: JUNIT: Test that login fails (username not in database)? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
	
	@Test
	public void testLoginIncorrectPassword(){
		account.login("Tadukoo", "not_my_password");
		
		// TODO: JUNIT: Test that login fails (password incorrect)? Perhaps an errorMessage in Account?
		throw new UnsupportedOperationException("Not sure how to test this yet... Doesn't matter though it's not implemented");
	}
}
