package requestTest;

import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import data.DataOutputFTP;
import data.FileInputContainerFTP;
import misc.globalData;
import read.ReadFileCodeTable;
import read.ReadPath;
import request.RequestConnectedRETR;

public class RequestConnectedRETR_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private DataOutputFTP mockData;
	private FileInputContainerFTP mockContainer;
	private RequestConnectedRETR request;
	private ReadPath mockRd;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockData = mock(DataOutputFTP.class, "mockData");
		mockContainer = mock(FileInputContainerFTP.class, "mockContainer");
		mockRd = mock(ReadPath.class, "mockRd");

	}
	
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedRETR(mockWriter, mockRfc, false, globalData.DEFAULT_USER_FILE_SYSTEM+"/", mockRd, mockContainer);
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}
	
	@Test
	public void test_connection_successfull_transfet_good() throws Exception {
		when(mockContainer.read(null)).thenReturn(0).thenReturn(-1);
		when(mockRd.checkPath(anyString())).thenReturn("Good");

		request = new RequestConnectedRETR(mockWriter, mockRfc, true, globalData.DEFAULT_USER_FILE_SYSTEM+"/",mockRd, mockContainer);
		request.setDataOutput(mockData);
		request.setDataFile(null);
		request.process("Test.txt");
		
		verify(mockRfc).printCode(mockWriter, 226);
	}
	

}
