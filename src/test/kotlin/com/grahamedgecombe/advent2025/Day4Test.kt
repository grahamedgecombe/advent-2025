package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day4.Day4
import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {
    @Test
    fun testPart1() {
        assertEquals(13, Day4.solvePart1(TEST_INPUT))
        assertEquals(1419, Day4.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day4.parse("""
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent())
        private val PROD_INPUT = Day4.parse()
    }
}
