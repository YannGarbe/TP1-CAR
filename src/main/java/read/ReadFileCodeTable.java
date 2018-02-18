package read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import exception.NoCodeFoundException;
import exception.NonExistentFileException;

public class ReadFileCodeTable extends ReadFile{

	private HashMap<Integer, String> codeMap;

	
	public ReadFileCodeTable(String filename) throws NonExistentFileException, IOException {
		super(filename);
		
		codeMap = new HashMap<Integer, String>();
		this.loadTable();
	}
	
	private void loadTable() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(this.filename));
		String line = buffer.readLine();
		while (line != null) {
			String tab[] = line.split("#");
			this.codeMap.put(Integer.parseInt(tab[0]),tab[1]);
			line = buffer.readLine();
		}
		buffer.close();
	}
	
	public String getMeaningCode(int code) throws NoCodeFoundException {
		String s = "";
		s = this.codeMap.get(code);
		if(s == null) throw new NoCodeFoundException();
		
		return s;
	}
	
	public String getFullCode(int code)throws  NoCodeFoundException {
		return code + " "+ this.getMeaningCode(code) + "\r\n";
	}
	
	
	public void printCode(OutputStreamWriter writer, int code) throws Exception {
		writer.write(this.getFullCode(code));
		writer.flush();
	}
}
