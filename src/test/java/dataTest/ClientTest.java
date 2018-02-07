package dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Client;

public class ClientTest {

	
	@Test
	public void test_Client_initialisation() {
		Client c = new Client();
		assertEquals("", c.getLogin());
		assertEquals("", c.getPass());
	}
	
	@Test
	public void test_Client_set_Login_and_password() {
		Client c = new Client();
		c.setLogin("MyLogin");
		c.setPass("MyPassword");
		assertEquals("MyLogin", c.getLogin());
		assertEquals("MyPassword", c.getPass());
	}
}
