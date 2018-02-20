package requestTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import read.ReadFileCodeTable;
import request.RequestQUIT;

public class RequestQUIT_Test {

	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	private RequestQUIT request;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		mockRfc = mock(ReadFileCodeTable.class, "mockRfc");
		
		request = new RequestQUIT(mockWriter, mockRfc);
	}
	
	@Test
	public void test_utilisation_QUIT_command() throws Exception {
		request.process("Good Cmd");
		
		verify(mockRfc).printCode(mockWriter, 221);
	}

}
