package com.grahamedgecombe.advent2025.day9

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.util.Vector2l
import kotlin.collections.isNotEmpty
import kotlin.collections.withIndex
import kotlin.math.abs
import kotlin.math.max

object Day9 : Puzzle<List<Vector2l>>(9) {
    override fun parse(input: Sequence<String>): List<Vector2l> {
        return input.map { line ->
            val (x, y) = line.split(',', limit = 2)
            Vector2l(x.toLong(), y.toLong())
        }.toList()
    }

    override fun solvePart1(input: List<Vector2l>): Long {
        require(input.isNotEmpty())

        var maxArea = Long.MIN_VALUE

        for ((i, u) in input.withIndex()) {
            for (v in input.subList(0, i)) {
                val area = (abs(u.x - v.x) + 1) * (abs(u.y - v.y) + 1)
                maxArea = max(maxArea, area)
            }
        }

        return maxArea
    }
}
