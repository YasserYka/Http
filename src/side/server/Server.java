package side.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import side.client.HandleClient;

public class Server {
	
	public static void main(String args[]) {
		create();
		run();
	}

	private static ServerSocket socket = null;
	private final static int PORT = 5555;
	private static ThreadPoolExecutor POOL = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	
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
			POOL.execute(new HandleClient(socket.accept()));
			System.out.println("R: CLIENT CONNECTED AND THREAD CREATED FOR IT");
		}catch (IOException e){/*LOG IT*/}
		
	}
	
	public static void close() {try {socket.close();} catch (IOException e) {}}
}
