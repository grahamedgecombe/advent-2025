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

    override fun solvePart1(input: List<List<Int>>): Long {
        return input.sumOf { bank ->
            maxJoltage(bank, 2)
        }
    }

    override fun solvePart2(input: List<List<Int>>): Long {
        return input.sumOf { bank ->
            maxJoltage(bank, 12)
        }
    }

    private fun maxJoltage(bank: List<Int>, n: Int, on: List<Int> = emptyList()): Long {
        if (on.size == n) {
            var result = 0L
            for (digit in on) {
                result = (result * 10) + digit
            }
            return result
        } else if (on.size > n) {
            throw IllegalArgumentException()
        }

        if (bank.isEmpty()) {
            return Long.MIN_VALUE
        }

        for (joltage in 9 downTo 1) {
            val index = bank.indexOf(joltage)
            if (index == -1) {
                continue
            }

            val max = maxJoltage(bank.subList(index + 1, bank.size), n, on + joltage)
            if (max != Long.MIN_VALUE) {
                return max
            }
        }

        return maxJoltage(bank.subList(1, bank.size), n, on)
    }
}
