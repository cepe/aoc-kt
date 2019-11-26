package ml.szloch.aoc.e2015.day1

import ml.szloch.aoc.AoC

class Day1 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return input().map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> 0
            }
        }.sum()
    }

    override fun secondStar(): Int {
        return input()
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
    Day1().solve()
}