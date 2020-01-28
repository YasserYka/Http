package side.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.Optional;

import request.RequestHandler;
import responce.ContentType;
import responce.Response;
import responce.Status;
import side.client.Client;

public class Server {

	private static ServerSocket socket = null;
	private final static int PORT = 1233;
	
	private Server() {}
	
	public static ServerSocket create() throws UnknownHostException {
		if(!Optional.ofNullable(socket).isPresent())
			try {socket = new ServerSocket(PORT);}catch (IOException e) {/*LOG IT*/}
		return socket;
	}
	
	public static void run() {
		if(!Optional.ofNullable(socket).isPresent()) {/*Throw error "Need to call create first*/}
		while(true) {accept();}
	}
	
	private static void accept() {
		try {	
			Socket client = socket.accept();
			System.out.println("R: CLIENT CONNECTION ACCEPTED");
			handleClient(client);
		}catch (IOException e){/*LOG IT*/}
		
	}

	private static void handleClient(Socket client) {
		StringBuilder request;
		PrintWriter printWrite;
		BufferedReader buffer;
		
		try{
			buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			printWrite = new PrintWriter(client.getOutputStream());     
			request = new StringBuilder();
			
			String line;
			while((line = buffer.readLine()) != null && line.length() != 0) {request.append(line);}
			
			RequestHandler.handle(client, printWrite, request.toString());
			closeBoth(client, printWrite);		
			
		}catch (IOException e) {/*LOG IT*/}
	}
	
	private static void closeBoth(Socket client, PrintWriter printWrite) {try {printWrite.close(); client.close();} catch (IOException e) {}}
	
	public static void close() {try {socket.close();} catch (IOException e) {}}
}
