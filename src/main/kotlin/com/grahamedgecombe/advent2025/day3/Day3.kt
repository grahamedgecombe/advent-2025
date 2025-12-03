package com.grahamedgecombe.advent2025.day3

import com.grahamedgecombe.advent2025.Puzzle

object Day3 : Puzzle<List<List<Int>>>(3) {
    override fun parse(input: Sequence<String>): List<List<Int>> {
        return input.map { bank ->
            bank.map { battery ->
                val joltage = battery.digitToInt()
                require(joltage in 1..9)
                joltage
            }.toList()
        }.toList()
    }

    override fun solvePart1(input: List<List<Int>>): Int {
        return input.sumOf { bank ->
            maxJoltage(bank)
        }
    }

    private fun maxJoltage(bank: List<Int>, on: List<Int> = emptyList()): Int {
        if (on.size == 2) {
            return on[0] * 10 + on[1]
        } else if (on.size > 2) {
            throw IllegalArgumentException()
        }

        if (bank.isEmpty()) {
            return Int.MIN_VALUE
        }

        val battery = bank[0]
        val remaining = bank.subList(1, bank.size)

        return maxOf(
            maxJoltage(remaining, on + battery),
            maxJoltage(remaining, on),
        )
    }
}
