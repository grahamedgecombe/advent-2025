package com.grahamedgecombe.advent2025.day1

import com.grahamedgecombe.advent2025.Puzzle

object Day1 : Puzzle<List<Int>>(1) {
    override fun parse(input: Sequence<String>): List<Int> {
        return input.map { line ->
            require(!line.isEmpty())

            var distance = line.substring(1).toInt()

            val direction = line.first()
            if (direction == 'L') {
                distance = -distance
            } else if (direction != 'R') {
                throw IllegalArgumentException()
            }

            return@map distance
        }.toList()
    }

    override fun solvePart1(input: List<Int>): Int {
        var dial = 50
        var zeroes = 0

        for (distance in input) {
            dial = (dial + distance) % 100
            if (dial < 0) {
                dial += 100
            }

            if (dial == 0) {
                zeroes++
            }
        }

        return zeroes
    }
}
