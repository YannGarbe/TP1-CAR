package read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import exception.NoCodeFoundException;
import exception.NonExistentFileException;

/**
 * Permet de lire les codes à renvoyer au client, avec le message qui leur est
 * attribué. Le fichier est structuré de façon à indiquer d'abord le code, puis
 * le message, avec un '#' pour démarquer les deux champs au moment de lire le
 * fichier ligne par ligne.
 * 
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ReadFileCodeTable extends ReadFile {

	/**
	 * Map contenant les codes et le message leur étant associé.
	 */
	private HashMap<Integer, String> codeMap;

	/**
	 * Constructeur prenant en paramètre un chemin vers le fichier de codes.
	 * 
	 * @param filename
	 *            le chemin vers le fichier tableCode.txt
	 * @throws NonExistentFileException
	 *             est engendrée si le chemin vers le fichier est invalide
	 * @throws IOException
	 *             est engendrée s'il y a un problème au niveau de la lecture du
	 *             fichier tableCode.txt
	 */
	public ReadFileCodeTable(String filename) throws NonExistentFileException, IOException {
		super(filename);

		codeMap = new HashMap<Integer, String>();
		this.loadTable();
	}

	/**
	 * Charge la table dans la map.
	 * 
	 * @throws IOException
	 *             est engendrée s'il y a un problème au niveau de la lecture du
	 *             fichier tableCode.txt
	 */
	private void loadTable() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(this.filename));
		String line = buffer.readLine();
		while (line != null) {
			String tab[] = line.split("#");
			if (tab.length == 2) {
				this.codeMap.put(Integer.parseInt(tab[0]), tab[1]);
				line = buffer.readLine();
			}
		}
		buffer.close();
	}

	/**
	 * Renvoie le message attribué à un code.
	 * 
	 * @param code
	 *            le code cherché
	 * @return le message, s'il existe, associé à un code.
	 * @throws NoCodeFoundException
	 *             est engendrée si le code n'existe pas.
	 */
	public String getMeaningCode(int code) throws NoCodeFoundException {
		String s = "";
		s = this.codeMap.get(code);
		if (s == null)
			throw new NoCodeFoundException();

		return s;
	}

	/**
	 * Renvoie le code complet, i.e. un code et son message, d'un code donné
	 * dans une seule chaîne de caractères.
	 * 
	 * @param code
	 *            le code demandé
	 * @return le code et son message dans une seule chaîne de caractères.
	 * @throws NoCodeFoundException
	 *             est engendrée si le code n'existe pas.
	 */
	public String getFullCode(int code) throws NoCodeFoundException {
		return code + " " + this.getMeaningCode(code) + "\r\n";
	}

	/**
	 * Ecrit dans un objet Writer le code complet.
	 * 
	 * @param writer
	 *            L'objet dans lequel écrire le code complet.
	 * @param code
	 *            Le code demandé
	 * @throws NoCodeFoundException
	 *             est engendrée si le code n'existe pas.
	 * @throws IOException
	 *             est engendrée s'il y a un problème au niveau de l'écriture
	 */
	public void printCode(OutputStreamWriter writer, int code) throws IOException, NoCodeFoundException {
		writer.write(this.getFullCode(code));
		writer.flush();
	}
}
