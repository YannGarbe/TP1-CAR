package request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import read.ReadFileCodeTable;



public class RequestConnectedSTOR extends RequestConnected{

	private InputStream input;
	private byte[] dataFile;
	
	public RequestConnectedSTOR(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) {
		super(writer, rfc, connected, userPath);
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			rfc.printCode(writer, 125);
			
			/*Création du fichier et de l'output Stream pour écrire dans le fichier*/
			File storedFile = new File(this.userPath+"/"+cmd);
			FileOutputStream fileOutput = new FileOutputStream(storedFile);
			
			/*Lecture du tableau de données reçu par la socket de données,*/
			/*Puis, écriture du tableau de données dans le fichier fraichement créé*/
			int cpt = input.read(dataFile);
			while (cpt != -1) {
				fileOutput.write(dataFile, 0, cpt);
				cpt = input.read(dataFile);
			}
			
			/*Fermeture et envoi des données*/
			fileOutput.close();
			fileOutput.flush();
			
			rfc.printCode(writer, 226);
			
		}
	}

	public void setDataInput (InputStream input) {
		this.input = input;
	}
	
	public void setDataFile (byte[] dataFile) {
		this.dataFile = dataFile;
	}
}
