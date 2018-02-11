package request;

import java.io.IOException;

import exception.NonExistentFileException;

public abstract class ProcessRequest {

	public abstract void process(String cmd) throws IOException, NonExistentFileException;
}
