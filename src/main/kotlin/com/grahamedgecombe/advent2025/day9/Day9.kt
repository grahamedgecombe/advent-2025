package com.grahamedgecombe.advent2025.day9

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.util.Vector2l
import kotlin.collections.isNotEmpty
import kotlin.collections.withIndex
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

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

    override fun solvePart2(input: List<Vector2l>): Long {
        require(input.isNotEmpty())

        var maxArea = Long.MIN_VALUE
        val polygon = buildList {
            for (i in 0 until input.size) {
                add(Rectangle.create(input[i], input[(i + 1) % input.size]))
            }
        }

        for ((i, u) in input.withIndex()) {
            for (v in input.subList(0, i)) {
                val rect = Rectangle.create(u, v)

                if (!rect.corners.all { corner -> insidePolygon(polygon, corner) }) {
                    continue
                }

                if (polygon.any { line -> rect.intersects(line) }) {
                    continue
                }

                maxArea = max(maxArea, rect.area)
            }
        }

        return maxArea
    }

    private data class Rectangle(val min: Vector2l, val max: Vector2l) {
        fun intersects(other: Rectangle): Boolean {
            return this.min.x < other.max.x &&
                    this.max.x > other.min.x &&
                    this.min.y < other.max.y &&
                    this.max.y > other.min.y
        }

        val corners: Sequence<Vector2l>
            get() = sequenceOf(
                Vector2l(min.x, min.y),
                Vector2l(min.x, max.y),
                Vector2l(max.x, min.y),
                Vector2l(max.x, max.y),
            )

        val area: Long
            get() = (max.x - min.x + 1) * (max.y - min.y + 1)

        companion object {
            fun create(u: Vector2l, v: Vector2l): Rectangle {
                return Rectangle(
                    Vector2l(min(u.x, v.x), min(u.y, v.y)),
                    Vector2l(max(u.x, v.x), max(u.y, v.y)),
                )
            }
        }
    }

    private fun insidePolygon(polygon: List<Rectangle>, v: Vector2l): Boolean {
        var inside = false

        for (line in polygon) {
            val a = line.min
            val b = line.max

            if (v.x == a.x && v.y == a.y) {
                return true
            }

            if ((a.y > v.y) != (b.y > v.y)) {
                val slope = (v.x - a.x) * (v.y - a.y) - (b.x - a.x) * (v.y - a.y)
                if (slope == 0L) {
                    return true
                }

                if ((slope < 0) != (b.y < a.y)) {
                    inside = !inside
                }
            }
        }

        return inside
    }
}
