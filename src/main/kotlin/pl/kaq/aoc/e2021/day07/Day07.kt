package pl.kaq.aoc.e2021.day07

import pl.kaq.aoc.AoC
import kotlin.math.abs

class Day07 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val nums = inputTrimmed().split(",").map { it.toInt() }
        val min = nums.minOrNull()!!
        val max = nums.maxOrNull()!!
        return (min..max).minOf { align -> nums.sumOf { abs(it - align) } }
    }

    override fun secondStar(): Int {
        val nums = inputTrimmed().split(",").map { it.toInt() }
        val min = nums.minOrNull()!!
        val max = nums.maxOrNull()!!
        return (min..max).minOf { align -> nums.sumOf { abs(it - align).let { dist -> dist * (dist + 1) / 2 } } }
    }

}

fun main() {
    Day07().solve()
}
