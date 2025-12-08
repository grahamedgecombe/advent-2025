package com.grahamedgecombe.advent2025.day8

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.UnsolvableException
import com.grahamedgecombe.advent2025.util.DisjointSet
import com.grahamedgecombe.advent2025.util.ForestDisjointSet
import com.grahamedgecombe.advent2025.util.Vector3l
import kotlin.collections.sortedDescending

object Day8 : Puzzle<List<Vector3l>>(8) {
    override fun parse(input: Sequence<String>): List<Vector3l> {
        return input.map { line ->
            val (x, y, z) = line.split(',', limit = 3)
            Vector3l(x.toLong(), y.toLong(), z.toLong())
        }.toList()
    }

    private data class Connection(val u: Vector3l, val v: Vector3l, val distance: Long)

    override fun solvePart1(input: List<Vector3l>): Long {
        return solve(input, 1000)
    }

    override fun solvePart2(input: List<Vector3l>): Long {
        return solve(input, null)
    }

    fun solve(input: List<Vector3l>, n: Int?): Long {
        val disjointSet = ForestDisjointSet<Vector3l>()
        for (v in input) {
            disjointSet.add(v)
        }

        var connections = buildList {
            for ((i, u) in input.withIndex()) {
                for (v in input.subList(0, i)) {
                    add(Connection(u, v, u.euclideanDistanceSquared(v)))
                }
            }

            sortBy(Connection::distance)
        }

        if (n != null) {
            connections = connections.subList(0, n)
        }

        for (connection in connections) {
            val u = disjointSet[connection.u]!!
            val v = disjointSet[connection.v]!!
            disjointSet.union(u, v)

            if (n == null && disjointSet.partitions == 1) {
                return connection.u.x * connection.v.x
            }
        }

        if (n == null) {
            throw UnsolvableException()
        }

        return disjointSet.map(DisjointSet.Partition<Vector3l>::size)
            .sortedDescending()
            .take(3)
            .fold(1, Int::times)
            .toLong()
    }
}
