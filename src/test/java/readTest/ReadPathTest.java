package readTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import misc.globalData;
import read.ReadPath;

public class ReadPathTest {

	private ReadPath rp;
	
	@Before
	public void setUp() {
		globalData.resetData();
	}
	
	@Test
	public void test_initialisation() {
		String s = "efzfze";
		rp = new ReadPath(s);
		assertEquals(s, rp.getPath());
	}
	
	@Test
	public void test_clean_path_without_double_point() {
		String s = "/bonjour/bonsoir/bonneNuit";
		rp = new ReadPath("");
		assertEquals(s, rp.cleanPath(s));
	}
	
	@Test
	public void test_clean_path_with_double_point() {
		String s = "/bonjour/bonsoir/bonneNuit/../../../salut/cava/..";
		rp = new ReadPath("");
		assertEquals("/salut", rp.cleanPath(s));
	}
	
	@Test
	public void test_check_path_forbidden_absolute_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals("", rp.checkPath(globalData.DEFAULT_USER_FILE_SYSTEM+".."));
	}
	
	@Test
	public void test_check_path_forbidden_relative_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals("", rp.checkPath(".."));
	}
	
	@Test
	public void test_check_path_unknown_absolute_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals("", rp.checkPath(globalData.DEFAULT_USER_FILE_SYSTEM+"/666"));
	}
	
	@Test
	public void test_check_path_unknown_relative_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals("", rp.checkPath("666"));
	}
	
	@Test
	public void test_check_path_good_relative_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals(globalData.DEFAULT_USER_FILE_SYSTEM+"/Test", rp.checkPath("Test"));
	}

	@Test
	public void test_check_path_good_absolute_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals(globalData.DEFAULT_USER_FILE_SYSTEM+"/Test", rp.checkPath(globalData.DEFAULT_USER_FILE_SYSTEM+"/Test"));
	}
	
	
	@Test
	public void test_goodFilePath_path_good_relative_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals(0, rp.goodFilePath("Test"));
	}
	
	@Test
	public void test_goodFilePath_path_good_absolute_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals(1, rp.goodFilePath(globalData.DEFAULT_USER_FILE_SYSTEM+"/Test"));
	}
	
	@Test
	public void test_goodFilePath_path_bad_path() {
		rp = new ReadPath(globalData.DEFAULT_USER_FILE_SYSTEM);
		assertEquals(-1, rp.goodFilePath("/home"));
	}
}
