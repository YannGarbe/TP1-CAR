package threadTest;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import request.FtpRequest;

import static org.mockito.Mockito.*;
import thread.ContextClient;

public class ContextClientTest {

	private Socket mockSocketClient;
	private ServerSocket mockSocketServer;
	private BufferedReader mockBuffer;
	private FtpRequest mockRequest;
	private ContextClient cc;
	
	@Before
	public void setUp() {
		this.mockSocketClient = mock(Socket.class, "mockSocketClient");
		this.mockSocketServer = mock(ServerSocket.class, "mockSocketServer");
		this.mockBuffer = mock(BufferedReader.class, "mockBuffer");
		this.mockRequest = mock(FtpRequest.class, "mockRequest");
		this.cc = new ContextClient(mockSocketClient, mockSocketServer, mockBuffer);
	}
	
	@Test
	public void test_begin_treatment_socket_closed() throws Exception {
		when(mockSocketClient.isClosed()).thenReturn(true);
		
		cc.beginTreatment(mockRequest);
		
		verify(mockRequest, never()).processRequest(anyString());
	}
	
	@Test
	public void test_begin_treatment_socket_opened_null_command() throws Exception {
		when(mockSocketClient.isClosed()).thenReturn(false);
		when(mockBuffer.readLine()).thenReturn(null);
		
		cc.beginTreatment(mockRequest);
		
		verify(mockRequest, never()).processRequest(anyString());
	}
	
	@Test
	public void test_begin_treatment_socket_opened_not_null_command() throws Exception {
		when(mockSocketClient.isClosed()).thenReturn(false);
		when(mockBuffer.readLine()).thenReturn("Good Command");
		
		cc.beginTreatment(mockRequest);
		
		verify(mockRequest).processRequest(anyString());
	}
}
