package requestTest;

import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import data.DataOutputFTP;
import misc.globalData;
import read.ReadFileCodeTable;
import request.RequestConnectedLIST;

public class RequestConnectedLIST_Test {
	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private DataOutputFTP mockData;
	private RequestConnectedLIST request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockData = mock(DataOutputFTP.class, "mockData");

	}
	
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedLIST(mockWriter, mockRfc, false, globalData.user_file_system);
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}
	
	@Test
	public void test_list_one_directory() throws Exception {
		
		request = new RequestConnectedLIST(mockWriter, mockRfc, true, "");
		request.setDataOutput(mockData);
		request.process(globalData.DEFAULT_USER_FILE_SYSTEM);
		
		verify(mockRfc).printCode(mockWriter, 226);
	}
	
	@Test
	public void test_list_unknown_path() throws Exception {
		request = new RequestConnectedLIST(mockWriter, mockRfc, true, "");
		request.setDataOutput(mockData);
		request.process("efzfzf");
		
		verify(mockRfc).printCode(mockWriter, 226);
	}
}
