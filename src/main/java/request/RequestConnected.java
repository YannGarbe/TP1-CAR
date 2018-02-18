package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public abstract class RequestConnected extends Request{

	protected boolean connected;
	protected String userPath;
	
	public RequestConnected(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		super(writer, rfc);
		this.connected = connected;
		this.userPath = userPath;
	}
	
	public String getUserPath() {
		return this.userPath;
	}
	
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
}
