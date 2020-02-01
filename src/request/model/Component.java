package request.model;

public enum Component {
	Method{
		@Override
		public String toString() {return "Method";}
	},
	Path{
		@Override
		public String toString() {return "Path";}
	},
	body{
		@Override
		public String toString() {return "Body";}
	}
}
