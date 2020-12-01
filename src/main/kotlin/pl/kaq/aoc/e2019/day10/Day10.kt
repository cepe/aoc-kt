package pl.kaq.aoc.e2019.day10

import pl.kaq.aoc.AoC
import java.lang.StrictMath.abs

typealias Point = Pair<Int, Int>

val Point.x: Int get() = first
val Point.y: Int get() = second

fun det(a: Point, b: Point, c: Point): Int {
    return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)
}

class Day10 : AoC<Int?, Int> {

    override fun firstStar(): Int? {
        return asteroids().map { countVisible(it, asteroids().minus(it).sortedByAngle(it, Point(100, 100))) }.max()
    }

    private fun asteroids(): List<Point> {
        return inputLines()
            .mapIndexed { rowNum, stringRow ->
                stringRow
                    .mapIndexed { colNum, cellValue -> Pair(Point(colNum, rowNum), cellValue) }
                    .filter { it.second == '#' }
                    .map { it.first }
            }.flatten()
    }

    private fun countVisible(p: Point, points: List<Point>): Int {
        var lastPoint = p.copy(p.first - 1, p.second + 500)
        var counter = 0
        for (point in points) {
            if (det(p, lastPoint, point) != 0) {
                counter += 1
            }
            lastPoint = point
        }
        return counter
    }

    override fun secondStar(): Int {
        val points = asteroids()

        val laserPosition: Point =
            points.first { countVisible(it, points.minus(it).sortedByAngle(it, Point(100, 100))) == 282 }

        val sortedAroundLaser = points.minus(laserPosition)
            .sortedByAngle(laserPosition, laserPosition.copy(laserPosition.first, 0))

        var counter = 200
        val destroyed = mutableSetOf<Point>()
        while (counter >= 0) {
            var lastPoint = laserPosition.copy(laserPosition.first - 1, laserPosition.second + 500)
            for (point in sortedAroundLaser) {
                if (point in destroyed) continue

                if (det(laserPosition, lastPoint, point) != 0) {
                    lastPoint = point
                    destroyed.add(point)
                    counter -= 1
                    if (counter == 0) {
                        return point.x * 100 + point.y
                    }
                }
            }
        }
        throw IllegalStateException()
    }

}

private fun List<Point>.sortedByAngle(s: Point, k: Point): List<Point> {

    val left = this.filter { det(s, k, it) >= 0 }
    val right = this.filter { det(s, k, it) < 0 }

    fun comparator(s: Point): Comparator<Point> {
        val compare: (Point, Point) -> Int = { a: Point, b: Point ->
            val d = det(s, a, b)
            if (d == 0) {
                abs(s.x - b.x) + abs(s.y - b.y) - (abs(s.x - a.x) + abs(s.y - a.y))
            } else {
                -d
            }
        }
        return Comparator(compare)
    }

    return left.sortedWith(comparator(s)).plus(right.sortedWith(comparator(s)))
}

fun main() {
    Day10().solve()
}
