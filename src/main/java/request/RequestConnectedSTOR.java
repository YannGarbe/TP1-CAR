package request;

import java.io.File;
import java.io.OutputStreamWriter;

import data.FileOutputContainerFTP;
import data.StreamInputContainerFTP;
import read.ReadFileCodeTable;
import read.ReadPath;



public class RequestConnectedSTOR extends RequestConnected{

	private StreamInputContainerFTP input;
	private byte[] dataFile;
	private FileOutputContainerFTP outputContainer;
	private ReadPath rd;
	
	public RequestConnectedSTOR(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath, ReadPath rd, FileOutputContainerFTP outputContainer) {
		super(writer, rfc, connected, userPath);
		this.outputContainer = outputContainer;
		this.rd = rd;
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			rfc.printCode(writer, 125);
			
			int res = rd.goodFilePath((cmd));
			String s = "";
			if(res == -1) {
				rfc.printCode(writer, 550);
				return;
			} else if (res == 0) {
				s = this.userPath+"/"+cmd;
			} else {
				s = cmd;
			}
			
			/*Création du fichier et de l'output Stream pour écrire dans le fichier*/
			File storedFile = new File(s);
			
				
			
			outputContainer.setOutputFile(storedFile);
			
			/*Lecture du tableau de données reçu par la socket de données,*/
			/*Puis, écriture du tableau de données dans le fichier fraichement créé*/
			int cpt = input.read(dataFile);
			while (cpt != -1) {
				outputContainer.write(dataFile, 0, cpt);
				cpt = input.read(dataFile);
			}
			
			/*Fermeture et envoi des données*/
			outputContainer.close();
			
			rfc.printCode(writer, 226);
			
		}
	}

	public void setDataInput (StreamInputContainerFTP input) {
		this.input = input;
	}
	
	public void setDataFile (byte[] dataFile) {
		this.dataFile = dataFile;
	}
}
