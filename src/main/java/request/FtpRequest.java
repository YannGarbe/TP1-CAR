package request;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import data.DataOutputFTP;
import data.StreamInputContainerFTP;
import factory.GetOwnerFileFactory;
import factory.RequestFactory;
import misc.StringTools;
import misc.globalData;
import read.ReadFileCodeTable;

public class FtpRequest {

	private OutputStreamWriter writer;
	private ReadFileCodeTable rfc;
	
	private boolean connected;
	private String loginClient;
	private String userPath;
	private RequestFactory fac;
	private char type;
	private String clientPort;
	
	private Socket dataSocket;
	private Request request;
	private ServerSocket dataServerSocket;
	
	public FtpRequest (InputStream input , OutputStream output, RequestFactory fac, ServerSocket dataServerSocket) throws Exception{
		this.rfc = new ReadFileCodeTable(globalData.DEFAULT_CODE_PATH);
		this.writer = new OutputStreamWriter(output);

		this.connected = false;
		this.fac = fac;
		this.userPath = globalData.user_file_system;
		this.type=' ';
		this.clientPort = "";
		
		this.dataServerSocket = dataServerSocket;
		
		rfc.printCode(writer, 220);
	}
	
	public int processRequest(String clientCommand) throws Exception {
		String [] fullCommand = clientCommand.split(" ");
		String commandName = fullCommand[0];
		String arg = "";
		request = fac.createRequest(commandName, writer, rfc, connected, userPath);
		if (clientCommand.split(" ").length > 1)
			arg = fullCommand[1];
		
		
		System.out.println("Commande reçue : -"+commandName+"-");
		//System.out.println("CHEMIN AU NIVEAU FTPREQUEST : "+this.userPath);
		switch(commandName) {
			
		case "QUIT" :
			request = new RequestQUIT(writer, rfc);
			request.process(arg);
			return -1;
			
		case "USER" :
			request.process(arg);
			this.loginClient = arg;
			return 1;
		case "PASS" :
			((RequestConnectedPASS) request).setLogin(loginClient);
			request.process(arg);
			this.connected = ((RequestConnectedPASS) request).isConnected();
			return 2;
			
		//Requêtes nécessitant une authentification
		case "PWD" :
			request.process(arg);
			return 3;
			
		case "CWD" :
			request.process(arg);
			this.userPath = ((RequestConnected) request).getUserPath();
			return 4;
			
		case "SYST" :
			request.process(arg);
			return 5;

		case "LIST" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedLIST) request).setDataOutput(new DataOutputFTP(dataSocket.getOutputStream(), new GetOwnerFileFactory(), new StringTools()));
			request.process(arg);
			dataSocket.close();
			return 6;
		
		case "PASV" :
			request.process(arg);
			
			dataSocket = dataServerSocket.accept();
			return 7;
			
		case "TYPE" :
			this.type = fullCommand[fullCommand.length-1].charAt(0);
			System.out.println(type);
			rfc.printCode(writer, 200);
			return 8;
			
		case "PORT" :
			this.clientPort = fullCommand[fullCommand.length-1];
			System.out.println(clientPort);
			rfc.printCode(writer, 200);
			return 9;
			
		case "STOR" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedSTOR) request).setDataInput(new StreamInputContainerFTP(dataSocket.getInputStream()));
			((RequestConnectedSTOR) request).setDataFile(new byte[dataSocket.getReceiveBufferSize()]);
			request.process(arg);
			dataSocket.close();
			return 10;	
			
		case "RETR" :
			((RequestConnected) request).setUserPath(this.userPath);
			((RequestConnectedRETR) request).setDataOutput(new DataOutputFTP(dataSocket.getOutputStream()));
			((RequestConnectedRETR) request).setDataFile(new byte[dataSocket.getReceiveBufferSize()]);
			
			request.process(arg);
			dataSocket.close();
			return 11;	
		case "CDUP" :
			request.process("..");
			this.userPath = ((RequestConnected) request).getUserPath();
			return 12;	
		case "DELE" :
			request.process(arg);
			return 13;	
		case "RMD" :
			request.process(arg);
			return 14;	
		case "MKD" :
			request.process(arg);
			return 15;	
			
		default :	
			rfc.printCode(writer, 502);
			return 404;
		}
		
	}
	
	public ReadFileCodeTable getRFC() {
		return this.rfc;
	}
	
	public OutputStreamWriter getWriter() {
		return this.writer;
	}
	
	public void setDataSocket(Socket dataSocket) {
		this.dataSocket = dataSocket;
	}
	
}
