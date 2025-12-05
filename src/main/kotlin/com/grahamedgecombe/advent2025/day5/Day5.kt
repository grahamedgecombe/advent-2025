package com.grahamedgecombe.advent2025.day5

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.util.RangeSet

object Day5 : Puzzle<Day5.Input>(5) {
    data class Input(val ranges: RangeSet, val ids: List<Long>)

    override fun parse(input: Sequence<String>): Input {
        val ranges = RangeSet()
        val ids = mutableListOf<Long>()

        val it = input.iterator()
        while (it.hasNext()) {
            val line = it.next()
            if (line.isEmpty()) {
                break
            }

            val (start, end) = line.split('-', limit = 2)
            ranges += LongRange(start.toLong(), end.toLong())
        }

        while (it.hasNext()) {
            ids += it.next().toLong()
        }

        return Input(ranges, ids)
    }

    override fun solvePart1(input: Input): Int {
        return input.ids.count { id ->
            id in input.ranges
        }
    }

    override fun solvePart2(input: Input): Long {
        return input.ranges.sumOf { range ->
            range.last - range.first + 1
        }
    }
}
