package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import request.model.Component;
import side.client.HandleClient;

public class Parser {
	
	private static Logger LOGGER = LogManager.getLogger(Parser.class);

	//Takes the full request then tokenize it and returns it as hash-map 
	public static HashMap<String, String> parse(String request){
		BufferedReader buffer = new BufferedReader(new StringReader(request));
		HashMap<String, String> tokens = new HashMap<String, String>();
		String line;
		int indexOfColon;
		
		try {
			parseStatusLine(tokens, buffer.readLine());
			while((line = buffer.readLine()) != null){
				indexOfColon = getColonIndex(line);
				tokens.put(line.substring(0,indexOfColon), line.substring(indexOfColon + 1, line.length()) );
			}
		} catch (IOException e) { LOGGER.error(e); }
		return tokens;
	}
	
	//Takes and split status line and puts them in hash-map
	private static void parseStatusLine(HashMap<String, String> tokens, String statusLine) {
		String[] splitedStatusLine = statusLine.split(" ");
		tokens.put(Component.Method.toString(), splitedStatusLine[0]);
		tokens.put(Component.Path.toString(), splitedStatusLine[1]);
	}
	
	//takes line and return it colon index
	private static int getColonIndex(String line) {return line.indexOf(':');}
}