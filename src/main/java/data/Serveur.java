package data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import exception.ClosedServerException;
import exception.PortLesserThan1023Exception;
import factory.ContextClientFactory;
import misc.globalData;


public class Serveur {

	private int port;
	private ServerSocket serverSocket;
	private ServerSocket dataServerSocket;
	private Socket clientSocket;
	
	public Serveur () throws Exception {
		this.port = globalData.used_port;
		this.serverSocket = new ServerSocket(globalData.used_port);
		this.dataServerSocket = new ServerSocket(globalData.used_port+1);
		this.clientSocket = new Socket();
		
	}
	
	public Serveur (ServerSocket serverSocket) throws Exception {
		this.port = globalData.used_port;
		this.serverSocket = serverSocket;
		this.dataServerSocket = new ServerSocket(globalData.used_port+1);
		this.clientSocket = new Socket();
		
	}
	
	public Serveur (int port) throws Exception {
		if (port <= 1024) {
			throw new PortLesserThan1023Exception();
		}
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		this.dataServerSocket = new ServerSocket(globalData.used_port+1);
		this.clientSocket = new Socket();
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void closeServer() throws IOException {
			if(!this.serverSocket.isClosed()) this.serverSocket.close();
			this.dataServerSocket.close();
			if (this.clientSocket != null) this.clientSocket.close();
	}
	
	public void connection() throws IOException, ClosedServerException {
		while(true) {
			process(new ContextClientFactory());
		}
	}
	
	
	/**
	 * Détaille la boucle principale du serveur
	 * @throws IOException 
	 * @throws ClosedServerException 
	 */
	public void process(ContextClientFactory contextFac) throws IOException, ClosedServerException {
		
		if(!serverSocket.isClosed()) {
			
			clientSocket = serverSocket.accept();
			
			contextFac.createContextClient(clientSocket, dataServerSocket);
		} else {
			System.out.println("MESSAGE : Le serveur est fermé");
			this.closeServer();
			throw new ClosedServerException();
		}
	}
}
