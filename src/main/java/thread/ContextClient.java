package thread;

import java.net.Socket;

public class ContextClient extends Thread{

	@SuppressWarnings("unused")
	private Socket socketClient;
	
	public ContextClient(Socket socketClient) {
		this.socketClient = socketClient;
		
		this.start();
	}
	
	public void run() {
		System.out.println("Client connecté !");
		System.out.println("Abandon du serveur... ");
		
		return;
	}
}
