package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputContainerFTP {

	private FileOutputStream fileOutput;

	public FileOutputContainerFTP() {}
	
	public void setOutputFile(File f) throws FileNotFoundException {
		this.fileOutput = new FileOutputStream(f);
	}
	
	
	public void write(byte [] b, int off, int len) throws IOException {
		fileOutput.write(b, off, len);
	}
	
	public void close() throws IOException {
		this.fileOutput.close();
		this.fileOutput.flush();
	}
}
