package request;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import read.ReadFileCodeTable;

public class RequestConnectedRETR extends RequestConnected{

	private OutputStream output;
	private byte[] dataFile;
	
	public RequestConnectedRETR(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		super(writer, rfc, connected, userPath);
		
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			rfc.printCode(writer, 125);
			
			/*Création du DataStream allant être envoyé au client*/
			DataOutputStream data = new DataOutputStream(output);
			
			/*Sélection du fichier à envoyer au client*/
			File retrievedFile = new File(this.userPath+"/"+cmd);
			FileInputStream fileInput = new FileInputStream(retrievedFile);
			
			/*Lecture du fichier à envoyer au client*/
			/*Puis, écriture dans le DataStream qui va être véritablement envoyé*/
			int cpt = fileInput.read(dataFile);
			while (cpt != -1) {
				data.write(dataFile, 0, cpt);
				cpt = fileInput.read(dataFile);
			}
			
			/*Fermeture et envoi des données*/
			fileInput.close();
			data.flush();
			
			rfc.printCode(writer, 226);
		}
	}
	
	public void setDataOutput (OutputStream output) {
		this.output = output;
	}
	
	public void setDataFile (byte[] dataFile) {
		this.dataFile = dataFile;
	}

}
