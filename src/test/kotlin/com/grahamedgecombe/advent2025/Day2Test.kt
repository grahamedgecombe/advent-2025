package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day2.Day2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun testPart1() {
        assertEquals(1227775554, Day2.solvePart1(TEST_INPUT))
        assertEquals(32976912643, Day2.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day2.parse("""
            11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
        """.trimIndent())
        private val PROD_INPUT = Day2.parse()
    }
}
