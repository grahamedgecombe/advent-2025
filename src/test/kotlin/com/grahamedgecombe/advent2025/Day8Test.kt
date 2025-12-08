package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day8.Day8
import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun testPart1() {
        assertEquals(40, Day8.solvePart1(TEST_INPUT, 10))
        assertEquals(75680, Day8.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day8.parse("""
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
            542,29,236
            431,825,988
            739,650,466
            52,470,668
            216,146,977
            819,987,18
            117,168,530
            805,96,715
            346,949,466
            970,615,88
            941,993,340
            862,61,35
            984,92,344
            425,690,689
        """.trimIndent())
        private val PROD_INPUT = Day8.parse()
    }
}
