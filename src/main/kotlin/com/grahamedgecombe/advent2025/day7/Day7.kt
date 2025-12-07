package com.grahamedgecombe.advent2025.day7

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.UnsolvableException
import com.grahamedgecombe.advent2025.util.CharGrid
import com.grahamedgecombe.advent2025.util.Vector2

object Day7 : Puzzle<CharGrid>(7) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '.')
    }

    override fun solvePart1(input: CharGrid): Int {
        val start = input.find('S') ?: throw UnsolvableException()
        return countSplits(input, start)
    }

    private fun countSplits(grid: CharGrid, position: Vector2, seen: MutableSet<Vector2> = mutableSetOf()): Int {
        if (position.y >= grid.height) {
            return 0
        } else if (!seen.add(position)) {
            return 0
        }

        val tile = grid[position]
        return if (tile == '^') {
            countSplits(grid, position + Vector2.LEFT, seen) + countSplits(grid, position + Vector2.RIGHT, seen) + 1
        } else {
            countSplits(grid, position + Vector2.DOWN, seen)
        }
    }
}
