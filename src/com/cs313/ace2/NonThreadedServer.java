package com.cs313.ace2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NonThreadedServer {
	public static void main(String[] args) {

		try {
			ServerSocket sock = new ServerSocket(6100);
			System.out.println("Server is running!");
			// now listen for connections
			while (true) {
				String userInput = "";
				Socket client = sock.accept();

				// await string from client and read in from buffer
				userInput = getInput(client, userInput);

				// create message to return to user
				Message clientReturn = new MessageImpl(userInput);
				clientReturn.setCounts();

				// Check value to be returned on server
				DebugInputCheck(userInput, clientReturn);

				// return message to user
				output(client, clientReturn);

				// Remember to close the stream
				// client.close();
				// System.out.println("Stream has been closed");
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

	public static String getInput(Socket client, String userInput)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		userInput = br.readLine();
		System.out.println("Input has been read");
		return userInput;
	}

	public static void DebugInputCheck(String userInput, Message clientReturn) {
		System.out
				.println("Message to be returned has been generated as follows");
		System.out.println("- User Input was: " + userInput);
		System.out.println("- Character Count: "
				+ clientReturn.getCharacterCount());
		System.out.println("- Digit Count: " + clientReturn.getDigitCount());

	}

	public static void output(Socket client, Message clientReturn)
			throws IOException {
		ObjectOutputStream outStream = new ObjectOutputStream(
				client.getOutputStream());
		outStream.writeObject(clientReturn);
		System.out.println("Output to client successful");
	}
}
