package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

public class parser {

	public HashMap<String, String> parse(String request){
		BufferedReader buffer = new BufferedReader(new StringReader(request));
		HashMap<String, String> tokens = new HashMap<String, String>();
		String line;
		int indexOfColon;
		
		try {
			line = buffer.readLine();
			tokens.put("statusLine", line);
			while(line.length() > 0) {
				indexOfColon = getColonIndex(line);
				tokens.put(line.substring(indexOfColon), line.substring(indexOfColon + 1, line.length()) );
			}
		} catch (IOException e) {/*TODO: LOG IT*/}
		return tokens;
	}
	
	private int getColonIndex(String line) {return line.indexOf(':');}
}
