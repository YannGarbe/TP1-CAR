package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public class RequestConnectedPWD extends RequestConnected {
	
	public RequestConnectedPWD(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		super(writer, rfc, connected, userPath);
	}
	
	

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			String s = "257 \"" + userPath + "\"\r\n";
			
			writer.write(s);
			writer.flush();
		}
	}

}
