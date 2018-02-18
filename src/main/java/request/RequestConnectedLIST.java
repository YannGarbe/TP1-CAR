package request;

import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import misc.StringTools;
import read.ReadFileCodeTable;

public class RequestConnectedLIST extends RequestConnected {

	private OutputStream output;
	
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
				DataOutputStream data = new DataOutputStream(output);
				data.writeBytes(StringTools.buildFileDescription(f));
				data.flush();
				rfc.printCode(writer, 226);
				data.close();
				
			} else {

				String str = "";
				System.out.println("Chemin : "+this.userPath);
				
				for (File tmp : f.listFiles()) {
					str+=StringTools.buildFileDescription(tmp);
				}

				DataOutputStream data = new DataOutputStream(output);
				data.writeBytes(str);
				rfc.printCode(writer, 226);
				data.close();
			}
			
		}
	}
	
	public void setDataOutput (OutputStream output) {
		this.output = output;
	}

}
