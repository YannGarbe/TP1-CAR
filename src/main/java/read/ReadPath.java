package read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import misc.Constantes;

public class ReadPath {
	private String userPath;
	
	public ReadPath(String userPath) {
		this.userPath = userPath;
	}
	
	public String checkPath(String newPath) {
		System.out.println("[NEW PATH]" + newPath);
		
		String newPathAbsolu = cleanPath(newPath);
		String newPathRelatif = cleanPath(userPath+"/"+newPath);
		
		/*Calcul absolu*/
		int nbSlashAbsolu = newPathAbsolu.split("/").length -1;
		File fileAbsolu = new File(newPathAbsolu);
		
		/*Calcul relatif*/
		int nbSlashRelatif = newPathRelatif.split("/").length -1;
		File fileRelatif = new File(newPathRelatif);
		
		//System.out.println("\n Ancien chemin : " + userPath);
		//System.out.println("Nouveau chemin Absolu : " + newPath);
		//System.out.println("Nouveau chemin Relatif : " + newPathRelatif);
		
		/*Le cas d'un envoi absolu*/
		if(newPath.startsWith(Constantes.DEFAULT_USER_FILE_SYSTEM) 
				&& nbSlashAbsolu >= Constantes.DEFAULT_LEVEL_FILE_SYSTEM
				&& fileAbsolu.exists()) {
			//System.out.println("ABSOLU ACCEPTE");
			return newPath;
		
		} 
		
		/*Le cas d'un envoi relatif*/
		if(newPathRelatif.startsWith(Constantes.DEFAULT_USER_FILE_SYSTEM) 
				&& nbSlashRelatif >= Constantes.DEFAULT_LEVEL_FILE_SYSTEM
				&& fileRelatif.exists()) {
			//System.out.println("RELATIF ACCEPTE");
			return newPathRelatif;
		}
			
		//System.out.println("RIEN N'EST ACCEPTE");
		return "";
	}
	
	
	public String cleanPath(String notCleanedPath) {
		String cleanedPath = "";
		String [] pathTab = notCleanedPath.split("/");
		List<String> listCleanPath = new ArrayList<String>(); 
		
		
		for (int i = 1; i < pathTab.length ; i++) {
			if(pathTab[i].equals("..")) {
				listCleanPath.remove(listCleanPath.size()-1);
			} else {
				listCleanPath.add("/"+pathTab[i]);
			}
		}
		
		for(String s : listCleanPath) {
			cleanedPath += s;
		}
		return cleanedPath;
	}
}
