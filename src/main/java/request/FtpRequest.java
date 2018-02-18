package request;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import factory.RequestFactory;
import misc.Constantes;
import read.ReadFileCodeTable;

public class FtpRequest {

	private OutputStreamWriter writer;
	private ReadFileCodeTable rfc;
	
	/*A voir par la suite*/
	@SuppressWarnings("unused")
	private OutputStream output;
	
	
	private boolean connected;
	private String loginClient;
	private String userPath;
	private RequestFactory fac;
	private char type;
	private String clientPort;
	
	private Socket dataSocket;
	private ServerSocket dataServerSocket;
	
	public FtpRequest (InputStream input , OutputStream output, RequestFactory fac, ServerSocket dataServerSocket) throws Exception{
		this.rfc = new ReadFileCodeTable(Constantes.DEFAULT_CODE_PATH);
		this.writer = new OutputStreamWriter(output);
		this.output = output;
		
		this.connected = false;
		this.fac = fac;
		this.userPath = Constantes.DEFAULT_USER_FILE_SYSTEM;
		this.type=' ';
		this.clientPort = "";
		
		this.dataServerSocket = dataServerSocket;
		
		rfc.printCode(writer, 220);
	}
	
	public void processRequest(String clientCommand) throws Exception {
		String [] fullCommand = clientCommand.split(" ");
		String commandName = fullCommand[0];
		String arg = "";
		Request request = fac.createRequest(commandName, writer, rfc, connected, userPath);
		if (clientCommand.split(" ").length > 1)
			arg = fullCommand[1];
		
		
		System.out.println("Commande reçue : -"+commandName+"-");
		//System.out.println("CHEMIN AU NIVEAU FTPREQUEST : "+this.userPath);
		switch(commandName) {
			
		case "QUIT" :
			request = new RequestQuit(writer, rfc);
			request.process(arg);
			//this.socketClient.close();
			break;
			
		case "USER" :
			request.process(arg);
			this.loginClient = arg;
			break;
		case "PASS" :
			((RequestConnectedPASS) request).setLogin(loginClient);
			request.process(arg);
			this.connected = ((RequestConnectedPASS) request).isConnected();
			break;
			
		//Requêtes nécessitant une authentification
		case "PWD" :
			request.process(arg);
			break;
			
		case "CWD" :
			request.process(arg);
			this.userPath = ((RequestConnected) request).getUserPath();
			break;
			
		case "SYST" :
			request.process(arg);
			break;

		case "LIST" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedLIST) request).setDataOutput(dataSocket.getOutputStream());
			request.process(arg);
			dataSocket.close();
			break;
		
		case "PASV" :
			if (!connected) {
				rfc.printCode(writer, 530);
			} else {
				rfc.printCode(writer, 227);
				dataSocket = dataServerSocket.accept();
			}
			break;	
			
		case "TYPE" :
			this.type = fullCommand[fullCommand.length-1].charAt(0);
			System.out.println(type);
			rfc.printCode(writer, 200);
			break;
			
		case "PORT" :
			this.clientPort = fullCommand[fullCommand.length-1];
			System.out.println(clientPort);
			rfc.printCode(writer, 200);
			break;	
			
		case "STOR" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedSTOR) request).setDataInput(dataSocket.getInputStream());
			((RequestConnectedSTOR) request).setDataFile(new byte[dataSocket.getReceiveBufferSize()]);
			request.process(arg);
			dataSocket.close();
			break;	
			
		case "RETR" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedRETR) request).setDataOutput(dataSocket.getOutputStream());
			((RequestConnectedRETR) request).setDataFile(new byte[dataSocket.getReceiveBufferSize()]);
			request.process(arg);
			dataSocket.close();
			break;	
			
		default :	
			rfc.printCode(writer, 502);
			break;
		}
		
		
	}
	
}
