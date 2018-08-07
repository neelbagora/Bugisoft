package com.beyondbell.bugisoft.old.Utilities.TextFormatters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFormatterTest {
	@Test
	void stringToParametersTest() {
		String[] test;

		// Trailing Spaces Test
		test = ParametersFormatter.stringToParameters("!test ");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("test", test[1]);


		// Normal Use Case Tests
		test = ParametersFormatter.stringToParameters("!test");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("test", test[1]);

		test = ParametersFormatter.stringToParameters("!Test");
		assertEquals(2, test.length);
		assertEquals("!", test[0]);
		assertEquals("Test", test[1]);

		test = ParametersFormatter.stringToParameters("! test");
		assertEquals(3, test.length);
		assertEquals("!", test[0]);
		assertEquals("", test[1]);
		assertEquals("test", test[2]);

		test = ParametersFormatter.stringToParameters("!test1 test2");
		assertEquals(3, test.length);
		assertEquals("!", test[0]);
		assertEquals("test1", test[1]);
		assertEquals("test2", test[2]);
	}
}
