package factory;

import java.io.OutputStreamWriter;

import data.FileInputContainerFTP;
import data.FileOutputContainerFTP;
import misc.globalData;
import read.ReadFileAuthentification;
import read.ReadFileCodeTable;
import read.ReadPath;
import request.*;

public class RequestFactory {

	
	public Request createRequest(String request, OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) throws Exception {
		switch(request) {
		case "QUIT" :
			return new RequestQUIT(writer, rfc);
		case "USER" :
			return new RequestUSER(writer, rfc, new ReadFileAuthentification(globalData.DEFAULT_LOGIN_PATH));
		case "PASS" :
			return new RequestConnectedPASS(writer, rfc, connected, new ReadFileAuthentification(globalData.DEFAULT_LOGIN_PATH));
		case "SYST" :
			return new RequestConnectedSYST(writer, rfc, connected);
		case "PASV" :
			return new RequestConnectedPASV(writer, rfc, connected);
		case "PWD" :
			return new RequestConnectedPWD(writer, rfc, connected, userPath);
		case "CWD" :
			return new RequestConnectedCWD(writer, rfc, connected, userPath, new ReadPath(userPath));
		case "LIST" :
			return new RequestConnectedLIST(writer, rfc, connected, userPath );
		case "STOR" :
			return new RequestConnectedSTOR(writer, rfc, connected, userPath,  new ReadPath(userPath), new FileOutputContainerFTP());
		case "RETR" :
			return new RequestConnectedRETR(writer, rfc, connected, userPath, new ReadPath(userPath), new FileInputContainerFTP());
		case "CDUP" :
			return new RequestConnectedCWD(writer, rfc, connected, userPath, new ReadPath(userPath));
		case "DELE" :
			return new RequestConnectedDELE(writer, rfc, connected, userPath, new ReadPath(userPath));
		case "RMD" :
			return new RequestConnectedDELE(writer, rfc, connected, userPath, new ReadPath(userPath));
		case "MKD" :
			return new RequestConnectedMKD(writer, rfc, connected, userPath, new ReadPath(userPath));
		
		default :
			return null;
		}
	}
	
}
