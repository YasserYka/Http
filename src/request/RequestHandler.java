package request;

import java.io.PrintWriter;
import java.util.HashMap;

import request.model.Component;
import responce.Response;
import responce.Response.Builder;
import responce.model.Status;

public class RequestHandler {
	
	public static void handle(PrintWriter printWrite, String request) {
		
		Builder responseBuilder = new Response.Builder();
		
		HashMap<String, String> parsedRequeset = Parser.parse(request);
		
		String path = parsedRequeset.get(Component.Path.toString());
		
		writeStatusResponse(responseBuilder, path);
		
		writeBody(responseBuilder, path);

		printWrite.print(responseBuilder.build().getResponse());
	}
	
	public static void writeStatusResponse(Builder responseBuilder, String path) {
		if(TemplateHandler.notFound(path))
			responseBuilder.status(Status.NOT_FOUND);
		else
			responseBuilder.status(Status.OK);
	}
	
	public static void writeBody(Builder responseBuilder, String path) {responseBuilder.body(TemplateHandler.getTemplateAsString(path));}
}
