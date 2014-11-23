/* ==============================================================================
 *
 * Filename: Server.java
 *
 * Synopsis: Server Class for ACE2
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
 * =============================================================================*/
package com.cs313.ace2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This is a Runnable Server Interface which can be executed in a thread by
 * another class. It receives an input string from a client system connecting to
 * the same port and then processes it, providing a Message object which it then
 * returns to the client system.
 * 
 * @author Craig Morrison
 * @version 1.0
 *
 */
public class Server implements Runnable {
	protected Socket client;

	/**
	 * Constructor to obtain the Socket that the server will be receiving
	 * connections through.
	 * 
	 * @param client
	 *            stores the current socket with which the class will be
	 *            interacting. Passed as parameter during initialisation of
	 *            class
	 */
	protected Server(Socket client) {
		this.client = client;
	}

	/**
	 * Run block which is executed in a thread upon being started by
	 * ServerBootstrap.java. This code calls methods to assist it in receiving
	 * user input, processing the client input, displaying it in the server
	 * window, then outputting it back to the client
	 */
	public void run() {
		try {
			// Create variable for user input and default it to null
			String userInput = "";
			// await string from client and read in from buffer
			userInput = getInput(client);

			// create message to return to user
			Message clientReturn = new MessageImpl(userInput);
			clientReturn.setCounts();

			// Check value to be returned on server
			DebugInputCheck(userInput, clientReturn);

			// Return message object to the user
			output(client, clientReturn);

			// Close the socket
			client.close();

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
	 * Helper Method to obtain a string entered by a user on a client system
	 * sending to the port we are listening on.
	 * 
	 * @param client
	 *            The server socket that is currently being used to communicate
	 *            with the client.
	 * @param userInput
	 *            local variable to hold userInput read from input stream
	 *            listening to socket.
	 * @return userInput as a string.
	 * @throws IOException
	 *             in the event that input from the buffer is not of a format
	 *             that can be converted or interpreted as a String.
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
	 * This method is purely for analysis of code as it is passed through the
	 * system and is of no other use.
	 * 
	 * @param userInput
	 *            The string received from the user's client system.
	 * @param clientReturn
	 *            The Message Object the server has prepared for returning.
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
	 * Another helper method which exists to return the Message Object to the
	 * user's system so that it may continue acting upon it. The object needs to
	 * be 'Serializble' so that it may be sent through the ObjectOutputStream.
	 * 
	 * @param client
	 *            The server socket that is currently being used to communicate
	 *            with the client.
	 * @param clientReturn
	 *            The Message object that we wish to return to the client
	 *            system.
	 */
	private static void output(Socket client, Message clientReturn) {
		ObjectOutputStream outStream;
		try {
			outStream = new ObjectOutputStream(client.getOutputStream());
			outStream.writeObject(clientReturn);
			System.out.println("Output to client successful");
		} catch (IOException e) {
			System.err.println("Output unsuccessful.\n"
					+ "Something is wrong with the client connection.\n"
					+ "Check connectivity and try again. Error: " + e);
			e.printStackTrace();
			System.exit(5);
		}

	}
}