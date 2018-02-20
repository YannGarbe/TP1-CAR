package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputContainerFTP {

	private FileInputStream fileInput;

	public FileInputContainerFTP() {}
	
	public void setInputFile(File f) throws FileNotFoundException {
		this.fileInput = new FileInputStream(f);
	}
	
	public int read(byte [] b) throws IOException {
		return fileInput.read(b);
	}
	
	public void close() throws IOException {
		this.fileInput.close();
	}
}
