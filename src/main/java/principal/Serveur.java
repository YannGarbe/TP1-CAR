package principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import exception.PortLesserThan1023Exception;

/**
 * Classe dans laquelle le main est appelé
 * @author Yann Garbé - Valentin Dambrine
 *
 */
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
		
		while(true) {
			process();
		}
	}
	
	public void process() throws IOException {
		//simpleSocket = serverSocket.accept();
		
	}
	
	public static void main (String args[]) {
		System.out.println("Serveur en construction!");
	}
	
	
}
