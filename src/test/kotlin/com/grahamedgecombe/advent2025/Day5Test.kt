package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day5.Day5
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun testPart1() {
        assertEquals(3, Day5.solvePart1(TEST_INPUT))
        assertEquals(513, Day5.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(14, Day5.solvePart2(TEST_INPUT))
        assertEquals(339668510830757, Day5.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day5.parse("""
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent())
        private val PROD_INPUT = Day5.parse()
    }
}
