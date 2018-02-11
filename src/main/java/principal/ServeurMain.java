package principal;

import java.io.IOException;

import data.Serveur;

/**
 * Classe dans laquelle le main est appelé
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ServeurMain {


	
	public static void main (String args[]) {
		System.out.println("Lancement du serveur...");
		Serveur s;
		try {
			s = new Serveur();
			System.out.println("Port : "+s.getPort());
			System.out.println("Version 0.01");
			s.connection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	
}
