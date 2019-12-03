package ml.szloch.aoc.e2019.day3

import ml.szloch.aoc.AoC
import kotlin.math.abs

class Wire(path: List<String>) {
    val points: MutableSet<Pair<Int, Int>> = mutableSetOf()
    val distanceTo: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    init {
        var x = 0
        var y = 0
        var distance = 0

        fun moveTo(i: Int, j: Int) {
            x = i
            y = j
            points.add(Pair(x, y))
            distance += 1
            distanceTo.putIfAbsent(Pair(x, y), distance)
        }

        path.forEach { pathCommand ->
            val times = pathCommand.substring(1).toInt()

            when (pathCommand[0]) {
                'U' -> repeat(times) { moveTo(x, y + 1) }
                'L' -> repeat(times) { moveTo(x - 1, y) }
                'R' -> repeat(times) { moveTo(x + 1, y) }
                else -> repeat(times) { moveTo(x, y - 1) }
            }
        }
    }
}


class Day3 : AoC<Int?, Int> {

    override fun firstStar(): Int? {
        val (firstWire, secondWire) = wires()
        val crosses = firstWire.points.intersect(secondWire.points)
        return crosses.map { abs(it.first) + abs(it.second) }.min()
    }

    override fun secondStar(): Int {
        val (firstWire, secondWire) = wires()
        val crosses = firstWire.points.intersect(secondWire.points)
        val closestCross = crosses.minBy { firstWire.distanceTo[it]!! + secondWire.distanceTo[it]!! }
        return firstWire.distanceTo[closestCross]!! + secondWire.distanceTo[closestCross]!!
    }

    private fun wires(): Pair<Wire, Wire> {
        val paths = inputLines().map(String::trim)
        return Pair(Wire(paths[0].split(",")), Wire(paths[1].split(",")))
    }
}


fun main() {
    Day3().solve()
}
