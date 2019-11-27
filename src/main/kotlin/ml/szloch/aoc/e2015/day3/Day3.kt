package ml.szloch.aoc.e2015.day3

import ml.szloch.aoc.AoC
import kotlin.reflect.KFunction0

class Mover(var x: Int, var y: Int) {
    fun moveUp() = run { y += 1 }
    fun moveDown() = run { y -= 1 }
    fun moveRight() = run { x += 1 }
    fun moveLeft() = run { x -= 1 }
    fun doNothing() = run {}
    fun getPosition() = Pair(x, y)
}

class Day3 : AoC<Int, Int> {
    override fun firstStar(): Int {
        val santa = Mover(0, 0)
        val encodedSantaMoves = encodedMoves()
        val santaMoves = decodeMoves(santa, encodedSantaMoves)

        val visitedHouses = mutableSetOf<Pair<Int, Int>>()
        santaMoves.forEach {
            run {
                it()
                visitedHouses.add(santa.getPosition())
            }
        }
        return visitedHouses.size
    }

    override fun secondStar(): Int {
        val santa = Mover(0, 0)
        val robot = Mover(0, 0)
        val encodedMoves = encodedMoves()
        val santaMovesEncoded = encodedMoves.slice(0 until encodedMoves.length step 2)
        val robotMovesEncoded = encodedMoves.slice(1 until encodedMoves.length step 2)
        val santaMoves = decodeMoves(santa, santaMovesEncoded)
        val robotMoves = decodeMoves(robot, robotMovesEncoded)

        val visitedHouses = mutableSetOf<Pair<Int, Int>>()
        santaMoves.forEach {
            run {
                it()
                visitedHouses.add(santa.getPosition())
            }
        }

        robotMoves.forEach {
            run {
                it()
                visitedHouses.add(robot.getPosition())
            }
        }
        return visitedHouses.size
    }

    private fun encodedMoves() = inputTrimmed().toLowerCase()

    private fun decodeMoves(mover: Mover, encodedSantaMoves: String): List<KFunction0<Unit>> {
        return encodedSantaMoves.map {
            when (it) {
                '>' -> mover::moveRight
                '<' -> mover::moveLeft
                'v' -> mover::moveDown
                '^' -> mover::moveUp
                else -> mover::doNothing
            }
        }
    }
}

fun main() {
    Day3().solve()
}