package withMainNonThreaded;

public class Main {
//This will not run because I'm a derp and these aren't threaded
	public static void main(String[] args) {
		// Start a new server
		Server responder = new Server();
		 responder.launch();

		// launch a client window
		NewClient clientApp = new NewClient();
		clientApp.start();
		System.exit(0);
	}

}
