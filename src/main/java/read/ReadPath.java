package read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import misc.globalData;

/**
 * Classe contenant quelques outils pour traiter le chemin du répertoire de
 * l'utilisateur.
 * 
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ReadPath {

	/**
	 * Un chemin vers le dossier de l'utilisateur.
	 */
	private String userPath;

	/**
	 * Constructeur prenant en paramètre le chemin du répertoire de
	 * l'utilisateur.
	 * 
	 * @param userPath
	 *            le chemin du répertoire de l'utilisateur.
	 */
	public ReadPath(String userPath) {
		this.userPath = userPath;
	}

	/**
	 * Vérifie si le chemin est valide.
	 * 
	 * @param newPath
	 *            le chemin à vérifier
	 * @return si le chemin est valide.
	 */
	public String checkPath(String newPath) {
		System.out.println("[NEW PATH]" + newPath);

		String newPathAbsolu = cleanPath(newPath);
		String newPathRelatif = cleanPath(userPath + "/" + newPath);

		/* Calcul absolu */
		File fileAbsolu = new File(newPathAbsolu);

		/* Calcul relatif */
		File fileRelatif = new File(newPathRelatif);

		// System.out.println("\n Ancien chemin : " + userPath);
		// System.out.println("Nouveau chemin Absolu : " + newPath);
		// System.out.println("Nouveau chemin Relatif : " + newPathRelatif);

		/* Le cas d'un envoi absolu */
		if (newPath.startsWith(globalData.user_file_system) && fileAbsolu.exists()) {
			// System.out.println("ABSOLU ACCEPTE");
			return newPath;

		}

		/* Le cas d'un envoi relatif */
		if (newPathRelatif.startsWith(globalData.user_file_system) && fileRelatif.exists()) {
			// System.out.println("RELATIF ACCEPTE");
			return newPathRelatif;
		}

		// System.out.println("RIEN N'EST ACCEPTE");
		return "";
	}

	/**
	 * Nettoie un chemin en ne renvoyant que le plus court chemin pour accéder à
	 * destination.
	 * 
	 * @param notCleanedPath
	 *            le chemin à nettoyer.
	 * @return le chemin le plus court pour accéder à destination
	 */
	public String cleanPath(String notCleanedPath) {
		String cleanedPath = "";
		String[] pathTab = notCleanedPath.split("/");
		List<String> listCleanPath = new ArrayList<String>();

		for (int i = 1; i < pathTab.length; i++) {
			if (pathTab[i].equals("..")) {
				listCleanPath.remove(listCleanPath.size() - 1);
			} else {
				listCleanPath.add("/" + pathTab[i]);
			}
		}

		for (String s : listCleanPath) {
			cleanedPath += s;
		}
		return cleanedPath;
	}

	/**
	 * Renvoie le chemin vers le répertoire de l'utilisateur.
	 * 
	 * @return le chemin vers le répertoire de l'utilisateur.
	 */
	public String getPath() {
		return this.userPath;
	}

	/**
	 * Renvoie le type de chemin.
	 * 
	 * @param newPath
	 *            un chemin
	 * @return 1 si le chemin donné est absolu, 0 s'il est relatif, -1 sinon.
	 */
	public int goodFilePath(String newPath) {

		String newPathAbsolu = cleanPath(newPath);
		String newPathRelatif = cleanPath(userPath + "/" + newPath);

		/* Le cas d'un envoi absolu */
		if (newPathAbsolu.startsWith(globalData.user_file_system) && !newPathAbsolu.contains("//")) {
			return 1;

		}

		/* Le cas d'un envoi relatif */
		if (newPathRelatif.startsWith(globalData.user_file_system) && !newPathRelatif.contains("//")) {
			return 0;
		}

		return -1;
	}

}
