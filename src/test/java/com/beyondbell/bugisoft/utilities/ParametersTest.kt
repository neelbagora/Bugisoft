package com.beyondbell.bugisoft.utilities

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

object ParametersTest {
    @Test
    fun getParametersTest() {
        // Normal Use Case Tests
        var test = getParameters("!test")
        assertEquals(2, test.size)
        assertEquals("!", test[0])
        assertEquals("test", test[1])

        test = getParameters("!Test")
        assertEquals(2, test.size)
        assertEquals("!", test[0])
        assertEquals("Test", test[1])

        test = getParameters("! test")
        assertEquals(3, test.size)
        assertEquals("!", test[0])
        assertEquals("", test[1])
        assertEquals("test", test[2])

        test = getParameters("!test1 test2")
        assertEquals(3, test.size)
        assertEquals("!", test[0])
        assertEquals("test1", test[1])
        assertEquals("test2", test[2])

        // Trailing Spaces Test
        test = getParameters("!test ")
        assertEquals(2, test.size)
        assertEquals("!", test[0])
        assertEquals("test", test[1])
    }
}