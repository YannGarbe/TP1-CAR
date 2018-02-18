package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;
import read.ReadPath;

public class RequestConnectedCWD extends RequestConnected{

	private ReadPath rd;
	
	public RequestConnectedCWD(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath, ReadPath rd) {
		super(writer, rfc, connected, userPath);
		this.rd = rd;
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			String s = "";
			String tmpPath = rd.checkPath(cmd);
			//System.out.println("     TMPPATH" + tmpPath);
			if(tmpPath.equals("")) {
				s = rfc.getFullCode(550);
			} else {
				this.userPath = tmpPath; 
				s = "200 \"" + tmpPath + "\"\r\n";
				
			}
			
			
			writer.write(s);
			writer.flush();
		}
		
		//System.out.println("     CHEMIN NIVEAU FIN CWD" + userPath);
	}

}
