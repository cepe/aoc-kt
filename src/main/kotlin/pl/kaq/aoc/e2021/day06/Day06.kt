package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC
import kotlin.reflect.KFunction1

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        return iterate(80, ::nextPop, readPopulation()).values.sum()
    }

    override fun secondStar(): Long {
        return iterate(256, ::nextPop, readPopulation()).values.sum()
    }

    private fun <T> iterate(times: Int, f: KFunction1<T, T>, init: T): T {
        tailrec fun iterateRec(curr: T, counter: Int): T {
            return if (counter == 0) curr else iterateRec(f(curr), counter - 1)
        }
        return iterateRec(init, times)
    }

    private fun nextPop(pop: Map<Int, Long>): Map<Int, Long> {
        return (0..8).associateWith { pop.getValue((it + 1) % 9) + (if (it == 6) pop.getValue(0) else 0) }
    }

    private fun readPopulation(): Map<Int, Long> {
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
