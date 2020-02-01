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
	
	public static void handle(PrintWriter printWrite, String request) {
		
		Builder builder = new Response.Builder();
		
		HashMap<String, String> parsedRequeset = Parser.parse(request);
		
		handleStatusLine(parsedRequeset.get("method"),"UNFINISHED", builder);
		
		printWrite.print(builder.build().getResponse());
	}
	
	private static void handleStatusLine(String method, String path, Builder builder){
		if(method.equals(Verb.GET.toString()))
			handleGetRequest(path, builder);
		else if(method.equals(Verb.POST.toString()))
			handlePostRequest();
	}
	
	public static void handlePostRequest() {
		
	}
	
	private static void handleGetRequest(String path, Builder builder) {
		 String body = getTemplateAsString(path);
		 int lengthOfBody = body.length();
		 
		 if(body != null)
			 builder.status(Status.OK).contentLength(lengthOfBody).body(body).contentType(ContentType.HTML);
	}
	
	private static String getTemplateAsString(String path) {
		InputStream inputStream = TemplateHandler.getTemplateAsInputStream(path);
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
}
