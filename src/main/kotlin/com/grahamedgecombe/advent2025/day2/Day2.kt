package com.grahamedgecombe.advent2025.day2

import com.grahamedgecombe.advent2025.Puzzle

object Day2 : Puzzle<List<LongRange>>(2) {
    override fun parse(input: Sequence<String>): List<LongRange> {
        return input.single().splitToSequence(',').map { str ->
            val (start, end) = str.split('-', limit = 2)
            return@map start.toLong()..end.toLong()
        }.toList()
    }

    override fun solvePart1(input: List<LongRange>): Long {
        return input.sumOf { range ->
            range.filter { id ->
                id.isInvalidPart1()
            }.sum()
        }
    }

    override fun solvePart2(input: List<LongRange>): Long {
        return input.sumOf { range ->
            range.filter { id ->
                id.isInvalidPart2()
            }.sum()
        }
    }

    private fun Long.isInvalidPart1(): Boolean {
        val str = toString()
        if (str.length % 2 != 0) {
            return false
        }

        val midpoint = str.length / 2
        return str.substring(0, midpoint) == str.substring(midpoint, str.length)
    }

    private fun Long.isInvalidPart2(): Boolean {
        val str = toString()

        next@for (n in 1 .. str.length / 2) {
            if (str.length % n != 0) {
                continue
            }

            val first = str.substring(0, n)
            for (i in n until str.length step n) {
                if (str.substring(i, i + n) != first) {
                    continue@next
                }
            }

            return true
        }

        return false
    }
}
