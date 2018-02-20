package request;

import java.io.OutputStreamWriter;

import read.ReadFileAuthentification;
import read.ReadFileCodeTable;

public class RequestUSER extends Request{

	private ReadFileAuthentification ra;
	
	public RequestUSER(OutputStreamWriter writer, ReadFileCodeTable rfc, ReadFileAuthentification ra) {
		super(writer, rfc);
		this.ra = ra;
	}

	public void process(String cmd) throws Exception {
		
		if(ra.containsLogin(cmd) != -1) {
			rfc.printCode(writer, 331);
		} else {
			rfc.printCode(writer, 430);
		}
		
	}

}
