package data;

/**
 * Regroupe les informations sur le client connecté ou voulant se connecter, comme le login ou le mot de passe
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class Client {

	private String login;
	private String pass;
	
	/**
	 * Instancie une classe Client
	 */
	public Client() {
		this.login = "";
		this.pass = "";
	}
	
	/**
	 * Donne le login du client
	 * @return Le login du client
	 */
	public String getLogin() {
		return this.login;
	}
	
	/**
	 * Donne le mot de passe du client
	 * @return Le mot de passe du client
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * Modifie le login du client avec la chaîne passée en paramètre
	 * @param login le nouveau login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Modifie le mot de passe du client avec la chaîne passée en paramètre
	 * @param pass le nouveau mot de passe
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
}
