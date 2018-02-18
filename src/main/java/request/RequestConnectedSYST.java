package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public class RequestConnectedSYST extends RequestConnected {


	public RequestConnectedSYST(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected) {
		super(writer, rfc, connected, "");
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			
			String s = rfc.getFullCode(215);
			
			s = s.replace("NAME", "UNIX");
			
			writer.write(s);
			writer.flush();
		}
		
	}
	
}
