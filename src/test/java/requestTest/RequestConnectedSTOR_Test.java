package requestTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import data.FileOutputContainerFTP;
import data.StreamInputContainerFTP;
import misc.globalData;
import read.ReadFileCodeTable;
import read.ReadPath;
import request.RequestConnectedSTOR;

public class RequestConnectedSTOR_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private StreamInputContainerFTP mockStream;
	private FileOutputContainerFTP mockContainer;
	private RequestConnectedSTOR request;
	private ReadPath mockRd;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		mockStream = mock(StreamInputContainerFTP.class, "mockStream");
		mockContainer = mock(FileOutputContainerFTP.class, "mockContainer");
		mockRd = mock(ReadPath.class, "mockRd");
	}
	
	
	@Test
	public void test_connection_failed() throws Exception {
		request = new RequestConnectedSTOR(mockWriter, mockRfc, false, globalData.DEFAULT_USER_FILE_SYSTEM+"/",mockRd, null);
		request.process("Bad Cmd");
		
		verify(mockRfc).printCode(mockWriter, 530);
	}
	
	@Test
	public void test_connection_successfull_transfet_good_absolute_path() throws Exception {
		when(mockStream.read(null)).thenReturn(0).thenReturn(-1);
		when(mockRd.goodFilePath(anyString())).thenReturn(1);
		request = new RequestConnectedSTOR(mockWriter, mockRfc, true, globalData.DEFAULT_USER_FILE_SYSTEM+"/",mockRd, mockContainer);
		request.setDataInput(mockStream);
		request.setDataFile(null);
		request.process("Test.txt");
		
		verify(mockRfc).printCode(mockWriter, 226);
	}
	
	@Test
	public void test_connection_successfull_transfet_good_relative_path() throws Exception {
		when(mockStream.read(null)).thenReturn(0).thenReturn(-1);
		when(mockRd.goodFilePath(anyString())).thenReturn(0);
		request = new RequestConnectedSTOR(mockWriter, mockRfc, true, globalData.DEFAULT_USER_FILE_SYSTEM+"/",mockRd, mockContainer);
		request.setDataInput(mockStream);
		request.setDataFile(null);
		request.process("Test.txt");
		
		verify(mockRfc).printCode(mockWriter, 226);
	}
	
	@Test
	public void test_connection_successfull_unknownFile() throws Exception {
		when(mockStream.read(null)).thenReturn(0).thenReturn(-1);
		when(mockRd.goodFilePath(anyString())).thenReturn(-1);
		
		request = new RequestConnectedSTOR(mockWriter, mockRfc, true, globalData.DEFAULT_USER_FILE_SYSTEM+"/",mockRd, mockContainer);
		request.setDataInput(mockStream);
		request.setDataFile(null);
		request.process("Test.txt");
		
		verify(mockRfc).printCode(mockWriter, 550);
	}
}
