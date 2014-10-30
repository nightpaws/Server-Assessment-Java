package com.cs313.ace2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		String userInput = "";

		try {
			ServerSocket sock = new ServerSocket(6100);
			System.out.println("Server is running!");
			// now listen for connections
			while (true) {
				Socket client = sock.accept();

				// await string from client and read in from buffer
				BufferedReader br = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				userInput = br.readLine();
				// br.close();
				System.out.println("Input has been read");

				// create message to return to user
				Message clientReturn = new ServerMessage(userInput);
				clientReturn.setCounts();

				// Check value to be returned on server
				System.out
						.println("Message to be returned has been generated as follows");
				System.out.println("- User Input was: " + userInput);
				System.out.println("- Character Count: "
						+ clientReturn.getCharacterCount());
				System.out.println("- Digit Count: "
						+ clientReturn.getDigitCount());

				// return message to user
				ObjectOutputStream outStream = new ObjectOutputStream(
						client.getOutputStream());
				outStream.writeObject(clientReturn);
				System.out.println("Output to client successful");

				// Remember to close the stream
				// client.close();
				System.out.println("Stream has been closed");
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}
