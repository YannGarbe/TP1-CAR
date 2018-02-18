package thread;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import factory.RequestFactory;
import request.FtpRequest;

public class ContextClient extends Thread{

	private Socket socketClient;
	private ServerSocket dataServerSocket;
	private BufferedReader input;
	
	public ContextClient(Socket socketClient, ServerSocket dataServerSocket) {
		this.socketClient = socketClient;
		this.dataServerSocket = dataServerSocket;
		
		try {
			input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			

			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void run() {

		try {
			FtpRequest ftpRequest = new FtpRequest(socketClient.getInputStream(), 
					socketClient.getOutputStream(), new RequestFactory(), this.dataServerSocket);
			while(true) {
					
				String inputCommand = "";
				
				if(!socketClient.isClosed()) {
					inputCommand = input.readLine();
					if (inputCommand != null) {
						ftpRequest.processRequest(inputCommand);
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
