package data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import exception.PortLesserThan1023Exception;
import misc.Constantes;
import thread.ContextClient;


public class Serveur {

	private int port;
	private ServerSocket serverSocket;
	private ServerSocket dataServerSocket;
	private Socket simpleSocket;
	
	public Serveur () throws Exception {
		this.port = Constantes.DEFAULT_PORT;
		this.serverSocket = new ServerSocket(Constantes.DEFAULT_PORT);
		this.dataServerSocket = new ServerSocket(Constantes.DEFAULT_PORT+1);
		this.simpleSocket = new Socket();
		
	}
	
	public Serveur (int port) throws Exception {
		if (port <= 1023) {
			throw new PortLesserThan1023Exception();
		}
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		this.simpleSocket = new Socket();
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void closeServer() throws IOException {
		this.serverSocket.close();
		this.simpleSocket.close();
	}
	
	public void connection() throws IOException {
		while(true) {
			process();
		}
	}
	
	/**
	 * Détaille la boucle principale du serveur
	 * @throws IOException 
	 */
	private void process() throws IOException {
		
		if(!serverSocket.isClosed()) {
			
			simpleSocket = serverSocket.accept();
			
			new ContextClient(simpleSocket, dataServerSocket);
		} else {
			System.out.println("MESSAGE : Le serveur est fermé");
			System.exit(0);
		}
	}
}
