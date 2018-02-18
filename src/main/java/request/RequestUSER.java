package request;

import java.io.OutputStreamWriter;

import misc.Constantes;
import read.ReadFileAuthentification;
import read.ReadFileCodeTable;

public class RequestUSER extends Request{

	public RequestUSER(OutputStreamWriter writer, ReadFileCodeTable rfc) {
		super(writer, rfc);
	}

	public void process(String cmd) throws Exception {
		ReadFileAuthentification ra = new ReadFileAuthentification(Constantes.DEFAULT_LOGIN_PATH);
		if(ra.containsLogin(cmd) != -1) {
			rfc.printCode(writer, 331);
		} else {
			rfc.printCode(writer, 430);
		}
		
	}

}
