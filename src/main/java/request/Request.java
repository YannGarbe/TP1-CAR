package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public abstract class Request {
	
	protected OutputStreamWriter writer; 
	protected ReadFileCodeTable rfc;
	
	public Request (OutputStreamWriter writer, ReadFileCodeTable rfc) {
		this.writer = writer;
		this.rfc = rfc;
	}
	
	public abstract void process(String cmd) throws Exception;
}
