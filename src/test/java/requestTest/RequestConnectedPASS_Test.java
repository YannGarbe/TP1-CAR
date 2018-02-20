package requestTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import read.ReadFileAuthentification;
import read.ReadFileCodeTable;
import request.RequestConnectedPASS;

public class RequestConnectedPASS_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private RequestConnectedPASS request;
	private ReadFileAuthentification mockRa;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockRa = mock(ReadFileAuthentification.class, "mockRa");
		
		request = new RequestConnectedPASS(mockWriter, mockRfc, false, mockRa);
	}
	
	@Test
	public void test_connection_successfull() throws Exception {
		when(mockRa.containsUser(anyString(), anyString())).thenReturn(true);
		
		request.process("Good Cmd");
		
		verify(mockRfc).printCode(mockWriter, 230);
	}
	
	@Test
	public void test_connection_failed() throws Exception {
		when(mockRa.containsUser(anyString(), anyString())).thenReturn(false);
		
		request.process("Good Cmd");
		
		verify(mockRfc).printCode(mockWriter, 430);
	}
	
	@Test
	public void test_getConnection_status() throws Exception {
		
		assertFalse(request.isConnected());
	}
}
