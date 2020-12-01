package pl.kaq.aoc.e2015.day01

import pl.kaq.aoc.AoC

class Day01 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputTrimmed().map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> 0
            }
        }.sum()
    }

    override fun secondStar(): Int {
        return inputTrimmed()
            .map {
                when (it) {
                    '(' -> 1
                    ')' -> -1
                    else -> 0
                }
            }
            .fold(listOf(0), { r: List<Int>, e -> r.plus(r.last() + e) })
            .indexOf(-1)
    }
}

fun main() {
    Day01().solve()
}