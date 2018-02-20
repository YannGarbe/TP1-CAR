package requestTest;

import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import misc.globalData;
import read.ReadFileCodeTable;
import read.ReadPath;
import request.RequestConnectedCWD;

public class RequestConnectedCWD_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private ReadPath mockRd;
	private RequestConnectedCWD request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockRd = mock(ReadPath.class, "mockRd");
	}
	
	@Test
	public void test_connection_successfull_bad_path() throws Exception {
		
		when(mockRd.checkPath(anyString())).thenReturn("");
		
		request = new RequestConnectedCWD(mockWriter, mockRfc, true, globalData.user_file_system, mockRd);
		request.process("Good Cmd");
		
		verify(mockWriter).write( mockRfc.getFullCode(550));
	}
	
	@Test
	public void test_connection_successfull_good_path() throws Exception {
		
		when(mockRd.checkPath(anyString())).thenReturn("Good Path");
		
		request = new RequestConnectedCWD(mockWriter, mockRfc, true, globalData.user_file_system, mockRd);
		request.process("Good Cmd");
		
		verify(mockWriter).write( "200 \"Good Path\"\r\n");
	}
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedCWD(mockWriter, mockRfc, false, "", null);
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}

}
