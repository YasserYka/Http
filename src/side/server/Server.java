package side.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import side.client.HandleClient;

public class Server {
	
	private static Logger LOGGER = LogManager.getLogger(Server.class);

	private static ThreadPoolExecutor POOL = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	
	private static ServerSocket SOCKET = null;
	private final static int PORT = 5555;
	
	
	private Server() {}
	
	public static ServerSocket create(){
		if(!Optional.ofNullable(SOCKET).isPresent())
			try {
				SOCKET = new ServerSocket(PORT); 
			} catch (UnknownHostException e) { LOGGER.error(e);
				} catch (IOException e) { LOGGER.error(e); 
					}
		return SOCKET;
	}
	
	public static void run() {
		if(!Optional.ofNullable(SOCKET).isPresent()) {
}
		while(true) { accept(); }
	}
	
	private static void accept() {
		try {	
			POOL.execute(new HandleClient(SOCKET.accept()));
			LOGGER.info("CLIENT CONNECTED AND THREAD CREATED FOR IT");
		} catch (IOException e){ 
			LOGGER.error(e); 
		}
		
	}
	
	public static void close() { try {SOCKET.close(); } catch (IOException e) { LOGGER.error(e); }}
}
