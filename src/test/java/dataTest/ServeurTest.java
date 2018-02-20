package dataTest;

import static org.junit.Assert.*;

import java.net.ServerSocket;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import data.Serveur;
import exception.ClosedServerException;
import exception.PortLesserThan1023Exception;
import factory.ContextClientFactory;
import misc.globalData;

public class ServeurTest {

	private Serveur serveur;
	private ServerSocket mockSocket;
	private ContextClientFactory mockFac;
	
	@Before
	public void setUp() {
		mockSocket = mock(ServerSocket.class, "mockSocket");
		mockFac = mock(ContextClientFactory.class, "mockFac");
	}
	
	@Test
	public void test_initialisation_without_given_port_or_serverSocket() throws Exception {
		this.serveur = new Serveur();
		assertEquals(globalData.used_port, this.serveur.getPort());
		this.serveur.closeServer();
	}

	@Test
	public void test_initialisation_without_given_port_but_with_serverSocket() throws Exception {
		this.serveur = new Serveur(mockSocket);
		assertEquals(globalData.used_port, this.serveur.getPort());
		this.serveur.closeServer();
	}
	
	@Test
	public void test_initialisation_with_port_greater_than_1023() throws Exception {
		this.serveur = new Serveur(5000);
		assertEquals(5000, serveur.getPort());
		this.serveur.closeServer();
	}
	
	@Test(expected = PortLesserThan1023Exception.class)
	public void test_initialisation_with_port_lesser_than_1023() throws Exception {
		this.serveur = new Serveur(1000);
	}

	@Test(expected = ClosedServerException.class)
	public void test_process_server_closed() throws Exception {
		when(mockSocket.isClosed()).thenReturn(true);
		this.serveur = new Serveur(mockSocket);
		
		this.serveur.process(mockFac);
	}
	
	@Test
	public void test_process_sever_open() throws Exception {
		when(mockSocket.isClosed()).thenReturn(false);
		this.serveur = new Serveur(mockSocket);
		
		this.serveur.process(mockFac);
		
		verify(mockSocket).accept();
		
		this.serveur.closeServer();
	}


}
