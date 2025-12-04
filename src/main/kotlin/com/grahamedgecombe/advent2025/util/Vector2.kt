package com.grahamedgecombe.advent2025.util

data class Vector2(val x: Int, val y: Int) {
    operator fun plus(v: Vector2): Vector2 {
        return Vector2(x + v.x, y + v.y)
    }

    operator fun minus(v: Vector2): Vector2 {
        return Vector2(x - v.x, y - v.y)
    }

    operator fun times(n: Int): Vector2 {
        return Vector2(x * n, y * n)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    companion object {
        val NEIGHBOURS = buildSet {
            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dx != 0 || dy != 0) {
                        add(Vector2(dx, dy))
                    }
                }
            }
        }
    }
}
