package responce;

public class Response {

    public static class Builder {
    	//Status of response
		private Status status;
		//Server information
		private String server;
		//version of the protocol 1.1 will be set as default
		private String version = "HTTP/1.1";
		//length of body
		private int contentLengthLine;
		//Type of content if JSON or HTML page
		private ContentType contentType;
		//Holds Document/Information to be sent
		private String body;
		
		public Builder() {}
		
		//This points to verb but to make it readable for the user
		public Builder status(Status status) {
			this.status = status;
			return this;
		}
		
		public Builder server(String server) {
			this.server = server;
			return this;
		}
		
		public Builder contentType(ContentType contentType) {
			this.contentType = contentType;
			return this;
		}
		
		public Builder contentLengthLine(int contentLengthLine) {
			this.contentLengthLine = contentLengthLine;
			return this;
		}
		
		public Builder body(String body) {
			this.body = body;
			return this;
		}
		
		public Response build() {
			
			StringBuilder responseAppender = new StringBuilder();
			//Status line
			responseAppender.append(String.format("%s %s\r\n", version, status));
			//Server information
			responseAppender.append(String.format("%s\r\n", Header.SERVER + server));
			//Content Type
			responseAppender.append(String.format("%s; charset=utf-8\r\n", Header.CONTENT_TYPE.toString() + contentType));
			//Content Length
			responseAppender.append(String.format("%s\r\n", Header.CONTENT_LENGTH.toString() + contentLengthLine));
			//Empty line
			responseAppender.append("\r\n");
			//Body
			responseAppender.append(body);
			
			return new Response(responseAppender.toString());
		}
	}
    
	private String response;
    
    private Response() {}
    
    private Response(String response) {this.response = response;}

	public String getResponse() {
		return response;
	}

	public void setRequest(String response) {
		this.response = response;
	}
}
