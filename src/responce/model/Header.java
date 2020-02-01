package responce.model;

public enum Header {
	
	SERVER{
		@Override
		public String toString() {return "Server: ";}
	},
	CONNECTION{
		@Override
		public String toString() {return "Connection: ";}
	},
	CONTENT_LENGTH{
		@Override
		public String toString() {return "Content-Length: ";}
	},
	CONTENT_TYPE{
		@Override
		public String toString() {return "Content-Type: ";}
	};

}
