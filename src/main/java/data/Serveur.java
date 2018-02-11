package data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import exception.PortLesserThan1023Exception;
import thread.ContextClient;
import thread.ForceQuitThread;

public class Serveur {

	private int port;
	private ServerSocket serverSocket ;
	private Socket simpleSocket;
	
	public Serveur () throws IOException {
		this.port = 4242;
		this.serverSocket = new ServerSocket(4242);
		this.simpleSocket = new Socket();
	}
	
	public Serveur (int port) throws PortLesserThan1023Exception, IOException {
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
	
	public boolean isConnected () {
	
		return simpleSocket.isConnected();
	}
	
	public void connection() throws IOException {
		ForceQuitThread force = new ForceQuitThread(serverSocket);
		force.run();
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
			new ContextClient(simpleSocket);
		} else {
			System.out.println("MESSAGE : Le serveur est fermé");
			System.exit(0);
		}
	}
}
