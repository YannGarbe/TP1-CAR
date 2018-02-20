package factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import thread.ContextClient;

public class ContextClientFactory {

	public void createContextClient(Socket socketClient, ServerSocket dataServerSocket) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		ContextClient cc = new ContextClient(socketClient, dataServerSocket, input);
		cc.start();
	}
}
