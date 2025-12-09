package com.grahamedgecombe.advent2025.util

data class Vector2l(val x: Long, val y: Long) {
    operator fun plus(v: Vector2l): Vector2l {
        return Vector2l(x + v.x, y + v.y)
    }

    operator fun minus(v: Vector2l): Vector2l {
        return Vector2l(x - v.x, y - v.y)
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
