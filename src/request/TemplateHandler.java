package request;

import java.io.InputStream;
import java.util.HashMap;

public class TemplateHandler {
	
	private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

	//The key holds path and value holds the template file name
	public static HashMap<String, String> mapper = new HashMap<String, String>();
	
	public static boolean notFound(String path) {
		return mapper.containsKey(path);
	}
	
	//Gets path and returns a template name if found
	public static String resolvePath(String path) {
		if(notFound(path))
			return "error.html";
		
		return mapper.get(path);
	}
	
	public static InputStream getTemplateAsInputStream(String path){return CLASS_LOADER.getResourceAsStream(resolvePath(path));}
	
	//Puts path and it's related file name in hash-map
	public static void add(String path, String fileName) {mapper.put(path, fileName);}
}
