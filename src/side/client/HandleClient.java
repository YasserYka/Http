package side.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import request.RequestHandler;
import side.server.Server;

public class HandleClient implements Runnable{

	private Socket client;
	
	private static Logger LOGGER = LogManager.getLogger(HandleClient.class);

	
	public HandleClient(Socket client) {this.client = client;}
	 
	@Override
	public void run() {
		StringBuilder request;
		PrintWriter printWrite;
		BufferedReader buffer;
		
		try{
			buffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			printWrite = new PrintWriter(client.getOutputStream());     
			request = new StringBuilder();
			
			String line;
			while((line = buffer.readLine()) != null && line.length() != 0) {request.append(line+'\n');}
			RequestHandler.handle(printWrite, request.toString());
			
			closeBoth(client, printWrite);		
			
		}catch (IOException e) { LOGGER.error(e); }
	}
	
	private static void closeBoth(Socket client, PrintWriter printWrite) {try {printWrite.close(); client.close();} catch (IOException e) {}}

}
