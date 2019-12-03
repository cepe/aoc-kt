package ml.szloch.aoc.e2019.day3

import ml.szloch.aoc.AoC
import kotlin.math.abs

class Mover {
    var x: Int = 0
    var y: Int = 0
    var distance: Int = 0

    val points: MutableSet<Pair<Int, Int>> = mutableSetOf()
    val distanceTo: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    fun applyCommand(pathCommand: String) {
        val times = pathCommand.substring(1).toInt()

        return when (pathCommand[0]) {
            'U' -> repeat(times) { moveTo(x, y + 1) }
            'L' -> repeat(times) { moveTo(x - 1, y) }
            'R' -> repeat(times) { moveTo(x + 1, y) }
            else -> repeat(times) { moveTo(x, y - 1) }
        }
    }

    private fun moveTo(i: Int, j: Int) {
        x = i
        y = j
        points.add(Pair(x, y))
        distance += 1
        distanceTo.putIfAbsent(Pair(x, y), distance)
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
        val firstWireDistances = firstWire.distanceTo
        val secondWireDistances = secondWire.distanceTo
        val closestCross = crosses.minBy { firstWireDistances.getValue(it) + secondWireDistances.getValue(it) }
        return firstWireDistances.getValue(closestCross!!) + secondWireDistances.getValue(closestCross)
    }

    private fun wires(): Pair<Mover, Mover> {
        val paths = inputLines().map(String::trim)
        val firstWire = Mover()
        paths[0].split(",").forEach(firstWire::applyCommand)

        val secondWire = Mover()
        paths[1].split(",").forEach(secondWire::applyCommand)

        return Pair(firstWire, secondWire)
    }
}


fun main() {
    Day3().solve()
}
