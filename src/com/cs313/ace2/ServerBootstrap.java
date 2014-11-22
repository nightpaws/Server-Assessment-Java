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
	 *            Takes in command-line arguments from user for handling the
	 *            application in different ways. This System does not make use
	 *            of this functionality.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try {
			// bind socket, inform of startup and create thread pool.
			ServerSocket sock = new ServerSocket(6100);
			System.out.println("Server is running!");
			ExecutorService executorService = Executors.newCachedThreadPool();

			// loop infinitely
			while (true) {

				Socket client = sock.accept();
				executorService.execute(new Server(client));
				// Code below here will not execute
			}
		} catch (IOException ioe) {
			System.err
					.println("An Input/Output Exception occurred. The Server will now close. Error: "
							+ ioe);
			System.exit(1);
		}
		in.close();
	}
}
