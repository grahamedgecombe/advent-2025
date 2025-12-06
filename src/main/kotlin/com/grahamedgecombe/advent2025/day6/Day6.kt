package com.grahamedgecombe.advent2025.day6

import com.grahamedgecombe.advent2025.Puzzle

object Day6 : Puzzle<List<Day6.Column>>(6) {
    enum class Op { Add, Multiply }
    data class Column(val rows: List<String>, val op: Op)

    override fun parse(input: Sequence<String>): List<Column> {
        val lines = input.toMutableList()
        require(lines.isNotEmpty())

        // restore trailing whitespace as my editor strips it
        val len = lines.maxOf(String::length)
        lines.replaceAll { line -> line.padEnd(len, ' ') }

        // calculate the width of each column
        // we start from the right - as the ops are left-aligned, it makes counting easier
        val widths = mutableListOf<Int>()
        val ops = mutableListOf<Op>()
        var width = 1

        for (c in lines.last().reversed()) {
            width++

            if (c == ' ') {
                continue
            }

            val op = when (c) {
                '+' -> Op.Add
                '*' -> Op.Multiply
                else -> throw IllegalArgumentException()
            }

            widths += width
            ops += op

            width = 0
        }

        widths.reverse()
        ops.reverse()

        // transpose
        var x = 0

        return List(widths.size) { i ->
            val width = widths[i]

            val rows = List(lines.size - 1) { j ->
                lines[j].substring(x, x + width - 1)
            }

            x += width

            return@List Column(rows, ops[i])
        }
    }

    override fun solvePart1(input: List<Column>): Long {
        return input.sumOf { col ->
            val values = col.rows.map { value -> value.trim().toLong() }

            when (col.op) {
                Op.Add -> values.fold(0L, Long::plus)
                Op.Multiply -> values.fold(1L, Long::times)
            }
        }
    }

    override fun solvePart2(input: List<Column>): Long {
        return input.sumOf { col ->
            val numDigits = col.rows.first().length

            val values = buildList {
                for (i in 0 until numDigits) {
                    var value = 0

                    for (row in col.rows) {
                        val digit = row[i]
                        if (digit != ' ') {
                            value = value * 10 + digit.digitToInt()
                        }
                    }

                    add(value)
                }
            }

            when (col.op) {
                Op.Add -> values.fold(0L, Long::plus)
                Op.Multiply -> values.fold(1L, Long::times)
            }
        }
    }
}
