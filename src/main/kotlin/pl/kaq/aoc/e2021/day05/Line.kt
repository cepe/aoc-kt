package pl.kaq.aoc.e2021.day05

import kotlin.math.abs
import kotlin.math.max

class Line(private val from: Point, private val to: Point) {
    fun isDiagonal(): Boolean {
        val (x1, y1) = from
        val (x2, y2) = to
        return (x1 != x2) and (y1 != y2)
    }

    fun points(): List<Point> {
        val (x1, y1) = from
        val (x2, y2) = to

        val xStep = step(x1, x2)
        val yStep = step(y1, y2)

        return (0..max(abs(x1 - x2), abs(y1 - y2)))
            .map { Point(x1 + xStep * it, y1 + yStep * it) }
    }

    private fun step(a: Int, b: Int): Int {
        if (b == a) return 0
        return if (a < b) 1 else -1
    }

}