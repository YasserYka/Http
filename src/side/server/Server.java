package side.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import request.RequestHandler;
import side.client.HandleClient;

public class Server {

	private static ServerSocket socket = null;
	private final static int PORT = 5555;
	
	private Server() {}
	
	public static ServerSocket create(){
		if(!Optional.ofNullable(socket).isPresent())
			try {socket = new ServerSocket(PORT);}catch (UnknownHostException e) {/*LOG IT*/} catch (IOException e) {/*LOG IT*/}
		return socket;
	}
	
	public static void run() {
		if(!Optional.ofNullable(socket).isPresent()) {/*Throw error "Need to call create first*/}
		while(true) {accept();}
	}
	
	private static void accept() {
		try {	
			Thread thread = new Thread(new HandleClient(socket.accept()));
			System.out.println("R: CLIENT CONNECTED AND THREAD CREATED FOR IT");
			thread.start();
		}catch (IOException e){/*LOG IT*/}
		
	}
	
	public static void close() {try {socket.close();} catch (IOException e) {}}
}
