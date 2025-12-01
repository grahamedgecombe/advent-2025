package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day1.Day1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun testPart1() {
        assertEquals(3, Day1.solvePart1(TEST_INPUT_1))
        assertEquals(1147, Day1.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(6, Day1.solvePart2(TEST_INPUT_1))
        assertEquals(10, Day1.solvePart2(TEST_INPUT_2))
        assertEquals(6789, Day1.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day1.parse("""
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent())
        private val TEST_INPUT_2 = Day1.parse("""
            R1000
        """.trimIndent())
        private val PROD_INPUT = Day1.parse()
    }
}
