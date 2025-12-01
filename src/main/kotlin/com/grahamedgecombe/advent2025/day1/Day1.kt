package com.grahamedgecombe.advent2025.day1

import com.grahamedgecombe.advent2025.Puzzle
import kotlin.math.absoluteValue

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
            dial = (dial + distance) unsignedMod 100

            if (dial == 0) {
                zeroes++
            }
        }

        return zeroes
    }

    override fun solvePart2(input: List<Int>): Int {
        var dial = 50
        var zeroes = 0

        for (distance in input) {
            val next = (dial + distance) unsignedMod 100

            zeroes += distance.absoluteValue / 100

            if (next == 0) {
                zeroes++
            } else {
                val crossedZero = dial != 0 && if (distance > 0) {
                    next < dial
                } else {
                    next > dial
                }

                if (crossedZero) {
                    zeroes++
                }
            }

            dial = next
        }

        return zeroes
    }

    private infix fun Int.unsignedMod(divisor: Int): Int {
        var result = this % divisor
        if (result < 0) {
            result += divisor
        }
        return result
    }
}
