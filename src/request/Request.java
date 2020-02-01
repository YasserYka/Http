package request;

import request.model.Verb;

public class Request{
	
    public static class Builder {
		private Verb verb;
		private String path;
		//version of the protocol 1.1 will be set as default
		private String version = "HTTP/1.1";
		//Host hedear
		private String host;
		
		public Builder() {}
		
		//This points to verb but to make it readable for the user
		public Builder method(Verb verb) {
			verb = this.verb;
			return this;
		}
		
		public Builder path(String path) {
			this.path = path;
			return this;
		}
		
		public Builder host(String host) {
			this.host = host;
			return this;
		}
		
		public Request build() {
			
			StringBuilder requestAppender = new StringBuilder();
			requestAppender.append(String.format("%s / %s\r\n", verb, version));
			requestAppender.append(String.format("Host: %s\r\n", host));
			//This line should be the lastly appended (for future reference)
			requestAppender.append("\r\n");
			
			return new Request(requestAppender.toString());
		}
	}
    
	private String request;
    
    private Request() {}
    
    private Request(String request) {this.request = request;}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
