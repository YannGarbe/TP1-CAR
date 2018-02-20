package request;

import java.io.File;
import java.io.OutputStreamWriter;

import data.DataOutputFTP;
import data.FileInputContainerFTP;
import read.ReadFileCodeTable;
import read.ReadPath;

public class RequestConnectedRETR extends RequestConnected{

	private DataOutputFTP data;
	private byte[] dataFile;
	private FileInputContainerFTP fileContainer;
	
	public RequestConnectedRETR(OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath, ReadPath rd, FileInputContainerFTP fileContainer) {
		super(writer, rfc, connected, userPath);
		this.fileContainer = fileContainer;
		
	}

	public void process(String cmd) throws Exception {
		if (!connected) {
			rfc.printCode(writer, 530);
		} else {
			rfc.printCode(writer, 125);
			
			
			/*Sélection du fichier à envoyer au client*/
			File retrievedFile = new File(this.userPath+"/"+cmd);
			fileContainer.setInputFile(retrievedFile);
			
			/*Lecture du fichier à envoyer au client*/
			/*Puis, écriture dans le DataStream qui va être véritablement envoyé*/
			int cpt = fileContainer.read(dataFile);
			while (cpt != -1) {
				data.write(dataFile, 0, cpt);
				cpt = fileContainer.read(dataFile);
			}
			
			/*Fermeture et envoi des données*/
			fileContainer.close();
			data.flush();
			
			rfc.printCode(writer, 226);
		}
	}
	
	public void setDataOutput (DataOutputFTP data) {
		this.data = data;
	}
	
	public void setDataFile (byte[] dataFile) {
		this.dataFile = dataFile;
	}

}
