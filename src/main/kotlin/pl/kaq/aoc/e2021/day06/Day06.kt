package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        return (1..80).fold(parsePopulation()) { prevPop, _ -> nextPop(prevPop) }.values.sum()
    }

    private fun nextPop(pop: Map<Int, Long>): Map<Int, Long> {
        return (0..8).associateWith { pop.getValue((it + 1) % 9) + (if (it == 6) pop.getValue(0) else 0) }
    }

    override fun secondStar(): Long {
        return (1..256).fold(parsePopulation()) { prevPop, _ -> nextPop(prevPop) }.values.sum()
    }

    private fun parsePopulation(): Map<Int, Long> {
        return inputTrimmed().split(",")
            .map { it.toInt() }
            .groupingBy { it }.eachCount()
            .mapValues { it.value.toLong() }
            .withDefault { 0 }
    }
}

fun main() {
    Day06().solve()
}
