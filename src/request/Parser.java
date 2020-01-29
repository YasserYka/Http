package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

public class Parser {

	public static HashMap<String, String> parse(String request){
		BufferedReader buffer = new BufferedReader(new StringReader(request));
		HashMap<String, String> tokens = new HashMap<String, String>();
		String line;
		int indexOfColon;
		
		try {
			line = buffer.readLine();
			tokens.put("method", whichMethod(line));
			while((line = buffer.readLine()) != null){
				indexOfColon = getColonIndex(line);
				tokens.put(line.substring(0,indexOfColon), line.substring(indexOfColon + 1, line.length()) );
			}
		} catch (IOException e) {/*TODO: LOG IT*/}
		return tokens;
	}
	
	private static String whichMethod(String statusLine){return statusLine.contains(Verb.GET.toString()) ? Verb.GET.toString() : Verb.POST.toString();}
	
	private static int getColonIndex(String line) {return line.indexOf(':');}
}