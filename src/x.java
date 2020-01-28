import java.net.UnknownHostException;

import side.server.Server;

public class x {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Server.create();
		} catch (UnknownHostException e) {
			// TODO Auto-grated catch block
			e.printStackTrace();
		}
		Server.run();
		Server.close();
	}

}
