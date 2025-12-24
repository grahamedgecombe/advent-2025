package com.grahamedgecombe.advent2025

import com.grahamedgecombe.advent2025.day12.Day12
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun testPart1() {
        assertEquals(2, Day12.solvePart1(TEST_INPUT))
        assertEquals(538, Day12.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT = Day12.parse("""
            0:
            ###
            ##.
            ##.

            1:
            ###
            ##.
            .##

            2:
            .##
            ###
            ##.

            3:
            ##.
            ###
            ##.

            4:
            ###
            #..
            ###

            5:
            ###
            .#.
            ###

            4x4: 0 0 0 0 2 0
            12x5: 1 0 1 0 2 2
            12x5: 1 0 1 0 3 2
        """.trimIndent())
        private val PROD_INPUT = Day12.parse()
    }
}
