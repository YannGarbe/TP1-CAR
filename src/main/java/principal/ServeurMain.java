package principal;

import data.Serveur;
import misc.globalData;
import read.ReadFileConfiguration;

/**
 * Classe dans laquelle le main est appelé
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ServeurMain {


	
	public static void main (String args[]) throws Exception {
		System.out.println("Lancement du serveur...");
		Serveur s;
		
		ReadFileConfiguration readConfig = new ReadFileConfiguration(globalData.DEFAULT_CONFIG_PATH);
		readConfig.setConfigurationData();

		s = new Serveur();
		System.out.println("Port : "+s.getPort());
		System.out.println("Version 0.85");
		s.connection();
	}
	
	
}
