package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day11.Day11
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun testPart1() {
        assertEquals(5, Day11.solvePart1(TEST_INPUT_1))
        assertEquals(749, Day11.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(2, Day11.solvePart2(TEST_INPUT_2))
        assertEquals(420257875695750, Day11.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day11.parse("""
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
        private val TEST_INPUT_2 = Day11.parse("""
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out
        """.trimIndent())
        private val PROD_INPUT = Day11.parse()
    }
}
