package com.cs313.ace2;

import java.util.Scanner;

public class ServerBootstrap {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Server s = new Server();
		char input = 'n';
		new Thread(s).start();

		while (true) {
			System.out.println("To quit, press q, then enter at any time:");
			input = in.nextLine().charAt(0);
			switch (input) {
			case 'q':
				in.close();
				System.out.println("Terminating Server...");
				System.exit(0);
				break;
			default:
				;
			}
		}
	}
}
