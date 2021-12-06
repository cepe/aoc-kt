package pl.kaq.aoc.e2021.day06

import pl.kaq.aoc.AoC
import pl.kaq.aoc.counted

class Day06 : AoC<Long, Long> {

    override fun firstStar(): Long {
        val nums = inputTrimmed()
            .split(",")
            .map { it.toInt() }
            .counted()
            .withDefault { 0 }

        val pop = (0..8)
            .map { nums.getValue(it).toLong() }
            .toMutableList()

        repeat(80) { tick(pop) }

        return pop.sum()
    }

    override fun secondStar(): Long {
        val nums = inputTrimmed()
            .split(",")
            .map { it.toInt() }
            .counted()
            .withDefault { 0 }

        val pop = (0..8)
            .map { nums.getValue(it).toLong() }
            .toMutableList()

        repeat(256) { tick(pop) }

        return pop.sum()
    }

    private fun tick(pop: MutableList<Long>) {
        val tmp = pop[0]
        for (i in 0..7) {
            pop[i] = pop[i + 1]
        }
        pop[8] = tmp
        pop[6] += tmp
    }
}

fun main() {
    Day06().solve()
}
