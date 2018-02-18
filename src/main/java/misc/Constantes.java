package misc;

public class Constantes {

	
	public static final int DEFAULT_PORT = 1885;
	
	public static final String DEFAULT_CODE_PATH = "./configuration/tableCode.txt";
	
	public static final String DEFAULT_LOGIN_PATH = "./configuration/tableLogin.txt";
	
	public static final String DEFAULT_USER_FILE_SYSTEM = System.getProperty("user.dir") + "/FileSystem";
	
	public static final int DEFAULT_LEVEL_FILE_SYSTEM = (DEFAULT_USER_FILE_SYSTEM.split("/").length) -1;
}
