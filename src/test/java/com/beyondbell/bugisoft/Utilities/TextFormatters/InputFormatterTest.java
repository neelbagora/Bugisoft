package com.beyondbell.bugisoft.Utilities.TextFormatters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFormatterTest {
	@Test
	void stringToParametersTest() {
		String[] test;

		// Checks if Message Length is 0 or 1
		test = InputFormatter.stringToParameters("");
		assertEquals(1, test.length);
		assertEquals("null", test[0]);

		for (char i = 0; i < 256; i++) {
			test = InputFormatter.stringToParameters(String.valueOf(i));
			assertEquals(1, test.length);
			assertEquals("null", test[0]);
		}


		// Checks For Prefix
		for (char i = 0; i < 256; i++) {
			test = InputFormatter.stringToParameters(String.valueOf(i) + "test");
			if (i == 33) {          // !
				assertEquals(2, test.length);
				assertEquals("!", test[0]);
				assertEquals("test", test[1]);
			} else if (i == 62) {   // >
				assertEquals(2, test.length);
				assertEquals(">", test[0]);
				assertEquals("test", test[1]);
			} else if (i == 59) {   // ;
				assertEquals(2, test.length);
				assertEquals(";", test[0]);
				assertEquals("test", test[1]);
			} else if (i == 94) {   // ^
				assertEquals(2, test.length);
				assertEquals("^", test[0]);
				assertEquals("test", test[1]);
			} else {
				assertEquals(1, test.length);
				assertEquals("null", test[0]);
			}
		}


		// Trailing Spaces Test
		test = InputFormatter.stringToParameters("!test ");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("test", test[1]);


		// Normal Use Case Tests
		test = InputFormatter.stringToParameters("!test");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("test", test[1]);

		test = InputFormatter.stringToParameters("!Test");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("Test", test[1]);

		test = InputFormatter.stringToParameters("! test");
		assertEquals(3, test.length);
		assertEquals("!", test[0]);
		assertEquals("", test[1]);
		assertEquals("test", test[2]);

		test = InputFormatter.stringToParameters("!test1 test2");
		assertEquals(3, test.length);
		assertEquals("!", test[0]);
		assertEquals("test1", test[1]);
		assertEquals("test2", test[2]);
	}
}