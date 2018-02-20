package read;

import java.io.File;

import exception.NonExistentFileException;

/**
 * Permet de lire un fichier
 * 
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public abstract class ReadFile {

	/**
	 * Le chemin vers un fichier à lire
	 */
	protected String filename;

	/**
	 * Constructeur pour lire un fichier
	 * 
	 * @param filename
	 *            le chemin vers un fichier à lire
	 * @throws NonExistentFileException
	 *             est engendrée si le chemin vers le fichier est invalide
	 */
	public ReadFile(String filename) throws NonExistentFileException {
		File tmp = new File(filename);
		if (!tmp.exists()) {
			throw new NonExistentFileException();
		} else {
			this.filename = filename;
		}
	}

	/**
	 * Donne le chemin du fichier voulu
	 * 
	 * @return Le chemin du fichier
	 */
	public String getPath() {
		return this.filename;
	}
}
