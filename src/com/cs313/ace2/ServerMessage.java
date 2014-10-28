package com.cs313.ace2;

public class ServerMessage implements Message {
	// Message to be sent back to the client after they message the server
	String userInput = "";
	protected int charCount = 0, digitCount = 0;

	@Override
	public void setCounts() {
		// set the counts for characters and digits
		for (int i = 0; i < userInput.length(); i++) {
			if (Character.isDigit(i)) {
				digitCount++;
			}
			charCount++;
		}

	}

	@Override
	public int getCharacterCount() {
		// return the number of characters
		return charCount;
	}

	@Override
	public int getDigitCount() {
		// return the number of digits
		return digitCount;
	}

}
