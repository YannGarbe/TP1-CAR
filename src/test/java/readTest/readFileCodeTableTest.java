package readTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exception.*;
import misc.Constantes;
import read.ReadFileCodeTable;

public class readFileCodeTableTest {

	
	private ReadFileCodeTable rfc;
	
	@Before
	public void setUp() throws NonExistentFileException, IOException {
		this.rfc = new ReadFileCodeTable(Constantes.DEFAULT_CODE_PATH);
	}
	
	@Test
	public void test_authentification_when_initialisation_successfull(){
		assertEquals(Constantes.DEFAULT_CODE_PATH, rfc.getPath());
	}

	@Test(expected=NonExistentFileException.class)
	public void test_authentification_when_initialisation_failed_because_of_wrong_filename_path() throws NonExistentFileException, IOException {
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
	public void test_printing_code_with_meaning_known_code_number() throws NoCodeFoundException {
		String s = rfc.getFullCode(200);
		assertEquals("200 Command okay.\r\n", s);
	}
	
	@Test(expected=NoCodeFoundException.class)
	public void test_printing_code_with_meaning_unknown_code_number() throws NoCodeFoundException {
		rfc.getFullCode(666);
	}
}
