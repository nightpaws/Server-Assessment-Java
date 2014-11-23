package com.cs313.ace2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageImplTest {
	static MessageImpl mess;
	static MessageImpl messWithNo;
	static MessageImpl messNull;
	static MessageImpl messSpace;
	static MessageImpl messOnlyNo;
	static MessageImpl messOnlyText;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mess = new MessageImpl("message123"); // chars:10 digits: 3
		messWithNo = new MessageImpl("1234message"); // chars:11 digits: 4
		messNull = new MessageImpl(null); // null
		messSpace = new MessageImpl(""); // 0
		messOnlyNo = new MessageImpl("3259"); // digits: 4
		messOnlyText = new MessageImpl("message"); // chars:7
		// initialise counts
		mess.setCounts();
		messWithNo.setCounts();
		messNull.setCounts();
		messSpace.setCounts();
		messOnlyNo.setCounts();
		messOnlyText.setCounts();
	}

	@Before
	public void setUp() {
		// initialise counts
		mess.setCounts();
		messWithNo.setCounts();
		messNull.setCounts();
		messSpace.setCounts();
		messOnlyNo.setCounts();
		messOnlyText.setCounts();
	}

	@Test
	public void testGetCharacterCount() {
		assertEquals(10, mess.getCharacterCount());
		assertEquals(11, messWithNo.getCharacterCount());
		assertEquals(0, messNull.getCharacterCount());
		assertEquals(0, messSpace.getCharacterCount());
		assertEquals(4, messOnlyNo.getCharacterCount());
		assertEquals(7, messOnlyText.getCharacterCount());
	}

	@Test
	public void testGetDigitCount() {
		assertEquals(3, mess.getDigitCount());
		assertEquals(4, messWithNo.getDigitCount());
		assertEquals(0, messNull.getDigitCount());
		assertEquals(0, messSpace.getDigitCount());
		assertEquals(4, messOnlyNo.getDigitCount());
		assertEquals(0, messOnlyText.getDigitCount());
	}

}
