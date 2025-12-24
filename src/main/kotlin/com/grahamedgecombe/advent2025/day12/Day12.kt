package com.grahamedgecombe.advent2025.day12

import com.grahamedgecombe.advent2025.Puzzle
import java.util.BitSet

object Day12 : Puzzle<Pair<List<Day12.Shape>, List<Day12.Region>>>(12) {
    override fun parse(input: Sequence<String>): Pair<List<Shape>, List<Region>> {
        val iterator = input.iterator()

        val shapes = List(6) {
            val pixels = buildList {
                iterator.next() // skip header

                while (iterator.hasNext()) {
                    val line = iterator.next()
                    if (line.isEmpty()) {
                        break
                    }

                    add(line.map { c -> c == '#' }.toList())
                }
            }

            Shape(pixels)
        }

        val regions = buildList {
            while (iterator.hasNext()) {
                val (size, str) = iterator.next().split(": ", limit = 2)

                val (width, height) = size.split('x', limit = 2)
                val counts = str.split(' ').map(String::toInt).toList()

                add(Region(width.toInt(), height.toInt(), counts))
            }
        }

        return Pair(shapes, regions)
    }

    override fun solvePart1(input: Pair<List<Shape>, List<Region>>): Int {
        val (shapes, regions) = input

        return regions.count { region ->
            val requiredPixels = region.counts.withIndex().sumOf { (i, count) -> shapes[i].units * count }
            val pixelCapacity = region.width * region.height

            if (requiredPixels > pixelCapacity) {
                return@count false
            }

            val requiredShapes = region.counts.sum() * Shape.SIZE * Shape.SIZE
            val shapeCapacity = pixelCapacity / (Shape.SIZE * Shape.SIZE)

            if (requiredShapes <= shapeCapacity) {
                return@count true
            }

            val grid = Grid(region.width, region.height)
            val seen = mutableSetOf<Grid>()
            val queue = ArrayDeque<Shape>()

            for ((i, count) in region.counts.withIndex()) {
                val shape = shapes[i]
                for (j in 0 until count) {
                    queue += shape
                }
            }

            return@count isValid(grid, seen, queue)
        }
    }

    private fun isValid(grid: Grid, seen: MutableSet<Grid>, queue: ArrayDeque<Shape>): Boolean {
        if (queue.isEmpty()) {
            return true
        } else if (grid in seen) {
            return false
        }

        // copy grid as we mutate it
        seen += Grid(grid)

        val shape = queue.removeFirst()

        for (transformed in shape.transformations()) {
            for (y in 0 until grid.height - Shape.SIZE + 1) {
                for (x in 0 until grid.width - Shape.SIZE + 1) {
                    if (grid.overlaps(x, y, transformed)) {
                        continue
                    }

                    grid.add(x, y, transformed)

                    if (isValid(grid, seen, queue)) {
                        return true
                    }

                    grid.remove(x, y, transformed)
                }
            }
        }

        queue.addLast(shape)
        return false
    }

    class Grid(val width: Int, val height: Int) {
        constructor(other: Grid) : this(other.width, other.height) {
            for (i in 0 until other.pixels.length()) {
                pixels[i] = other.pixels[i]
            }
        }

        private val pixels = BitSet()

        operator fun get(x: Int, y: Int): Boolean {
            return if (x in 0 until width && y in 0 until height) {
                pixels[y * width + x]
            } else {
                false
            }
        }

        operator fun set(x: Int, y: Int, value: Boolean) {
            require(x in 0 until width && y in 0 until height)
            pixels[y * width + x] = value
        }

        fun overlaps(x: Int, y: Int, shape: Shape): Boolean {
            for (dy in 0 until Shape.SIZE) {
                for (dx in 0 until Shape.SIZE) {
                    if (shape[dx, dy] && this[x + dx, y + dy]) {
                        return true
                    }
                }
            }

            return false
        }

        fun add(x: Int, y: Int, shape: Shape) {
            for (dy in 0 until Shape.SIZE) {
                for (dx in 0 until Shape.SIZE) {
                    if (shape[dx, dy]) {
                        this[x + dx, y + dy] = true
                    }
                }
            }
        }

        fun remove(x: Int, y: Int, shape: Shape) {
            for (dy in 0 until Shape.SIZE) {
                for (dx in 0 until Shape.SIZE) {
                    if (shape[dx, dy]) {
                        this[x + dx, y + dy] = false
                    }
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Grid

            if (width != other.width) return false
            if (height != other.height) return false
            if (pixels != other.pixels) return false

            return true
        }

        override fun hashCode(): Int {
            var result = width
            result = 31 * result + height
            result = 31 * result + pixels.hashCode()
            return result
        }
    }

    data class Shape(val pixels: List<List<Boolean>>) {
        init {
            require(pixels.size == SIZE)
            require(pixels.all { line -> line.size == SIZE })
        }

        val units = pixels.sumOf { line ->
            line.count { pixel ->
                pixel
            }
        }

        operator fun get(x: Int, y: Int): Boolean {
            require(x in 0 until SIZE && y in 0 until SIZE)
            return pixels[y][x]
        }

        fun flip(): Shape {
            val newPixels = List(SIZE) { y ->
                List(SIZE) { x ->
                    pixels[y][SIZE - x - 1]
                }
            }
            return Shape(newPixels)
        }

        fun rotate(): Shape {
            val newPixels = List(SIZE) { y ->
                List(SIZE) { x ->
                    pixels[x][SIZE - y - 1]
                }
            }
            return Shape(newPixels)
        }

        fun transformations(): Set<Shape> {
            var current = this

            return buildSet {
                for (i in 0 until 2) {
                    for (j in 0 until 4) {
                        add(current)

                        current = current.rotate()
                    }

                    current = current.flip()
                }
            }
        }

        override fun toString(): String {
            val builder = StringBuilder()
            for (line in pixels) {
                for (pixel in line) {
                    builder.append(if (pixel) '#' else '.')
                }
                builder.append('\n')
            }
            return builder.toString()
        }

        companion object {
            const val SIZE = 3
        }
    }

    data class Region(val width: Int, val height: Int, val counts: List<Int>)
}
