package com.grahamedgecombe.advent2025.day10

import com.google.common.collect.Sets
import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.UnsolvableException
import com.microsoft.z3.Context
import com.microsoft.z3.IntExpr
import com.microsoft.z3.IntNum
import com.microsoft.z3.Status

object Day10 : Puzzle<List<Day10.Machine>>(10) {
    override fun parse(input: Sequence<String>): List<Machine> {
        return input.map(Machine::parse).toList()
    }

    override fun solvePart1(input: List<Machine>): Int {
        return input.sumOf(Machine::minPressesPart1)
    }

    override fun solvePart2(input: List<Machine>): Int {
        return input.sumOf(Machine::minPressesPart2)
    }

    data class Machine(
        val desiredIndicators: Int,
        val buttons: Set<Int>,
        val joltages: List<Int>
    ) {
        fun minPressesPart1(): Int {
            for (i in 0 until buttons.size) {
                for (combination in Sets.combinations(buttons, i)) {
                    if (combination.fold(0, Int::xor) == desiredIndicators) {
                        return i
                    }
                }
            }

            throw UnsolvableException()
        }

        fun minPressesPart2(): Int {
            Context().use { ctx ->
                val optimize = ctx.mkOptimize()

                val counts = buttons.indices.map { i ->
                    ctx.mkIntConst("count$i")
                }
                for (count in counts) {
                    optimize.Add(ctx.mkGe(count, ctx.mkInt(0)))
                }

                for ((i, joltage) in joltages.withIndex()) {
                    val terms = mutableListOf<IntExpr>()

                    for ((j, button) in buttons.withIndex()) {
                        if (button and (1 shl i) != 0) {
                            terms += counts[j]
                        }
                    }

                    optimize.Add(ctx.mkEq(ctx.mkAdd(*terms.toTypedArray()), ctx.mkInt(joltage)))
                }

                val handle = optimize.MkMinimize(ctx.mkAdd(*counts.toTypedArray()))

                if (optimize.Check() != Status.SATISFIABLE) {
                    throw UnsolvableException()
                }

                val value = handle.value
                if (value !is IntNum) {
                    throw UnsolvableException()
                }
                return value.int
            }
        }

        companion object {
            fun parse(line: String): Machine {
                val parts = line.split(' ')
                require(parts.size >= 3)

                val desiredIndicators = parseIndicators(parts.first())
                val joltages = parseInts(parts.last().removeSurrounding("{", "}"))

                val buttons = buildSet {
                    for (str in parts.subList(1, parts.size - 1)) {
                        add(parseButtons(str))
                    }
                }

                return Machine(desiredIndicators, buttons, joltages)
            }

            private fun parseInts(str: String): List<Int> {
                return str.split(',').map(String::toInt).toList()
            }

            private fun parseIndicators(str: String): Int {
                val str = str.removeSurrounding("[", "]")

                var desiredIndicators = 0

                for ((i, c) in str.withIndex()) {
                    if (c == '#') {
                        desiredIndicators = desiredIndicators or (1 shl i)
                    } else if (c != '.') {
                        throw IllegalArgumentException()
                    }
                }

                return desiredIndicators
            }

            private fun parseButtons(str: String): Int {
                val str = str.removeSurrounding("(", ")")

                var buttons = 0
                for (i in parseInts(str)) {
                    buttons = buttons or (1 shl i)
                }
                return buttons
            }
        }
    }
}
