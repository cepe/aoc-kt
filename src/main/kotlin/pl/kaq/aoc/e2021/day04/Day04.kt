package pl.kaq.aoc.e2021.day04

import pl.kaq.aoc.AoC
import pl.kaq.aoc.e2021.day04.BingoParser.parseInput
import pl.kaq.aoc.e2021.day04.BingoSimulator.simulateBingo

class Day04 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val (numbers, boards) = parseInput(inputTrimmed())
        val scores = simulateBingo(numbers, boards)
        return scores.first()
    }

    override fun secondStar(): Int {
        val (numbers, boards) = parseInput(inputTrimmed())
        val scores = simulateBingo(numbers, boards)
        return scores.last()
    }
}

fun main() {
    Day04().solve()
}
