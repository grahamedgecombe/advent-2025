package com.grahamedgecombe.advent2025.util

import java.util.TreeSet
import kotlin.math.max
import kotlin.math.min

class RangeSet : Iterable<LongRange> {
    private val set = TreeSet(compareBy(LongRange::first).thenBy(LongRange::last))

    operator fun plusAssign(range: LongRange) {
        var range = range

        val before = firstRangeBefore(range.first)
        if (before != null && range.overlaps(before)) {
            range = range.merge(before)
            set -= before
        }

        set.tailSet(range).removeIf { other ->
            if (range.overlaps(other)) {
                range = range.merge(other)
                return@removeIf true
            } else {
                return@removeIf false
            }
        }

        set += range
    }

    operator fun contains(value: Long): Boolean {
        val before = firstRangeBefore(value)
        return before != null && value in before
    }

    override fun iterator(): Iterator<LongRange> {
        return set.iterator()
    }

    private fun firstRangeBefore(value: Long): LongRange? {
        return set.floor(LongRange(value, Long.MAX_VALUE))
    }

    private fun LongRange.overlaps(other: LongRange): Boolean {
        return first <= other.last && other.first <= last
    }

    private fun LongRange.merge(other: LongRange): LongRange {
        return LongRange(min(first, other.first), max(last, other.last))
    }
}
