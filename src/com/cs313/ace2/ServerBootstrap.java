package com.cs313.ace2;

import java.util.Scanner;

public class ServerBootstrap {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Server s = new Server();
		char input = 'n';
		new Thread(s).start();

		while (true) {
			System.out.println("Quit? y|n");
			input = in.nextLine().charAt(0);
			switch (input) {
			case 'y':
				in.close();
				System.exit(0);
				break;
			default:
				;
			}
		}
	}
}
