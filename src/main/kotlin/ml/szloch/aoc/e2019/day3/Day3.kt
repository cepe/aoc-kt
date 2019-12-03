package ml.szloch.aoc.e2019.day3

import ml.szloch.aoc.AoC
import kotlin.math.abs

class Mover {
    var x: Int = 0
    var y: Int = 0
    var distance: Int = 0

    val points: MutableSet<Pair<Int, Int>> = mutableSetOf()
    val distanceTo: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    init {
        distanceTo.putIfAbsent(Pair(0, 0), 0)
    }

    fun move(move: Move) {
        when (move) {
            is Move.Left -> repeat(move.times) { moveLeft() }
            is Move.Right -> repeat(move.times) { moveRight() }
            is Move.Up -> repeat(move.times) { moveUp() }
            is Move.Down -> repeat(move.times) { moveDown() }
        }
    }

    private fun moveLeft() = moveTo(x - 1, y)
    private fun moveRight() = moveTo(x + 1, y)
    private fun moveDown() = moveTo(x, y - 1)
    private fun moveUp() = moveTo(x, y + 1)

    private fun moveTo(i: Int, j: Int) {
        x = i
        y = j
        points.add(Pair(x, y))
        distance += 1
        distanceTo.putIfAbsent(Pair(x, y), distance)
    }
}


sealed class Move {
    class Left(val times: Int) : Move()
    class Right(val times: Int) : Move()
    class Up(val times: Int) : Move()
    class Down(val times: Int) : Move()
}

class Day3 : AoC<Int?, Int> {

    override fun firstStar(): Int? {
        val (firstWire, secondWire) = getWirePaths()
        val crosses = firstWire.points.intersect(secondWire.points)
        return crosses.map { abs(it.first) + abs(it.second) }.min()
    }

    override fun secondStar(): Int {
        val (firstWire, secondWire) = getWirePaths()
        val crosses = firstWire.points.intersect(secondWire.points)
        val firstWireDistances = firstWire.distanceTo
        val secondWireDistances = secondWire.distanceTo
        val closestCross = crosses.minBy { firstWireDistances.getValue(it) + secondWireDistances.getValue(it) }
        return firstWireDistances.getValue(closestCross!!) + secondWireDistances.getValue(closestCross)
    }

    private fun getWirePaths(): Pair<Mover, Mover> {
        val paths = inputLines()
            .map(String::trim)

        val firstWirePathCommands = paths[0].split(",").map(this::toMoveCommands)
        val firstWire = Mover()
        firstWirePathCommands.forEach(firstWire::move)

        val secondWirePathCommands = paths[1].split(",").map(this::toMoveCommands)
        val secondWire = Mover()
        secondWirePathCommands.forEach(secondWire::move)

        return Pair(firstWire, secondWire)
    }

    private fun toMoveCommands(stringCommand: String): Move {
        val matchedCommand = "([LRUD])(\\d+)".toRegex().matchEntire(stringCommand)
        if (matchedCommand != null) {
            val matchedGroups = matchedCommand.groupValues
            val times = matchedGroups[2].toInt()

            when (matchedGroups[1]) {
                "U" -> return Move.Up(times)
                "L" -> return Move.Left(times)
                "R" -> return Move.Right(times)
                "D" -> return Move.Down(times)
            }
        }
        throw IllegalStateException()
    }

}

fun main() {
    Day3().solve()
}
