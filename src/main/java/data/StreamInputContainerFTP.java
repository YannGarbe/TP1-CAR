package data;

import java.io.IOException;
import java.io.InputStream;

public class StreamInputContainerFTP {

	private InputStream steamInput;

	public StreamInputContainerFTP(InputStream steamInput) {
		this.steamInput = steamInput;
	}
	
	public int read(byte [] b) throws IOException {
		return steamInput.read(b);
	}
	
	public void close() throws IOException {
		this.steamInput.close();
	}
}
