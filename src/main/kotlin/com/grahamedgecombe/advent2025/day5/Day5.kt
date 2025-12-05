package com.grahamedgecombe.advent2025.day5

import com.grahamedgecombe.advent2025.Puzzle

object Day5 : Puzzle<Day5.Input>(5) {
    data class Input(val ranges: List<LongRange>, val ids: List<Long>)

    override fun parse(input: Sequence<String>): Input {
        val ranges = mutableListOf<LongRange>()
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
            input.ranges.any { range ->
                range.contains(id)
            }
        }
    }
}
