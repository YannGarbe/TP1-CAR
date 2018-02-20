package miscTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factory.GetOwnerFileFactory;
import misc.StringTools;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

public class StringToolsTest {

	private File mockFile;
	private GetOwnerFileFactory mockFac;
	@Before
	public void setUp() throws IOException {
		mockFile = mock(File.class, "mockFile");
		mockFac = mock(GetOwnerFileFactory.class, "mockFac");
		
		when(mockFile.lastModified()).thenReturn((long) 0);
		when(mockFac.getOwnerFile(mockFile)).thenReturn("no");
		when(mockFile.length()).thenReturn((long) 0);
		when(mockFile.getName()).thenReturn("NoFile");
	}
	
	@Test
	public void test_buildDescription_notDir_notWrite_notRead_notExe() throws IOException {
		
		when(mockFile.isDirectory()).thenReturn(false);
		when(mockFile.canRead()).thenReturn(false);
		when(mockFile.canWrite()).thenReturn(false);
		when(mockFile.canExecute()).thenReturn(false);

		StringTools st = new StringTools();
		assertEquals("---- no 0 Jan 01 01:00 NoFile\r\n", st.buildFileDescription(mockFile, mockFac));		
	}
	
	@Test
	public void test_buildDescription_Dir_Write_Read_Exe() throws IOException {
		
		when(mockFile.isDirectory()).thenReturn(true);
		when(mockFile.canRead()).thenReturn(true);
		when(mockFile.canWrite()).thenReturn(true);
		when(mockFile.canExecute()).thenReturn(true);
		
		StringTools st = new StringTools();
		assertEquals("drwx no 0 Jan 01 01:00 NoFile\r\n", st.buildFileDescription(mockFile, mockFac));		
	}

}
