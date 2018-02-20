package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import exception.NonExistentFileException;
import misc.globalData;

/**
 * Permet de lire un fichier de configuration pour configurer le serveur.
 * 
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ReadFileConfiguration extends ReadFile {

	/**
	 * Map contenant les associations clé/valeur contenues servant à configurer
	 * le serveur
	 */
	private HashMap<String, String> configMap;

	/**
	 * Constructeur prenant en entrée le chemin vers un fichier pour configurer
	 * le serveur
	 * 
	 * @param filename
	 *            le chemin vers le fichier de configuration du serveur
	 * @throws NonExistentFileException
	 *             est engendrée si le chemin vers le fichier est invalide
	 * @throws IOException
	 *             est engendrée s'il y a un problème au niveau de la lecture du
	 *             fichier contenant la configuration du serveur
	 */
	public ReadFileConfiguration(String filename) throws NonExistentFileException, IOException {
		super(filename);
		configMap = new HashMap<String, String>();
		loadConfig();
	}

	/**
	 * Charge la configuration du serveur
	 * 
	 * @throws IOException
	 *             est engendrée s'il y a un problème au niveau de la lecture du
	 *             fichier contenant la configuration du serveur
	 */
	private void loadConfig() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(this.filename));
		String line = buffer.readLine();
		while (line != null) {
			String tab[] = line.split("#");
			if (tab.length == 2) {
				this.configMap.put(tab[0], tab[1]);
			}
			line = buffer.readLine();
		}
		buffer.close();
	}

	/**
	 * Configure le port et le répertoire racine du serveur
	 * 
	 * @throws NumberFormatException
	 *             est engendrée si la chaine devant contenir le port ne
	 *             représente pas un chiffre
	 */
	public void setConfigurationData() throws NumberFormatException {
		/* Gestion du port */
		if (configMap.containsKey("PORT")) {
			int port = Integer.parseInt(configMap.get("PORT"));
			if (port > 1023) {
				globalData.used_port = port;
			} else {
				System.out.println("Le PORT donné dans le fichier de config est incorrect.\n" + "Le PORT par défaut ("
						+ globalData.DEFAULT_PORT + ") est donné à la place");
			}
		}

		/* Gestion du répertoire racine */
		if (configMap.containsKey("HOME")) {
			File f = new File(configMap.get("HOME"));
			if (f.exists()) {
				globalData.user_file_system = f.getAbsolutePath();
				globalData.level_file_system = (globalData.user_file_system.split("/").length) - 1;
			} else {
				System.out.println("Le répertoire racine donné dans le fichier de config est incorrect.\n"
						+ "Le répertoire par défaut (" + globalData.DEFAULT_USER_FILE_SYSTEM
						+ ") est donné à la place");
			}
		}
	}

	/**
	 * Met à jour la configuration
	 * @param configMap la nouvelle configuration du serveur
	 */
	public void setDataMap(HashMap<String, String> configMap) {
		this.configMap = configMap;
	}

}
