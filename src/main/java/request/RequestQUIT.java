package request;

import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public class RequestQUIT extends Request{

	public RequestQUIT(OutputStreamWriter writer, ReadFileCodeTable rfc) {
		super(writer, rfc);
	}

	
	public void process(String cmd) throws Exception {
		rfc.printCode(writer, 221);
	}

}
