package factory;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;
import read.ReadPath;
import request.*;

public class RequestFactory {

	
	public Request createRequest(String request, OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		switch(request) {
		case "QUIT" :
			return new RequestQuit(writer, rfc);
		case "USER" :
			return new RequestUSER(writer, rfc);
		case "PASS" :
			return new RequestConnectedPASS(writer, rfc, connected);
		case "SYST" :
			return new RequestConnectedSYST(writer, rfc, connected);
		case "PWD" :
			return new RequestConnectedPWD(writer, rfc, connected, userPath);
		case "CWD" :
			return new RequestConnectedCWD(writer, rfc, connected, userPath, new ReadPath(userPath));
		case "LIST" :
			return new RequestConnectedLIST(writer, rfc, connected, userPath);
		case "STOR" :
			return new RequestConnectedSTOR(writer, rfc, connected, userPath);
		case "RETR" :
			return new RequestConnectedRETR(writer, rfc, connected, userPath);
		
		}
		return null;
	}
	
}
