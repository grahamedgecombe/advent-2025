package com.grahamedgecombe.advent2025.util

data class Vector3l(val x: Long, val y: Long, val z: Long) {
    operator fun plus(v: Vector3l): Vector3l {
        return Vector3l(x + v.x, y + v.y, z + v.z)
    }

    operator fun minus(v: Vector3l): Vector3l {
        return Vector3l(x - v.x, y - v.y, z - v.z)
    }

    fun euclideanDistanceSquared(v: Vector3l): Long {
        return (x - v.x) * (x - v.x) + (y - v.y) * (y - v.y) + (z - v.z) * (z - v.z)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }
}
