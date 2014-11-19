package com.cs313.ace2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerBootstrap {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// Server s = new Server();
//		char input = 'n';
		// new Thread(s).start();

		// from server.java
		try {
			ServerSocket sock = new ServerSocket(6100);
			System.out.println("Server is running!");
			ExecutorService executorService = Executors.newCachedThreadPool();
			while (true) {
				Socket client = sock.accept();
				executorService.execute(new Server(client));
				System.out.println("To quit, press q, then enter at any time:");
//				input = in.nextLine().charAt(0);
//				switch (input) {
//				case 'q':
//					client.close();
//					sock.close();
//					in.close();
//					executorService.shutdown();
//					System.out.println("Terminating Server...");
//					System.exit(0);
//					break;
//				default:
//					;
//				}
			}
		} catch (IOException ioe) {

		}
	}
}
