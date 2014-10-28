package com.cs313.ace2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket sock = null;
		Scanner sc = new Scanner(System.in);
		String userInput = "";

		// Obtain user input to send to server
		userInput = sc.nextLine();

		// Open connection to server
		try {
			sock = new Socket("127.0.0.1", 6100);

			// send the data to the server
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			out.println(userInput);

			InputStream in = sock.getInputStream();
			ObjectInputStream inStream = new ObjectInputStream(in);
			Object recieved;

			while ((recieved = inStream.readObject()) == null)
				;

			Message imp1 = null;
			// Receive the output from server and display to the user
			if (recieved instanceof Message) {
				imp1 = (Message) recieved;
				System.out.println("Character count for " + userInput + " is: "
						+ imp1.getCharacterCount());
				System.out.println("Digit count for " + userInput + " is: "
						+ imp1.getDigitCount());

			} else {
				System.out
						.println("Message recieved is not of the correct format.");
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			sock.close();
			sc.close();
		}
	}

}
