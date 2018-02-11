package read;

import java.io.File;

import exception.NonExistentFileException;

public abstract class ReadFile {

	protected String filename;
	
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
	 * @return Le chemin du fichier
	 */
	public String getPath() {
		return this.filename;
	}
}
