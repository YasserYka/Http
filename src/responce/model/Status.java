package responce.model;

public enum Status {

	OK{
		@Override
		public String toString() {return "200 OK";}
	},
	NOT_FOUND{
		@Override
		public String toString() {return "404 Not Found";}
	};
}
