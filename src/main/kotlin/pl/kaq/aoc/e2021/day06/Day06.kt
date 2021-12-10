package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        return generateSequence(readPop()) { nextPop(it) }.drop(80).first().values.sum()
    }

    override fun secondStar(): Long {
        return generateSequence(readPop()) { nextPop(it) }.drop(256).first().values.sum()
    }

    private fun nextPop(pop: Map<Int, Long>): Map<Int, Long> {
        return (0..8).associateWith { pop.getValue((it + 1) % 9) + (if (it == 6) pop.getValue(0) else 0) }
    }

    private fun readPop(): Map<Int, Long> {
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
