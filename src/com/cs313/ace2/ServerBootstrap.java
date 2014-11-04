package com.cs313.ace2;

import java.util.concurrent.Executors;

public class ServerBootstrap {

	public static void main(String[] args) {
		Thread worker = new Thread(new Server());
		worker.start();

	}

}
