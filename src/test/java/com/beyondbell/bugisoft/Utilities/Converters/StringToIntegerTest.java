package com.beyondbell.bugisoft.Utilities.Converters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringToIntegerTest {
	@Test
	void stringToIntegerTest() {
		int integer = 0;

		// Non-Number Tests
		boolean threwError = false;
		try {
			integer = StringToInteger.convert("hello");
		} catch (Throwable throwable) {
			threwError = true;
		}
		assertTrue(threwError);


		// Normal Use Case Tests
		try {
			integer = StringToInteger.convert("1");
		} catch (Throwable ignored) {

		}
		assertEquals(1, integer);

		try {
			integer = StringToInteger.convert("29");
		} catch (Throwable ignored) {

		}
		assertEquals(29, integer);

		try {
			integer = StringToInteger.convert("234");
		} catch (Throwable ignored) {

		}
		assertEquals(234, integer);

		try {
			integer = StringToInteger.convert("9145");
		} catch (Throwable ignored) {

		}
		assertEquals(9145, integer);
	}
}
