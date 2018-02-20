package request;

import java.io.OutputStreamWriter;

import read.ReadFileAuthentification;
import read.ReadFileCodeTable;

public class RequestConnectedPASS extends RequestConnected{

	private String login;
	private ReadFileAuthentification ra;
	
	public RequestConnectedPASS(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, ReadFileAuthentification ra) {
		super(writer, rfc, connected, "");
		login = "";
		this.ra = ra;
	}
	
	public void process(String cmd) throws Exception {
		if(ra.containsUser(login, cmd)) {
			connected = true;
			rfc.printCode(writer, 230);
		} else {
			rfc.printCode(writer, 430);
		}
		
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isConnected() {
		return this.connected;
	}
}
