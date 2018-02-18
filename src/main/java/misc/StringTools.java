package misc;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class StringTools {

	
	public static String buildFileDescription(File f) throws IOException {
		String desc = "";
		String [] dateTab = new Date(f.lastModified()).toString().split(" ");
		String [] timeTab = dateTab[3].split(":");
		// Détermination du type de fichier (- : fichier | d : dossier)
		if (f.isDirectory()) {
			desc += "d";
		} else {
			desc += "-";
		}
		
		//Détermination des droits d'accès
		if(f.canRead()) desc += "r"; else desc += "-";
		if(f.canWrite()) desc += "w"; else desc += "-";
		if(f.canExecute()) desc += "x"; else desc += "-";
			
		
		//Ajout d'informations supplémentaires
		
		
		desc += " "+java.nio.file.Files.getOwner(f.toPath());
		desc += " "+f.length();
		desc += " "+dateTab[1];
		desc += " "+dateTab[2];
		desc += " "+timeTab[0]+":"+timeTab[1];
		desc += " "+f.getName();
		
		
		desc += "\r\n";
		return desc;
		
	}
}
