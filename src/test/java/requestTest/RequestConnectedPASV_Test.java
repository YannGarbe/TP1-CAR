package requestTest;

import static org.mockito.Mockito.*;


import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import misc.globalData;
import read.ReadFileCodeTable;
import request.RequestConnectedPASV;

public class RequestConnectedPASV_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private RequestConnectedPASV request;
	
	@Before
	public void setUp() {
		globalData.resetData();
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		
	}
	
	@Test
	public void test_connection_successfull_port_is_multiple_of_256() throws Exception {
		
		when(mockRfc.getMeaningCode(227)).thenReturn("FORMAT");
		globalData.used_port = 255;
		request = new RequestConnectedPASV(mockWriter, mockRfc, true);
		request.process("Good Cmd");
		
		verify(mockWriter).write("227 (127,0,0,1,1,0)\r\n");
	}
	
	@Test
	public void test_connection_successfull_port_is_not_multiple_of_256() throws Exception {
		
		
		when(mockRfc.getMeaningCode(227)).thenReturn("FORMAT");
		globalData.used_port = 254;
		request = new RequestConnectedPASV(mockWriter, mockRfc, true);
		request.process("Good Cmd");
		
		verify(mockWriter).write( "227 (127,0,0,1,0,255)\r\n");
	}
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedPASV(mockWriter, mockRfc, false);
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}

}
