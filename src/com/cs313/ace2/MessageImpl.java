/***************************************************************************************************************
 *
 * Filename: MessageImpl.java
 *
 * Synopsis: Message Implementation for ACE2
 *
 *   This is an implementation of the Message interface which allows the server to communicate its working
 *  with the client. This defines the workings of operations that the server may call upon the object
 *  such as the logic for defining of methods such as setCount().
 *
 * GitHub Repository: https://github.com/nightpaws/CS313-Assessed-Coursework-2
 * 
 * Author:
 *      Craig Morrison, Reg no: 201247913
 *
 * Lab:
 *      Monday 9am
 *
 * Promise: I confirm that this submission is all my own work.
 *
 *            (Craig Morrison)	__________________________________________
 *
 * Version: Full version history can be found on GitHub.
 *
 **************************************************************************************************************/package com.cs313.ace2;

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
