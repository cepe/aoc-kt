package pl.kaq.aoc.e2021.day01

import pl.kaq.aoc.AoC

class Day01 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputLines()
            .map(String::toInt)
            .windowed(2)
            .count { it[0] < it[1] }
    }

    override fun secondStar(): Int {
        return inputLines()
            .map(String::toInt)
            .windowed(3)
            .map { it.sum() }
            .windowed(2)
            .count { it[0] < it[1] }
    }

}

fun main() {
    Day01().solve()
}
