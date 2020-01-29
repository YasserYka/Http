package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import responce.ContentType;
import responce.Response;
import responce.Status;
import responce.Response.Builder;

public class RequestHandler {
	
	private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

	public static void handle(PrintWriter printWrite, String request) {
		
		Builder builder = new Response.Builder();
		
		HashMap<String, String> parsedRequeset = Parser.parse(request);
		
		handleStatusLine(parsedRequeset.get("method"), builder);
		
		printWrite.print(builder.build().getResponse());
	}
	
	private static void handleStatusLine(String method, Builder builder){
		if(method.equals(Verb.GET.toString()))
			handleGetRequest(builder);
	}
	
	private static void handleGetRequest(Builder builder) {
		 String body = getTemplateAsString();
		 int lengthOfBody = body.length();
		 
		 if(body != null)
			 builder.status(Status.OK).contentLength(lengthOfBody).body(body).contentType(ContentType.HTML);
	}
	
	private static String getTemplateAsString() {
		InputStream inputStream = CLASS_LOADER.getResourceAsStream(resolveTemplateName());
		System.out.println(inputStream == null);
		if(inputStream == null)
			return null;
		
		BufferedReader buffer;
		StringBuilder template = new StringBuilder();
		String line;
		
		try {
			buffer = new BufferedReader(new InputStreamReader(inputStream));
			
			while((line = buffer.readLine()) != null)
				template.append(line);
		}catch (IOException e) {}
		
		return template.toString();
	}
	
	private static String resolveTemplateName() {
		return "index.html";
	}
	
}
