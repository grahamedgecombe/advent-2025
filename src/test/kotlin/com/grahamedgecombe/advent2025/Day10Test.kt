package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day10.Day10
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun testPart1() {
        assertEquals(7, Day10.solvePart1(TEST_INPUT))
        assertEquals(475, Day10.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(33, Day10.solvePart2(TEST_INPUT))
        assertEquals(18273, Day10.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day10.parse("""
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
        """.trimIndent())
        private val PROD_INPUT = Day10.parse()
    }
}
