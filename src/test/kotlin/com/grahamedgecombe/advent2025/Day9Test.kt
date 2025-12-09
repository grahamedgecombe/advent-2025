package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day9.Day9
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun testPart1() {
        assertEquals(50, Day9.solvePart1(TEST_INPUT))
        assertEquals(4782896435, Day9.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(24, Day9.solvePart2(TEST_INPUT))
        assertEquals(1540060480, Day9.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day9.parse("""
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent())
        private val PROD_INPUT = Day9.parse()
    }
}
