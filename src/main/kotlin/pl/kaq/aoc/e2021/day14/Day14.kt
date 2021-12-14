package pl.kaq.aoc.e2021.day14

import pl.kaq.aoc.AoC

class Day14 : AoC<Long, Long> {

    override fun firstStar(): Long {
        val (polymer, rules) = parseInput()
        val (min, max) = simulate(polymer, rules, 10)
        return max - min
    }

    override fun secondStar(): Long {
        val (polymer, rules) = parseInput()
        val (min, max) = simulate(polymer, rules, 40)
        return max - min
    }

    private fun simulate(polymer: String, rules: Map<String, String>, stepCount: Int): Pair<Long, Long> {
        val steps = generateSequence(polymer.windowed(2).associateWith { 1L }) { counters -> step(counters, rules) }
        val afterSteps = steps
            .drop(stepCount)
            .first()
            .flatMap { (k, v) -> listOf(k[0] to v, k[1] to v) }
            .plus(Pair(polymer.first(), 1L))
            .plus(Pair(polymer.last(), 1L))
            .sumGroups()

        return Pair(afterSteps.minOf { it.value } / 2, afterSteps.maxOf { it.value } / 2)
    }

    private fun <T> List<Pair<T, Long>>.sumGroups(): Map<T, Long> {
        return this.groupBy { it.first }
            .map { (k, v) -> k to v.sumOf { it.second } }
            .toMap()
    }

    private fun step(counters: Map<String, Long>, rules: Map<String, String>) = counters
        .flatMap { (k, v) ->
            if (k in rules) listOf(k[0] + rules.getValue(k) to v, rules.getValue(k) + k[1] to v) else listOf(k to v)
        }
        .sumGroups()

    private fun parseInput(): Pair<String, Map<String, String>> {
        val lines = inputLines()
        val polymer = lines[0]
        val rules = lines
            .drop(2)
            .map { it.split(" -> ") }
            .associate { it[0] to it[1] }
        return Pair(polymer, rules)
    }
}

fun main() {
    Day14().solve()
}
