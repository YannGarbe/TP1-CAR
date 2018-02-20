package request;

import java.io.File;
import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;
import read.ReadPath;

public class RequestConnectedDELE extends RequestConnected{

	private ReadPath rd;
	public RequestConnectedDELE(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath, ReadPath rd) {
		super(writer, rfc, connected, userPath);
		this.rd = rd;
	}

	
	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			String path = rd.checkPath(cmd);
			if(path.equals("")) {
				rfc.printCode(writer, 550);
				return;
			}
			File f = new File(path);
			boolean finished = f.delete();
			if(finished) {
				rfc.printCode(writer, 226);
			} else {
				rfc.printCode(writer, 451);
			}
		}
	}

}
