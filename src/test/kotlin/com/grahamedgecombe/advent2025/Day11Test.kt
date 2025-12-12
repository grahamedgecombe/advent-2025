package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day11.Day11
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun testPart1() {
        assertEquals(5, Day11.solvePart1(TEST_INPUT))
        assertEquals(749, Day11.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day11.parse("""
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
        """.trimIndent())
        private val PROD_INPUT = Day11.parse()
    }
}
