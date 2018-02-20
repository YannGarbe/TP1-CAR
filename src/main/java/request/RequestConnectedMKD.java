package request;

import java.io.File;
import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;
import read.ReadPath;

public class RequestConnectedMKD extends RequestConnected{

	private ReadPath rd;
	public RequestConnectedMKD(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath, ReadPath rd) {
		super(writer, rfc, connected, userPath);
		this.rd = rd;
	}

	
	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			int res = rd.goodFilePath(cmd);
			String path = "";
			if(res == -1) {
				rfc.printCode(writer, 550);
				return;
			} else if (res == 0) {
				path = this.userPath+"/"+cmd;
			} else {
				path = cmd;
			}
			File f = new File(path);
			boolean finished = f.mkdirs();
			if(finished) {
				rfc.printCode(writer, 226);
			} else {
				rfc.printCode(writer, 451);
			}
		}
	}
}
