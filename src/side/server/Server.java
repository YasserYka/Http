package side.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Optional;

public class Server {

	private static ServerSocket socket = null;
	private final static int PORT = 8080;
	
	private Server() {}
	
	public static ServerSocket create() {
		if(!Optional.ofNullable(socket).isPresent())
			try {socket = new ServerSocket(PORT, 10, InetAddress.getLocalHost());}catch (IOException e) {/*LOG IT*/}
		return socket;
	}
	
	public static void run() {
		
		if(!Optional.ofNullable(socket).isPresent()) {
			//Throw error "Need to call create first"
		}
		
		while(true) {
			accept();
			
		}


	}
	
	private static void accept() {
		try {	
			Socket client = socket.accept();
			System.out.println("R: CLIENT CONNECTION ACCEPTED");
			handleClient(client);
		}catch (IOException e){/*LOG IT*/}
		
	}

	private static void handleClient(Socket client) {
		try(BufferedReader buffer = new BufferedReader(new InputStreamReader(client.getInputStream()))){
			StringBuilder conetnt = new StringBuilder();
			String line;
			while((line = buffer.readLine()) != null) {
				System.out.println(line);
			}
			client.close();
		}catch (IOException e) {/*LOG IT*/}
	}
}
