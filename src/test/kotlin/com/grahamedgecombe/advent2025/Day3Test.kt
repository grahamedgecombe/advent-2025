package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day3.Day3
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun testPart1() {
        assertEquals(357, Day3.solvePart1(TEST_INPUT))
        assertEquals(17092, Day3.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(3121910778619, Day3.solvePart2(TEST_INPUT))
        assertEquals(170147128753455, Day3.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day3.parse("""
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent())
        private val PROD_INPUT = Day3.parse()
    }
}
