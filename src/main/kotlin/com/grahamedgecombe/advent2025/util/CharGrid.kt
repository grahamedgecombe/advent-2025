package com.grahamedgecombe.advent2025.util

class CharGrid private constructor(
    private val tiles: CharArray,
    val width: Int,
    val height: Int,
    private val default: Char,
) : Iterable<Pair<Vector2, Char>> {
    constructor(grid: CharGrid) : this(grid.tiles.copyOf(), grid.width, grid.height, grid.default)
    constructor(width: Int, height: Int, default: Char) : this(CharArray(width * height) {
        default
    }, width, height, default)

    operator fun get(v: Vector2): Char {
        return get(v.x, v.y)
    }

    operator fun get(x: Int, y: Int): Char {
        return if (x in 0 until width && y in 0 until height) {
            tiles[y * width + x]
        } else {
            default
        }
    }

    operator fun set(x: Int, y: Int, c: Char) {
        if (x in 0 until width && y in 0 until height) {
            tiles[y * width + x] = c
        }
    }

    operator fun set(v: Vector2, c: Char) {
        return set(v.x, v.y, c)
    }

    override fun toString(): String {
        val builder = StringBuilder()

        var i = 0
        for (y in 0 until height) {
            for (x in 0 until width) {
                builder.append(tiles[i++])
            }
            builder.append('\n')
        }

        return builder.toString()
    }

    override fun iterator(): Iterator<Pair<Vector2, Char>> {
        return iterator {
            var i = 0
            for (y in 0 until height) {
                for (x in 0 until width) {
                    yield(Pair(Vector2(x, y), tiles[i++]))
                }
            }
        }
    }

    companion object {
        fun parse(lines: List<String>, default: Char): CharGrid {
            require(lines.isNotEmpty())

            val height = lines.size
            val width = lines.first().length

            val tiles = CharArray(width * height)

            var i = 0
            for (line in lines) {
                require(line.length == width)

                for (c in line) {
                    tiles[i++] = c
                }
            }

            return CharGrid(tiles, width, height, default)
        }
    }
}
