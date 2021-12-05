package pl.kaq.aoc.e2021.day05

import pl.kaq.aoc.AoC
import pl.kaq.aoc.counters

typealias Point = Pair<Int, Int>

class Day05 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputLines()
            .map(LineParser::parseLine)
            .filter { !it.isDiagonal() }
            .flatMap { it.points() }
            .counters()
            .count { it > 1 }
    }

    override fun secondStar(): Int {
        return inputLines()
            .map(LineParser::parseLine)
            .flatMap { it.points() }
            .counters()
            .count { it > 1 }
    }
}

fun main() {
    Day05().solve()
}
