package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exception.NonExistentFileException;

/**
 * Permet de gérer le système d'authentification d'un client au serveur
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ReadAuthentification {

	private String filename;
	
	/**
	 * Instancie la classe d'authentification avec le chemin du fichier de la table de login/mot de passe
	 * @param filename le chemin vers le fichier contenant la table de login/mot de passe
	 * @throws NonExistentFileException est engendrée si le chemin vers le fichier est invalide
	 */
	public ReadAuthentification(String filename) throws NonExistentFileException {
		File tmp = new File(filename);
		if (!tmp.exists()) {
			throw new NonExistentFileException();
		} else {
			this.filename = filename;
		}
		
	}
	
	/**
	 * Donne le chemin du fichier contenant la table de login/mot de passe
	 * @return Le chemin du fichier
	 */
	public String getPath() {
		return this.filename;
	}

	/**
	 * Déduit si un utilisateur a l'autorisation de se connecter avec le login et le mot de passe donnés.
	 * @param login le login de l'utilisateur
	 * @param pass le mot de passe de l'utilisateur
	 * @return True si l'utilisateur a l'autorisation de se connecter, False sinon
	 * @throws IOException est engendrée s'il y a un problème au niveau de la lecture du fichier contenant la table de login/mot de passe
	 */
	public boolean containsUser(String login, String pass) throws IOException {
		int loginRep = containsLogin(login);
		return loginRep != -1 && (loginRep == containsPass(pass)) ;
	}
	
	/**
	 * Indique le numéro de ligne à laquelle est situé le login passé en paramètre dans la table de login/mot de passe
	 * @param login le login de l'utilisateur
	 * @return Le numéro de la ligne à laquelle est situé le login. Renvoie -1 si le login n'est pas dans la table
	 * @throws IOException est engendrée s'il y a un problème au niveau de la lecture du fichier contenant la table de login/mot de passe
	 */
	public int containsLogin(String login) throws IOException {
		return this.containsInTable(0, login);
	}

	/**
	 * Indique le numéro de ligne à laquelle est situé le mot de pase passé en paramètre dans la table de login/mot de passe
	 * @param pass le mot de passe de l'utilisateur
	 * @return Le numéro de la ligne à laquelle est situé le mot de passe. Renvoie -1 si le mot de passe n'est pas dans la table
	 * @throws IOException est engendrée s'il y a un problème au niveau de la lecture du fichier contenant la table de login/mot de passe
	 */
	public int containsPass(String pass) throws IOException {
		return this.containsInTable(1, pass);
	}
	
	/**
	 * Indique le numéro de ligne à laquelle est situé la chaîne de caractères passée en paramètre avec le bon index dans la table des login mot de passe.
	 * @param index l'index de la table : 0 pour les login, 1 pour les mots de passe
	 * @param s la chaîne de caractères
	 * @return Le numéro de ligne à laquelle est située la chaîne de caractères. Renvoi -1 si la chaîne n'est pas dans la table.
	 * @throws IOException est engendrée s'il y a un problème au niveau de la lecture du fichier contenant la table de login/mot de passe
	 */
	private int containsInTable(int index, String s) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(this.filename));
		int nbLine = 1;
		String line = buffer.readLine();
		while (line != null) {
			String tab[] = line.split("#");
			if(tab[index].equals(s)) {
				buffer.close();
				return nbLine;
			}
			nbLine++;
			line = buffer.readLine();
		}
		buffer.close();
		return -1;
	}
}
