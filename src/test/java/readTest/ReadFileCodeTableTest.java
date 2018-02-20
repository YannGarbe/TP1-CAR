package readTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import exception.*;
import misc.globalData;
import read.ReadFileCodeTable;
import static org.mockito.Mockito.*;

public class ReadFileCodeTableTest {

	
	private ReadFileCodeTable rfc;
	
	@Before
	public void setUp() throws NonExistentFileException, IOException {
		this.rfc = new ReadFileCodeTable(globalData.DEFAULT_CODE_PATH);
	}
	
	@Test
	public void test_codeTable_when_initialisation_successfull(){
		assertEquals(globalData.DEFAULT_CODE_PATH, rfc.getPath());
	}

	@Test(expected=NonExistentFileException.class)
	public void test_codeTable_when_initialisation_failed_because_of_wrong_filename_path() throws NonExistentFileException, IOException {
		new ReadFileCodeTable("zdadzad");
	}
	
	@Test(expected=NoCodeFoundException.class)
	public void test_searching_for_meaning_with_unknown_code_number() throws NonExistentFileException, IOException, NoCodeFoundException {
		rfc.getMeaningCode(-1);
	}
	
	@Test
	public void test_searching_for_meaning_with_known_code_number() throws NonExistentFileException, IOException, NoCodeFoundException {
		String s = rfc.getMeaningCode(530);
		assertEquals("Not logged in.", s);
	}

	@Test
	public void test_get_full_code_with_meaning_known_code_number() throws NoCodeFoundException {
		String s = rfc.getFullCode(200);
		assertEquals("200 Command okay.\r\n", s);
	}
	
	@Test(expected=NoCodeFoundException.class)
	public void test_get_full_code_with_meaning_unknown_code_number() throws NoCodeFoundException {
		rfc.getFullCode(666);
	}
	
	@Test
	public void test_printing_code_() throws Exception {
		OutputStreamWriter mockWriter = mock(OutputStreamWriter.class, "mockWriter");
		
		rfc.printCode(mockWriter, 200);
		verify(mockWriter).flush();
	}
}
