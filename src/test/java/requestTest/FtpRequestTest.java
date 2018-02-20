package requestTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factory.RequestFactory;
import misc.globalData;
import request.*;

import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class FtpRequestTest {

	private FtpRequest ftpRequest;
	private InputStream mockInputStream;
	private OutputStream mockOutputStream;
	private RequestFactory mockFac;
	private ServerSocket mockServeur;
	private Socket mockDataSocket;
	
	
	
	@Before
	public void setUp() throws Exception {
		//this.output = mock(DataOutputStream.class, "output");
		//this.socketClient = mock(Socket.class, "socketClient");
		//this.ftpRequest = new FtpRequest(socketClient);
		
		mockInputStream = mock(InputStream.class, "mockInputStream");
		mockOutputStream = mock(OutputStream.class, "mockOutputStream");
		mockFac = mock(RequestFactory.class, "mockFac");
		mockServeur = mock(ServerSocket.class, "mockServeur");
		mockDataSocket = mock (Socket.class, "mockDataSocket");
		ftpRequest = new FtpRequest(mockInputStream, mockOutputStream, mockFac, mockServeur);
		
	}
	
	@Test
	public void test_process_quit() throws Exception {
		assertEquals(-1, ftpRequest.processRequest("QUIT"));
	}

	@Test
	public void test_process_user() throws Exception {
		RequestUSER mockRequest = mock(RequestUSER.class, "mockRequest"); 
		String s = "USER Login";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		assertEquals(1, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_pass() throws Exception {
		RequestConnectedPASS mockRequest = mock(RequestConnectedPASS.class, "mockRequest"); 
		String s = "PASS pass";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		assertEquals(2, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_pwd() throws Exception {
		RequestConnectedPWD mockRequest = mock(RequestConnectedPWD.class, "mockRequest"); 
		String s = "PWD";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		assertEquals(3, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_cwd() throws Exception {
		RequestConnectedCWD mockRequest = mock(RequestConnectedCWD.class, "mockRequest"); 
		String s = "CWD path";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		assertEquals(4, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_SYST() throws Exception {
		RequestConnectedSYST mockRequest = mock(RequestConnectedSYST.class, "mockRequest"); 
		String s = "SYST";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		assertEquals(5, ftpRequest.processRequest(s));
	}

	@Test
	public void test_process_LIST() throws Exception {
		RequestConnectedLIST mockRequest = mock(RequestConnectedLIST.class, "mockRequest"); 
		String s = "LIST";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(6, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_PASV() throws Exception {
		RequestConnectedPASV mockRequest = mock(RequestConnectedPASV.class, "mockRequest"); 
		String s = "PASV";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(7, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_type() throws Exception {
		Request mockRequest = mock(Request.class, "mockRequest"); 
		String s = "TYPE";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(8, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_port() throws Exception {
		Request mockRequest = mock(Request.class, "mockRequest"); 
		String s = "PORT";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(9, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_stor() throws Exception {
		RequestConnectedSTOR mockRequest = mock(RequestConnectedSTOR.class, "mockRequest"); 
		String s = "STOR";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(10, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_retr() throws Exception {
		RequestConnectedRETR mockRequest = mock(RequestConnectedRETR.class, "mockRequest"); 
		String s = "RETR";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(mockRequest);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(11, ftpRequest.processRequest(s));
	}
	
	@Test
	public void test_process_bad_command() throws Exception {
		//RequestConnectedRETR mockRequest = mock(RequestConnectedRETR.class, "mockRequest"); 
		String s = "zadaz";
		when(mockFac.createRequest(s.split(" ")[0], ftpRequest.getWriter(), ftpRequest.getRFC(), false,  globalData.user_file_system))
			.thenReturn(null);
		
		ftpRequest.setDataSocket(mockDataSocket);
		assertEquals(404, ftpRequest.processRequest(s));
	}
}
