package responce.model;

public enum ContentType {

	JSON{
		@Override
		public String toString() {return "application/json";}
	},
	HTML{
		@Override
		public String toString() {return "text/html";}
	};
}
