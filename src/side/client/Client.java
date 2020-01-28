package side.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket socket;
	private static InetAddress ADDRESS;
	private static int PORT;
	private PrintWriter printWrite;
	
	public Client() {PORT = 8080;}
	
	public void create() {
		try {
			ADDRESS = InetAddress.getLocalHost();
			socket = new Socket(ADDRESS, PORT);
		}
		catch(UnknownHostException e) {}
		catch(IOException e) {}
	}
	
	public void sendMessage(String message) {
		try {
			printWrite = new PrintWriter(socket.getOutputStream(), true);
			printWrite.print(message);
			printWrite.flush();
		} catch (IOException e) {}		
	}
	
	

}

