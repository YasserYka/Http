package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TemplateHandler {
	
	//ClassLoader used for retrieving Files from resources directory
	private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

	//The key holds path and value holds the template file name
	private static HashMap<String, String> MAPPER = new HashMap<String, String>();
	
	private static final String NOT_FOUND = "/notfound";
	
	static {
		MAPPER.put("/", "index.html");
		MAPPER.put(NOT_FOUND, "notFound.html");
	}
	
	public static boolean notFound(String path) {
		return !MAPPER.containsKey(path);
	}
	
	//Gets path and returns a template name if found
	private static String resolvePath(String path) {
		if(notFound(path))
			return MAPPER.get(NOT_FOUND);
		return MAPPER.get(path);
	}
	
	private static InputStream getTemplateAsInputStream(String path){ return CLASS_LOADER.getResourceAsStream(resolvePath(path)); }
	
	//Puts path and it's related file name in hash-map
	public static void add(String path, String fileName) {MAPPER.put(path, fileName);}
	
	
	public static String getTemplateAsString(String path) {
		
		InputStream inputStream = TemplateHandler.getTemplateAsInputStream(path);
		
		if(inputStream == null)
			return null;
		
		BufferedReader buffer;
		StringBuilder template = new StringBuilder();
		String line;
		
		try {
			buffer = new BufferedReader(new InputStreamReader(inputStream));
			
			while((line = buffer.readLine()) != null)
				template.append(line);
		} catch (IOException e) {
			
		}
		
		return template.toString();
	}
}
