package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC
import kotlin.reflect.KFunction1

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        return ::nextPop.iterate(80, readPop()).values.sum()
    }

    override fun secondStar(): Long {
        return ::nextPop.iterate(256, readPop()).values.sum()
    }

    private fun <T> KFunction1<T, T>.iterate(times: Int, init: T): T {
        return (1..times).fold(init) { acc, _ -> this(acc) }
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
