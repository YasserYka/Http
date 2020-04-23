package side.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
	
	private static Logger LOGGER = LogManager.getLogger(Client.class);
	
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
		catch(UnknownHostException e) { LOGGER.error(e); }
		catch(IOException e) { LOGGER.error(e); }
	}
	
	public void sendMessage(String message) {
		try {
			printWrite = new PrintWriter(socket.getOutputStream(), true);
			printWrite.print(message);
			printWrite.flush();
		} catch (IOException e) { LOGGER.error(e); }		
	}
	
	

}

