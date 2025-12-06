package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day6.Day6
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun testPart1() {
        assertEquals(4277556, Day6.solvePart1(TEST_INPUT))
        assertEquals(5877594983578, Day6.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day6.parse("""
            123 328  51 64 
             45 64  387 23 
              6 98  215 314
            *   +   *   +  
        """.trimIndent())
        private val PROD_INPUT = Day6.parse()
    }
}
