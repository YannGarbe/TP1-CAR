package requestTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import request.FtpRequest;

import static org.mockito.Mockito.*;

import java.io.DataOutputStream;
import java.net.Socket;

public class FtpRequestTest {

	@SuppressWarnings("unused")
	private Socket socketClient;
	@SuppressWarnings("unused")
	private FtpRequest ftpRequest;
	@SuppressWarnings("unused")
	private DataOutputStream output;
	
	@Before
	public void setUp() throws Exception {
		this.output = mock(DataOutputStream.class, "output");
		this.socketClient = mock(Socket.class, "socketClient");
		//this.ftpRequest = new FtpRequest(socketClient);
	}
	
	/*@Test
	public void test_initialisationFtpRequest() throws IOException, NonExistentFileException {
		Socket test = new Socket();
		FtpRequest tmp = new FtpRequest(test);
		assertEquals(test, tmp.getSocket());
	}*/

	@Test
	public void test_UserCmd_when_login_is_incorrect() {
		assertTrue(true);
		
	}
	/*
	@Test
	public void test_PassCmd_when_pass_is_correct() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_PassCmd_when_log_correct_pass_incorrect() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_PassCmd_pass_sent_before_login() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_ListCmd_when_repertory_is_empty()  {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_ListCmd_when_repertory_isnt_empty() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_RetrCmd_when_asked_file_is_available() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_RetrCmd_when_asked_file_doesnt_exist() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_RetrCmd_when_asked_repertory_is_available() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_RetrCmd_when_asked_repertory_doesnt_exist() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_StorCmd_when_chosen_file_is_available() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_StorCmd_when_chosen_file_doesnt_exist() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_StorCmd_when_chosen_repertory_is_available() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_StorCmd_when_chosen_repertory_doesnt_exist() {
		fail("Not implemented yet");
	}
	
	@Test
	public void test_QuitCmd() {
		fail("Not implemented yet");
	}
	*/

}
