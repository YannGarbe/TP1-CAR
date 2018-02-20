package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import factory.RequestFactory;
import request.FtpRequest;

/**
 * Classe permettant de prendre en charge plusieurs clients en attribuant chaque
 * demande de connexion à un nouveau thread.
 * 
 * @author Yann Garbé - Valentin Dambrine
 *
 */
public class ContextClient extends Thread {

	/**
	 * Le socket client
	 */
	private Socket socketClient;

	/**
	 * Le socket serveur contenant les données
	 */
	private ServerSocket dataServerSocket;

	/**
	 * Un buffer lisant une entrée
	 */
	private BufferedReader input;

	/**
	 * Constructeur initialisant un socket client, un socket serveur et un
	 * buffer
	 * 
	 * @param socketClient
	 *            un socket client
	 * @param dataServerSocket
	 *            un socket serveur
	 * @param input
	 *            un buffer d'entrée
	 */
	public ContextClient(Socket socketClient, ServerSocket dataServerSocket, BufferedReader input) {
		this.socketClient = socketClient;
		this.dataServerSocket = dataServerSocket;
		this.input = input;

	}

	/**
	 * Méthode lançant le thread.
	 */
	public void run() {
		try {
			FtpRequest ftpRequest = new FtpRequest(socketClient.getInputStream(), socketClient.getOutputStream(),
					new RequestFactory(), this.dataServerSocket);
			while (this.beginTreatment(ftpRequest) != -1) {
			}
			this.socketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lance le traitement d'une requête FTP
	 * 
	 * @param ftpRequest
	 *            une commande à traiter
	 * @return un numéro différent selon la commande appelée, ou 404 si la
	 *         commande n'existe pas.
	 * @throws Exception
	 *             est engendrée lors d'un problème dans la lecture du buffer ou
	 *             dans le traitement de la requête.
	 */
	public int beginTreatment(FtpRequest ftpRequest) throws Exception {
		String inputCommand = "";
		int repply = 0;
		if (!socketClient.isClosed()) {
			inputCommand = input.readLine();

			if (inputCommand != null) {
				repply = ftpRequest.processRequest(inputCommand);

			}
		}
		return repply;
	}
}
