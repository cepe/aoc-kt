package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        var pop = mutablePopulation()
        repeat(80) { pop = nextPop(pop) }
        return pop.values.sum()
    }

    override fun secondStar(): Long {
        var pop = mutablePopulation()
        repeat(256) { pop = nextPop(pop) }
        return pop.values.sum()
    }

    private fun nextPop(pop: Map<Int, Long>): Map<Int, Long> {
        return (0..8).associateWith { pop.getValue((it + 1) % 9) + (if (it == 6) pop.getValue(0) else 0) }
    }

    private fun mutablePopulation(): Map<Int, Long> {
        return inputTrimmed().split(",")
            .map { it.toInt() }
            .groupingBy { it }.eachCount()
            .mapValues { it.value.toLong() }
            .toMutableMap()
            .withDefault { 0 }
    }
}

fun main() {
    Day06().solve()
}
