package noMainNonThreaded;

public interface Message {
	// set the counts for characters and digits
	public void setCounts();

	// return the number of characters
	public int getCharacterCount();

	// return the number of digits
	public int getDigitCount();
}
