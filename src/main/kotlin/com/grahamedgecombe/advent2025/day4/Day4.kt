package com.grahamedgecombe.advent2025.day4

import com.grahamedgecombe.advent2025.Puzzle
import com.grahamedgecombe.advent2025.util.CharGrid
import com.grahamedgecombe.advent2025.util.Vector2

object Day4 : Puzzle<CharGrid>(4) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '.')
    }

    override fun solvePart1(input: CharGrid): Int {
        return input.count { (position, tile) ->
            if (tile != '@') {
                return@count false
            }

            val rolls = Vector2.NEIGHBOURS.count { v -> input[position + v] == '@' }
            return@count rolls < 4
        }
    }
}
