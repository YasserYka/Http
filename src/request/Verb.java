package request;

public enum Verb {
	POST{
		@Override
		public String toString() {return "POST";}
	},
	GET{
		@Override
		public String toString() {return "GET";}
	};
}
