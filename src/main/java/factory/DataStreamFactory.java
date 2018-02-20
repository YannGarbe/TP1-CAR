package factory;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class DataStreamFactory {

	public DataOutputStream createOutputStream(OutputStream output) {
		return new DataOutputStream(output);
	}
}
