/* ==============================================================================
 *
 * Filename: MessageImpl.java
 *
 * Synopsis: Message Implementation for ACE2
 *
 *
 * GitHub Repository: https://github.com/nightpaws/CS313-Assessed-Coursework-2
 * 
 * Author: Craig Morrison, Reg no: 201247913
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
 * =============================================================================*/
package com.cs313.ace2;

import java.io.Serializable;

/**
 * This is an implementation of the Message interface which allows the server to
 * communicate its working with the client. This defines the workings of
 * operations that the server may call upon the object such as the logic for
 * defining of methods such as setCount().
 * 
 * @author Craig Morrison
 * @version 1.0
 */
public class MessageImpl implements Message, Serializable {
	/**
	 * serialVersionUID = identifier for serialisation in this implementation of
	 * the Message Interface. Required by java.io.Serializable.
	 */
	private static final long serialVersionUID = -8355523039598854001L;
	protected String userInput = null;
	protected int charCount, digitCount;

	public MessageImpl(String inputString) {
		userInput = inputString;
	}

	/**
	 * Set the count values of charCount and digitCount based upon the user
	 * input provided
	 */
	public void setCounts() {
		charCount = 0;
		digitCount = 0;
		// set the counts for characters and digits
		if (userInput != null) {
			userInput = userInput.replaceAll("\\s+", "");
			for (int i = 0; i < userInput.length(); i++) {
				charCount++;
				if (Character.isDigit(userInput.charAt(i))) {
					digitCount++;
				}
			}
		}
	}

	/**
	 * Return the value stored in charCount
	 */
	public int getCharacterCount() {
		// return the number of characters
		return charCount;
	}

	/**
	 * Return the value stored in digitCount
	 */
	public int getDigitCount() {
		// return the number of digits
		return digitCount;
	}

}
