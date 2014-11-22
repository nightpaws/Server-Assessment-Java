/* ==============================================================================
 *
 * Filename: Message.java
 *
 * Synopsis: Message Interface for ACE2
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

/**
 * A bare ADT for defining the structure of a Message object as used by the
 * server to communicate its working back to the client.
 * 
 * @author Craig Morrison
 * @version 1.0
 *
 */
public interface Message {
	/**
	 * set the counts for characters and digits
	 */
	public void setCounts();

	/**
	 * return the number of characters
	 */
	public int getCharacterCount();

	/**
	 * return the number of digits
	 */
	public int getDigitCount();
}
