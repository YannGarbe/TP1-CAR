package requestTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import read.*;
import request.RequestUSER;

public class RequestUSER_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private ReadFileAuthentification mockRa;
	private RequestUSER request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockRa = mock(ReadFileAuthentification.class, "mockRa");
		
		request = new RequestUSER(mockWriter, mockRfc, mockRa);
	}
	
	@Test
	public void test_process_login_successful() throws Exception {
		when(mockRa.containsLogin(anyString())).thenReturn(0);
		
		request.process("Good Cmd");
		
		verify(mockRfc).printCode(mockWriter, 331);
	}
	
	
	@Test
	public void test_process_login_failed() throws Exception {
		when(mockRa.containsLogin(anyString())).thenReturn(-1);
		
		request.process("Good Cmd");
		
		verify(mockRfc).printCode(mockWriter, 430);
	}

}
