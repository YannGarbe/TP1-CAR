package readTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exception.NonExistentFileException;
import misc.globalData;
import read.ReadFileAuthentification;


public class ReadFileAuthentificationTest {

	private ReadFileAuthentification ra;
	
	@Before
	public void setUp() throws NonExistentFileException {
		this.ra = new ReadFileAuthentification(globalData.DEFAULT_LOGIN_PATH);
	}
	
	@Test
	public void test_authentification_when_initialisation_successfull(){
		assertEquals(globalData.DEFAULT_LOGIN_PATH, ra.getPath());
	}

	@Test(expected=NonExistentFileException.class)
	public void test_authentification_when_initialisation_failed_because_of_wrong_filename_path() throws NonExistentFileException {
		new ReadFileAuthentification("zdadzad");
	}
	
	@Test
	public void test_containsLogin_when_login_is_contained_in_file() throws IOException {
		assertTrue(ra.containsLogin("admin") != -1);
	}
	
	@Test
	public void test_containsLogin_when_login_is_not_contained_in_file() throws IOException {
		assertFalse(ra.containsLogin("LoginInexistant") != -1);
	}
	
	@Test
	public void test_containsPassWord_when_passWord_is_contained_in_file() throws IOException {
		assertTrue(ra.containsPass("admin")  != -1);
	}
	
	@Test
	public void test_containsPassWord_when_passWord_is_not_contained_in_file() throws IOException {
		assertFalse(ra.containsPass("MotDePasseInexistant")  != -1);
	}
	
	@Test
	public void test_containsUser_when_login_and_passWord_are_contained_in_file() throws IOException {
		assertTrue(ra.containsUser("admin", "admin"));
	}
	
	@Test
	public void test_containsUser_when_login_and_passWord_are_not_contained_in_file() throws IOException {
		assertFalse(ra.containsUser("LoginInexistant", "MotDePasseInexistant"));
	}
	
	@Test
	public void test_containsUser_when_login_is_not_contained_in_file() throws IOException {
		assertFalse(ra.containsUser("admin", "MotDePasseInexistant"));
	}
}
