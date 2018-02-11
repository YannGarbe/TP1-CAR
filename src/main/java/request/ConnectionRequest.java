package request;

import java.io.IOException;

import exception.NonExistentFileException;
import read.ReadAuthentification;

public class ConnectionRequest extends ProcessRequest {

	public void process(String cmd) throws IOException, NonExistentFileException {
		ReadAuthentification rd = new ReadAuthentification("./configuration/tableLogin.txt");
		
		String [] tab = cmd.split(" ");
		if (rd.containsUser(tab[0], tab[1])) {
			// à suivre...
		}
		
	}
}