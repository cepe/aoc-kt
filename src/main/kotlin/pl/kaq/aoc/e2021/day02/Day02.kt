package pl.kaq.aoc.e2021.day02

import pl.kaq.aoc.AoC

class Day02 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val (d, h) = inputLines()
            .map { it.split(" ") }
            .map { Pair(it[0], it[1].toInt()) }
            .fold(Pair(0, 0)) { (d, h), (command, num) ->
                when (command) {
                    "forward" -> Pair(d, h + num)
                    "down" -> Pair(d + num, h)
                    "up" -> Pair(d - num, h)
                    else -> throw IllegalStateException()
                }
            }
        return d * h;
    }


    override fun secondStar(): Int {
        val (d, h, _) = inputLines()
            .map { it.split(" ") }
            .map { Pair(it[0], it[1].toInt()) }
            .fold(Triple(0, 0, 0)) { (d, h, a), (command, num) ->
                when (command) {
                    "forward" -> Triple(d + a * num, h + num, a)
                    "down" -> Triple(d, h, a + num)
                    "up" -> Triple(d, h, a - num)
                    else -> throw IllegalStateException()
                }
            }
        return d * h;
    }
}

fun main() {
    Day02().solve()
}
