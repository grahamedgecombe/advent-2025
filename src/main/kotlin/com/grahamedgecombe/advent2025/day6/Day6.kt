package com.grahamedgecombe.advent2025.day6

import com.grahamedgecombe.advent2025.Puzzle

object Day6 : Puzzle<Day6.Input>(6) {
    enum class Op { Add, Multiply }
    data class Input(val rows: List<List<Long>>, val ops: List<Op>)

    private val WS = Regex("\\s+")

    override fun parse(input: Sequence<String>): Input {
        val lines = input.toList()
        require(lines.isNotEmpty())

        val rows = buildList {
            for (line in lines.subList(0, lines.size - 1)) {
                add(line.trim().split(WS).map(String::toLong))
            }
        }

        val ops = lines.last().trim().split(WS).map { str ->
            when (str) {
                "+" -> Op.Add
                "*" -> Op.Multiply
                else -> throw IllegalArgumentException()
            }
        }

        return Input(rows, ops)
    }

    override fun solvePart1(input: Input): Long {
        return input.ops.withIndex().sumOf { (i, op) ->
            when (op) {
                Op.Add -> input.rows.fold(0L) { acc, row -> acc + row[i] }
                Op.Multiply -> input.rows.fold(1L) { acc, row -> acc * row[i] }
            }
        }
    }
}
