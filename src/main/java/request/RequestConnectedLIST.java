package request;

import java.io.File;
import java.io.OutputStreamWriter;

import data.DataOutputFTP;
import read.ReadFileCodeTable;

public class RequestConnectedLIST extends RequestConnected {

	private DataOutputFTP data;
	
	public RequestConnectedLIST(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		super(writer, rfc, connected, userPath);
	}

	public void process(String cmd) throws Exception {

		
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			System.out.println(cmd);
			rfc.printCode(writer, 125);
			File f = new File(cmd);
			
			
			if (!f.exists()) {
				f = new File(this.userPath);
			}
			if (!f.isDirectory()) {
				//data.writeBytes(st.buildFileDescription(f, new GetOwnerFileFactory()));
				data.writeData(f);
				data.flush();
				rfc.printCode(writer, 226);
				data.close();
				
			} else {
				
				//data.writeBytes(str);
				data.writeDataDirectory(f);
				rfc.printCode(writer, 226);
				data.close();
			}
			
		}
	}
	
	public void setDataOutput (DataOutputFTP data) {
		this.data = data;
	}

}
