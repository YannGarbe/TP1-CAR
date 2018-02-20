package misc;

public class globalData {
	
	
	/*Détermination du port*/
	public static final int DEFAULT_PORT = 1885;
	
	public static int used_port = DEFAULT_PORT;	
	
	/*Détermination du répertoire racine*/
	public static final String DEFAULT_USER_FILE_SYSTEM = System.getProperty("user.dir") + "/FileSystem";
	
	public static String user_file_system = DEFAULT_USER_FILE_SYSTEM;
	
	/*Détermination du niveau du répertoire racine*/
	public static final int DEFAULT_LEVEL_FILE_SYSTEM = (DEFAULT_USER_FILE_SYSTEM.split("/").length) -1;
	
	public static int level_file_system = DEFAULT_LEVEL_FILE_SYSTEM;
	
	
	/*Chemin des fichiers de configuration*/
	public static final String DEFAULT_CODE_PATH = "./configuration/tableCode.txt";
	
	public static final String DEFAULT_LOGIN_PATH = "./configuration/tableLogin.txt";
	
	public static final String DEFAULT_CONFIG_PATH = "./configuration/configServeur.txt";
	
	/*Méthode de réinitialisation*/
	public static void resetData() {
		used_port = DEFAULT_PORT;
		user_file_system = DEFAULT_USER_FILE_SYSTEM;
		level_file_system = DEFAULT_LEVEL_FILE_SYSTEM;
	}
}
