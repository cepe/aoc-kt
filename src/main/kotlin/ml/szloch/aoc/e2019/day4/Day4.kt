package ml.szloch.aoc.e2019.day4

import ml.szloch.aoc.AoC

class Day4 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputRange()
            .map { it.toString() }
            .filter { it.length == 6 }
            .filter { "(\\d)\\1".toRegex().containsMatchIn(it) }
            .filter(this::notDecreasing)
            .size
    }

    override fun secondStar(): Int {
        return inputRange()
            .map { it.toString() }
            .filter { it.length == 6 }
            .filter(this::twoInRow)
            .filter(this::notDecreasing)
            .size
    }

    private fun notDecreasing(string: String) = string.asSequence().windowed(2).all { (a, b) -> a <= b }

    private fun twoInRow(string: String): Boolean = string.groupBy { it }.any { (_, g) -> g.size == 2 }

    private fun inputRange(): IntRange {
        val twoIntegers = inputTrimmed().split("-").map(String::toInt)
        return twoIntegers[0]..twoIntegers[1]
    }

}

fun main() {
    Day4().solve()
}
