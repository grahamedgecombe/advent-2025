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
                id.isInvalid()
            }.sum()
        }
    }

    private fun Long.isInvalid(): Boolean {
        val str = toString()
        if (str.length % 2 != 0) {
            return false
        }

        val midpoint = str.length / 2
        return str.substring(0, midpoint) == str.substring(midpoint, str.length)
    }
}
