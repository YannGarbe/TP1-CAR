package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ForceQuitThread extends Thread{

	private ServerSocket server;
	
	public ForceQuitThread(ServerSocket server) {
		this.server = server;
		
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		String s = "";
		while (!s.equals("forcequit")) {
			
			s = sc.nextLine();
			
			if(s.equals("quit")) {
				try {
					server.close();
					System.out.println("MESSAGE : Fermeture du serveur");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		sc.close();
		return;
	}
}
