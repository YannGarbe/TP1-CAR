package dataTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Serveur;
import exception.PortLesserThan1023Exception;
import misc.Constantes;

public class ServeurTest {

	private Serveur serveur;
	
	@Before 
	public void setUp() throws Exception {
		this.serveur = new Serveur();
		
	}

	@After
	public void closeServer() throws IOException {
		serveur.closeServer();
	}
	
	@Test
	public void test_initialisation_without_given_port() {
		assertEquals(Constantes.DEFAULT_PORT, this.serveur.getPort());
	}

	@Test
	public void test_initialisation_with_port_greater_than_1023() throws Exception {
		Serveur s = new Serveur(5000);
		assertEquals(5000, s.getPort());
	}
	
	@Test(expected = PortLesserThan1023Exception.class)
	public void test_initialisation_with_port_lesser_than_1023() throws Exception {
		new Serveur(1000);
	}

	/*@Test
	public void test_process_server_closed() throws IOException {
		
		serveur.closeServer();
		serveur.connection();
		assertTrue(true);
		
	}*/
	/*@Test
	public void test_receive_successfull() throws IOException {
		this.serveur.process();
		assertTrue(serveur.isConnected());
	}

	@Test
	public void test_receive_failed() {
		assertTrue(serveur.isConnected());
	}*/

}
