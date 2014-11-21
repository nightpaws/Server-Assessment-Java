package com.cs313.ace2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***************************************************************************************************************
 *
 * Filename: ServerBootstrap.java
 *
 * Synopsis: Bootstrap for ACE2
 *
 * This class provides a caching thread pool which creates an instance of the
 * server class to process user input as it is received by the system. It opens
 * a socket and simply waits until it is required.
 *
 * GitHub Repository: https://github.com/nightpaws/CS313-Assessed-Coursework-2
 * 
 * Author: Craig Morrison, Reg no: 201247913
 *
 * Lab: Monday 9am
 *
 * Promise: I confirm that this submission is all my own work.
 *
 * (Craig Morrison) __________________________________________
 *
 * Version: Full version history can be found on GitHub.
 *
 **************************************************************************************************************/
public class ServerBootstrap {

	/**
	 * Main application class for the server side of the application. This
	 * handles the binding of the server socket, creation of the thread pool and
	 * the starting of new server class instances
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// Server s = new Server();
		// char input = 'n';
		// new Thread(s).start();

		// from server.java
		try {
			ServerSocket sock = new ServerSocket(6100);
			System.out.println("Server is running!");
			ExecutorService executorService = Executors.newCachedThreadPool();
			while (true) {

				Socket client = sock.accept();
				executorService.execute(new Server(client));
				// code below will not execute

				// System.out.println("To quit, press q, then enter at any time:");
				// input = in.nextLine().charAt(0);
				// switch (input) {
				// case 'q':
				// client.close();
				// sock.close();
				// in.close();
				// executorService.shutdown();
				// System.out.println("Terminating Server...");
				// System.exit(0);
				// break;
				// default:
				// ;
				// }
			}
		} catch (IOException ioe) {
			System.err.println("An Input/Output Exception occurred. The Server will now close. Error: " + e);
			System.exit(1);
		}
	}
}
