package data;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import factory.GetOwnerFileFactory;
import misc.StringTools;

public class DataOutputFTP extends DataOutputStream{

	private GetOwnerFileFactory ownerFac; 
	private StringTools st;
	
	public DataOutputFTP(OutputStream output, GetOwnerFileFactory ownerFac, StringTools st) {
		super(output);
		this.ownerFac = ownerFac;
		this.st = st;
	}
	
	public DataOutputFTP(OutputStream output) {
		super(output);
	}
	
	public void writeData(File f) throws IOException {
		this.writeBytes(st.buildFileDescription(f, ownerFac));
	}

	public void writeDataDirectory(File f) throws IOException {
		String str = "";
		for (File tmp : f.listFiles()) {
			str+=st.buildFileDescription(tmp, new GetOwnerFileFactory());
		}
		this.writeBytes(str);
	}
}
