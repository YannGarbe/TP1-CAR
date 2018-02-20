package requestTest;

import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import misc.globalData;
import read.ReadFileCodeTable;
import request.RequestConnectedPWD;

public class RequestConnectedPWD_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private RequestConnectedPWD request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		
	}
	
	@Test
	public void test_connection_successfull() throws Exception {
		request = new RequestConnectedPWD(mockWriter, mockRfc, true, globalData.user_file_system);
		request.process("Good Cmd");
		
		verify(mockWriter).write( "257 \"" + globalData.user_file_system + "\"\r\n");
	}
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedPWD(mockWriter, mockRfc, false, "");
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}
}
