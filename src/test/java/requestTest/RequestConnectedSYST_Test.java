package requestTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import read.ReadFileCodeTable;
import request.RequestConnectedSYST;

public class RequestConnectedSYST_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private RequestConnectedSYST request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		
		
	}
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedSYST(mockWriter, mockRfc, false);
		
		request.process("BAD Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}
	
	@Test
	public void test_connection_successfull() throws Exception {
		when(mockRfc.getFullCode(215)).thenReturn("Good Request");
		request = new RequestConnectedSYST(mockWriter, mockRfc, true);
		
		request.process("Good Cmd");
		
		verify(mockWriter).flush();
	}

}
