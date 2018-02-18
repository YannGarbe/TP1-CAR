package request;

import java.io.OutputStreamWriter;

import misc.Constantes;
import read.ReadFileAuthentification;
import read.ReadFileCodeTable;

public class RequestConnectedPASS extends RequestConnected{

	private String login;
	
	public RequestConnectedPASS(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected) {
		super(writer, rfc, connected, "");
		login = "";
	}
	
	public void process(String cmd) throws Exception {
		ReadFileAuthentification ra = new ReadFileAuthentification(Constantes.DEFAULT_LOGIN_PATH);
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
