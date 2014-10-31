package com.cs313.ace2;

import java.io.Serializable;

public class MessageImpl implements Message,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8355523039598854001L;
	
	
	// Message to be sent back to the client after they message the server
	protected String userInput = null;
	protected int charCount = 0, digitCount = 0;

	public MessageImpl(String inputString) {
		userInput = inputString;
	}

	@Override
	public void setCounts() {
		// set the counts for characters and digits
		for (int i = 0; i < userInput.length(); i++) {
			charCount++;
			if (Character.isDigit(userInput.charAt(i))) {
				digitCount++;
			}
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
