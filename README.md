# Tp1 - Serveur FTP 

## Etudiants : 
- Yann GARBE 
- Valentin DAMBRINE

## RESUME

Ce logiciel propose un serveur FTP compatible avec différents clients FTP, comme FileZilla ou la commande ftp du Bash Linux. Il permet ainsi de se connecter à un répertoire distant et d'envoyer ou de récupérer des fichiers.

## HOWTO

- CLONER LE PROJET : $git clone https://gitlab-etu.fil.univ-lille1.fr/garbe/car-tp1-garbe-dambrine.git
- GENERER LA DOCUMENTATION : $mvn javadoc:javadoc
- SON EMPLACEMENT DEPUIS 'CAR-TP1' : ./target/site/apidocs/
- GENERER LE PROJET : $mvn package
- EXECUTER LE PROJET : $java -jar target/CAR-TP1-1.0-SNAPSHOT.jar

## Couverture actuelle des tests 

La couverture actuelle est de 51,7%

## Liste des exigences

| Code    | Exigence                                                                                  | OK(répondue)/KO(non répondue) |
| :-----: | ------------------------------------------------------------------------------------------|-------------------------------|
|  CMP    | Le code du serveur compile correctement avec Maven                                        | **OK**                        |
|  DOC    | Le code du serveur est documenté (Readme.md, Javadoc)                                     | En attente                    |
|  TST    | Le code du serveur est proporement testé (tests unitaires sous JUnit)                     | **OK**                        |
|  COO    | Le code du serveur est conçu en suivant les principes de conception objet                 | **OK**                        |
|  EXE    | Le code du serveur s'exécute                                                              | **OK**                        |
|  CON    | Je peux me connecter au serveur avec un client FTP                                        | **OK**                        |
|  BADU   | Le serveur rejette ma connection si mon utilisateur est inconnu                           | **OK**                        |
|  BADP   | Le serveur rejette ma connection si mon mot de passe est incorrect                        | **OK**                        |
|  LST    | Je peux lister le contenu d'un répertoire distant                                         | **OK**                        |
|  CWD	  | Je peux entrer dans un répertoire distant                                                 | **OK**                        |
|  CDUP   | Je peux quitter un répertoire distant                                                     | **OK**                        |
|  ROOT   | Je ne peux pas descendre dans la hiérarchie au delà du répertoire racine de l'utilisateur | **OK**                        |
|  GETT   | Je peux télécharger un fichier texte                                                      | **OK**                        |
|  GETB   | Je peux télécharger un fichier binaire (image)                                            | **OK**                        |
|  GETR   | Je peux télécharger un répertoire complet                                                 | `KO`                          | 
|  PUTT   | Je peux mettre en ligne un fichier texte                                                  | **OK**                        |
|  PUTB   | Je peux mettre en ligne un fichier binaire (image)                                        | **OK**                        |
|  PUTR   | Je peux mettre en ligne un répertoire complet                                             | `KO`                          |
|  RENF   | Je peux renommer un fichier distant                                                       | `KO`                          |
|  MKD    | Je peux créer un répertoire distant                                                       | **OK**                        |
|  REND   | Je peux renommer un répertoire distant                                                    | `KO`                          |
|  RMD    | Je peux supprimer un répertoire distant                                                   | **OK**                        |
|  CLOS   | Je peux couper proprement la connection (sans crasher le serveur)                         | **OK**                        |
|  PORT   | Je peux configurer le port du serveur FTP                                                 | **OK**                        |
|  HOME   | Je peux configurer le répertoire racine du serveur FTP                                    | **OK**                        |
|  ACPA   |	Le serveur supporte le mode ACTIF et PASSIF                                               | `KO : Passif uniquement`      |
|  THRE   | Le serveur supporte la connexion de plusieurs clients simultanés                          | **OK**                        |


## Architecture

### Paquetage

Le logiciel est découpé en huit paquets :
 * `data` : Paquet contenant la classe Serveur, gérant les demandes de connexion au serveur. Elle contient également d'autres classes de données ;
 * `exception` : Paquet contenant les différentes exceptions utilisées pour veiller au bon fonctionnement du logiciel ;
 * `factory` : Paquet contenant les classes implémentant un patron de conception de type Factory ;
 * `misc` : Diverses classes contenant des outils pour des traitements de chaînes de caractères ou pour accéder aux constantes du logiciel ;
 * `principal` : Paquet contenant la classe lançant l'application ;
 * `read` : Classes lisant les différents fichiers de configuration de l'application ;
 * `request` : Classes gérant les commandes envoyées au serveur ;
 * `thread` : Gestion de plusieurs clients.

#### Catch :
 
 * Classe `ContextClient`

	try {
			FtpRequest ftpRequest = new FtpRequest(socketClient.getInputStream(), 
					socketClient.getOutputStream(), new RequestFactory(), this.dataServerSocket);
			while(this.beginTreatment(ftpRequest)!= -1) {}
			this.socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
	}	


#### Throw :

 * Classe `Serveur`

`public Serveur () throws Exception`
`public Serveur (ServerSocket serverSocket) throws Exception`
`public Serveur (int port) throws Exception`
`public void closeServer() throws IOException`
`public void connection() throws IOException, ClosedServerException`
`public void process(ContextClientFactory contextFac) throws IOException, ClosedServerException`

 * Classe `ContextClientFactory`

`public void createContextClient(Socket socketClient, ServerSocket dataServerSocket) throws IOException`

 * Classe `GetOwnerFileFactory`

`public String getOwnerFile(File f) throws IOException`

 * Classe `RequestFactory`

`public Request createRequest(String request, OutputStreamWriter writer, ReadFileCodeTable rfc, boolean connected, String userPath) throws Exception`

 * Classe `StringTools`

`public String buildFileDescription(File f, GetOwnerFileFactory ownerFac) throws IOException`

 * Classe `ReadFile`

`public ReadFile(String filename) throws NonExistentFileException`

 * Classe `ReadFileAuthentification`

`public ReadFileAuthentification(String filename) throws NonExistentFileException`
`public boolean containsUser(String login, String pass) throws IOException`
`public int containsLogin(String login) throws IOException`
`public int containsPass(String pass) throws IOException`
`private int containsInTable(int index, String s) throws IOException`

 * Classe `ReadFileCodeTable`

`public ReadFileCodeTable(String filename) throws NonExistentFileException, IOException`
`private void loadTable() throws IOException`
`public String getMeaningCode(int code) throws NoCodeFoundException`
`public String getFullCode(int code)throws  NoCodeFoundException`
`public void printCode(OutputStreamWriter writer, int code) throws Exception`

 * Classe `ReadFileConfiguration`

`public ReadFileConfiguration(String filename) throws NonExistentFileException, IOException`
`private void loadConfig() throws IOException`
`public void setConfigurationData() throws NumberFormatException`

 * Classe `FtpRequest`

`public FtpRequest (InputStream input , OutputStream output, RequestFactory fac, ServerSocket dataServerSocket) throws Exception`
`public int processRequest(String clientCommand) throws Exception`

 * Classe `Request`

`public abstract void process(String cmd) throws Exception;`

 * Classe `ContextClient`

`public int beginTreatment(FtpRequest ftpRequest) throws Exception`
