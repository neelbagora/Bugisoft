package com.beyondbell.bugisoft.Utilities.TextFormatters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFormatterTest {
	@Test
	void stringToParametersTest() {
		String[] test;

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