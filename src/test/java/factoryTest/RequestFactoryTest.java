package factoryTest;

import static org.junit.Assert.*;

import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import factory.RequestFactory;
import read.ReadFileCodeTable;
import request.*;

import static org.mockito.Mockito.*;

public class RequestFactoryTest {

	private RequestFactory fac;
	private OutputStreamWriter mockWriter;
	private ReadFileCodeTable mockRfc;
	
	@Before
	public void setUp() {
		mockWriter = mock(OutputStreamWriter.class, "writer");
		mockRfc = mock(ReadFileCodeTable.class, "rfc");
		fac = new RequestFactory();
	}
	
	
	@Test
	public void test_create_factory_command_QUIT() throws Exception {
		assertEquals(RequestQUIT.class, fac.createRequest("QUIT", mockWriter, mockRfc, false, "").getClass());
	}

	@Test
	public void test_create_factory_command_USER() throws Exception {
		assertEquals(RequestUSER.class, fac.createRequest("USER", mockWriter, mockRfc, false, "").getClass());
	}

	@Test
	public void test_create_factory_command_PASS() throws Exception {
		assertEquals(RequestConnectedPASS.class, fac.createRequest("PASS", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_SYST() throws Exception {
		assertEquals(RequestConnectedSYST.class, fac.createRequest("SYST", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_PASV() throws Exception {
		assertEquals(RequestConnectedPASV.class, fac.createRequest("PASV", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_PWD() throws Exception {
		assertEquals(RequestConnectedPWD.class, fac.createRequest("PWD", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_CWD() throws Exception {
		assertEquals(RequestConnectedCWD.class, fac.createRequest("CWD", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_LIST() throws Exception {
		assertEquals(RequestConnectedLIST.class, fac.createRequest("LIST", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_STOR() throws Exception {
		assertEquals(RequestConnectedSTOR.class, fac.createRequest("STOR", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_command_RETR() throws Exception {
		assertEquals(RequestConnectedRETR.class, fac.createRequest("RETR", mockWriter, mockRfc, false, "").getClass());
	}
	
	@Test
	public void test_create_factory_other_command() throws Exception {
		assertNull(fac.createRequest("Bad request", mockWriter, mockRfc, false, ""));
	}
}
