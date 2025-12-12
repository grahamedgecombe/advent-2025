package com.grahamedgecombe.advent2025.day11

import com.grahamedgecombe.advent2025.Puzzle

object Day11 : Puzzle<Map<String, List<String>>>(11) {
    override fun parse(input: Sequence<String>): Map<String, List<String>> {
        return buildMap {
            for (line in input) {
                val (device, outputs) = line.split(": ", limit = 2)
                this[device] = outputs.split(' ')
            }
        }
    }

    override fun solvePart1(input: Map<String, List<String>>): Int {
        return countPaths(input, "you", emptySet())
    }

    private fun countPaths(graph: Map<String, List<String>>, current: String, seen: Set<String>): Int {
        if (current == "out") {
            return 1
        } else if (current in seen) {
            return 0
        }

        return graph.getOrDefault(current, emptyList()).sumOf { neighbour ->
            countPaths(graph, neighbour, seen + current)
        }
    }
}
