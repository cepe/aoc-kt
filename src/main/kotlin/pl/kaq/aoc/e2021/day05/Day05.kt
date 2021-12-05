package pl.kaq.aoc.e2021.day05

import pl.kaq.aoc.AoC

typealias Point = Pair<Int, Int>

class Day05 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputLines()
            .map(LineParser::parseLine)
            .filter { !it.isDiagonal() }
            .flatMap { it.points() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }

    override fun secondStar(): Int {
        return inputLines()
            .map(LineParser::parseLine)
            .flatMap { it.points() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }
}

fun main() {
    Day05().solve()
}
