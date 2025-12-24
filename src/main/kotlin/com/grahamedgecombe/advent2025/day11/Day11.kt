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

    override fun solvePart1(input: Map<String, List<String>>): Long {
        return countPaths(input, "you", "out")
    }

    override fun solvePart2(input: Map<String, List<String>>): Long {
        val svrToFft = countPaths(input, "svr", "fft")
        val fftToDac = countPaths(input, "fft", "dac")
        val dacToOut = countPaths(input, "dac", "out")

        val svrToDac = countPaths(input, "svr", "dac")
        val dacToFft = countPaths(input, "dac", "fft")
        val fftToOut = countPaths(input, "fft", "out")

        return (svrToFft * fftToDac * dacToOut) + (svrToDac * dacToFft * fftToOut)
    }

    private data class Key(
        val current: String,
        val goal: String,
    )

    private fun countPaths(
        graph: Map<String, List<String>>,
        current: String,
        goal: String,
        cache: MutableMap<Key, Long> = mutableMapOf(),
    ): Long {
        if (current == goal) {
            return 1
        }

        val key = Key(current, goal)

        var count = cache[key]
        if (count != null) {
            return count
        }

        count = graph.getOrDefault(current, emptyList()).sumOf { neighbour ->
            countPaths(graph, neighbour, goal, cache)
        }
        cache[key] = count

        return count
    }
}
