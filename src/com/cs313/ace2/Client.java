package com.cs313.ace2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/***************************************************************************************************************
 *
 * Filename: Client.java
 * 
 * Synopsis: Client Command Line Interface for ACE2
 *
 * This is a client interface for interacting with a corresponding Server. This
 * class receives user input in the form of a text string, then submits the data
 * as a string to the server. It then waits for a Message object in response
 * which it then returns to the user detailing the length, and number of digits
 * within the string.
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
public class Client {
	/**
	 * 
	 * Main class where execution of the client application commences from. This
	 * class prompts users for input, then sends the data to the server
	 * specified. Once sending is complete, it will await a response in the form
	 * of a Message object, which it then outputs to the user via command line.
	 * 
	 * @param args
	 *            Takes in command-line arguments from user for handling the
	 *            application in different ways. This System does not make use
	 *            of this functionality.
	 */
	public static void main(String[] args) {
		String version = "0.9";
		Socket sock = null;
		Scanner sc = new Scanner(System.in);
		String userInput = "";

		/*
		 * Information for user describing project. A bit untidy in the code
		 * view thanks to Eclipse's wrapping function
		 */
		System.out
				.println("Craig Morrison\t\t\t\t Assessed Coursework Exercise 2\t\t\t\tVersion: "
						+ version);
		System.out.println("Student ID 201247913\n\n");

		// Obtain user input to send to server
		System.out.println("Please enter a string of text to be processed:");
		userInput = sc.nextLine();

		try {
			// New socket for connecting to server on port 6100 at localhost
			sock = new Socket("127.0.0.1", 6100);
			System.out
					.println("\n=========\nProcessing:\nSocket Opened on client\n");

			// Use PrintWriter to send the data from userInput to the server
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			out.println(userInput);
			System.out.println("User Input data sent to the server...");

			// receive the response from the server
			System.out.println("Recieving response...");
			InputStream in = sock.getInputStream();
			ObjectInputStream inStream = new ObjectInputStream(in);
			System.out.println("Response recieved from the server...");

			// wait whilst received is null and stream input into 'received'
			Object received;
			while ((received = inStream.readObject()) == null)
				;

			// Receive the output from server and display to the user
			System.out.println("Now outputting result...\n=========\n");

			MessageImpl imp1 = null;

			if (received instanceof Message) {
				imp1 = (MessageImpl) received;
				System.out.println("Character count is: "
						+ imp1.getCharacterCount());
				System.out.println("Digit count for is: "
						+ imp1.getDigitCount());

			} else {
				System.out
						.println("Message recieved is not of the correct format.");
			}

		} catch (ConnectException e) {
			// If Server is not running display error and retry execution
			// System.err.println(e);
			System.err
					.println("Server Connection Unsuccessful. The connection to the server was refused. Is the server online? \n"
							+ e + "\n\n");
			main(args);
		} catch (ClassNotFoundException e) {
			// If Object Input Stream class is not found.
			System.err
					.println("Your system does not have the required Input Stream Class available to run this application "
							+ e + "\n\n");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// A problem occurred whilst streaming data.
			System.err
					.println("An error occurred whilst transferring data. Check your network connection status.");
			e.printStackTrace();
			System.exit(2);
		} finally {
			sc.close();
			System.exit(0);
		}
	}

}
