package request;

import java.io.OutputStreamWriter;

import misc.globalData;
import read.ReadFileCodeTable;

public class RequestConnectedPASV extends RequestConnected{

	public RequestConnectedPASV(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected) {
		super(writer, rfc, connected, "");
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			String s = rfc.getMeaningCode(227);
			int p1, p2 = 0;
			int port = globalData.used_port+1;
			if ((port % 256) != 0) {
				p2 = port % 256; 
			}
			p1 = (port - p2)/256;
			s = s.replace("FORMAT", "(127,0,0,1,"+p1+","+p2+")");
			
			writer.write("227 "+s+"\r\n");
			writer.flush();;
		}
	}

}
