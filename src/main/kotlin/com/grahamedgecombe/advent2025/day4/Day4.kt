package com.grahamedgecombe.advent2025.day4

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.util.CharGrid
import com.grahamedgecombe.advent2025.util.Vector2

object Day4 : Puzzle<CharGrid>(4) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '.')
    }

    override fun solvePart1(input: CharGrid): Int {
        return input.count { (position, tile) -> isAccessible(input, position, tile) }
    }

    override fun solvePart2(input: CharGrid): Int {
        var removed = 0

        while (true) {
            val tiles = input.filter { (position, tile) -> isAccessible(input, position, tile) }
            if (tiles.isEmpty()) {
                break
            }

            for ((position, _) in tiles) {
                input[position] = '.'
                removed++
            }
        }

        return removed
    }

    private fun isAccessible(grid: CharGrid, position: Vector2, tile: Char): Boolean {
        if (tile != '@') {
            return false
        }

        val rolls = Vector2.NEIGHBOURS.count { v -> grid[position + v] == '@' }
        return rolls < 4
    }
}
