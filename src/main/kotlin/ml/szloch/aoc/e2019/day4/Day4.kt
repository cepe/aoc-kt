package ml.szloch.aoc.e2019.day4

import ml.szloch.aoc.AoC

class Day4 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputRange()
            .map { it.toString() }
            .filter { it.length == 6 }
            .filter { "(\\d)\\1".toRegex().containsMatchIn(it) }
            .filter { it == it.toCharArray().sorted().joinToString("") }
            .size
    }

    override fun secondStar(): Int {
        return inputRange()
            .map { it.toString() }
            .filter { it.length == 6 }
            .filter(this::twoInRow)
            .filter { it == it.toCharArray().sorted().joinToString("") }
            .size
    }

    private fun twoInRow(string: String): Boolean {
        for ((_, g) in string.groupBy { it }) {
            if (g.size == 2) {
                return true
            }
        }
        return false
    }


    private fun inputRange(): IntRange {
        val twoIntegers = inputTrimmed().split("-").map(String::toInt)
        return twoIntegers[0]..twoIntegers[1]
    }

}

fun main() {
    Day4().solve()
}
