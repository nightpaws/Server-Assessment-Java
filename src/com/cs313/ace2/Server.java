/***************************************************************************************************************
 *
 * Filename: Server.java
 *
 * Synopsis: Server Class for ACE2
 *
 *  This is a Runnable Server Interface which can be executed in a thread by another class. It receives an input
 *  string from a client system connecting to the same port and then processes it, providing a Message object
 *  which it then returns to the client system.
 *
 * GitHub Repository: https://github.com/nightpaws/CS313-Assessed-Coursework-2
 * 
 * Author: Craig Morrison, Reg no: 201247913
 *
 * Lab:
 *      Monday 9am
 *
 * Promise: I confirm that this submission is all my own work.
 *
 *            (Craig Morrison)	__________________________________________
 *
 * Version: Full version history can be found on GitHub.
 *
 **************************************************************************************************************/

package com.cs313.ace2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements Runnable {
	protected Socket client;

	/**
	 * Constructor to obtain the Socket that the server will be receiving
	 * connections through.
	 * 
	 * @param client
	 */
	protected Server(Socket client) {
		this.client = client;
	}

	/**
	 * Run block which is executed in a thread upon being started by
	 * ServerBootstrap.java
	 */
	public void run() {
		// while (true) {
		try {
			// System.out.println("Server is running!");
			String userInput = "";

			// await string from client and read in from buffer
			userInput = getInput(client);

			// create message to return to user
			Message clientReturn = new MessageImpl(userInput);
			clientReturn.setCounts();
			
			// Check value to be returned on server
			DebugInputCheck(userInput, clientReturn);

			// return message to user
			output(client, clientReturn);

			// Remember to close the stream
			 client.close();
			// sock.close();
			// System.out.println("Stream has been closed");
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("I'm interrupted!");
				// break;
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		// }

	}

	/**
	 * 
	 * Helper Method to obtain a users input on a specified socket and return
	 * the value to the provided string
	 * 
	 * @param client
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	private static String getInput(Socket client) throws IOException {
		String userInput;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		userInput = br.readLine();
		System.out.println("Input has been read");
		return userInput;
	}

	/**
	 * A server side method for displaying the current state of the Message
	 * Object to show that the content being returned in the server console.
	 * 
	 * @param userInput
	 *            The string received by the user
	 * @param clientReturn
	 *            The Message Object the server has prepared for returning
	 */
	private static void DebugInputCheck(String userInput, Message clientReturn) {
		System.out
				.println("Message to be returned has been generated as follows");
		System.out.println("- User Input was: " + userInput);
		System.out.println("- Character Count: "
				+ clientReturn.getCharacterCount());
		System.out.println("- Digit Count: " + clientReturn.getDigitCount());

	}

	/**
	 * 
	 * @param client
	 * @param clientReturn
	 * @throws IOException
	 */
	private static void output(Socket client, Message clientReturn)
			throws IOException {
		ObjectOutputStream outStream = new ObjectOutputStream(
				client.getOutputStream());
		outStream.writeObject(clientReturn);
		System.out.println("Output to client successful");
	}
}