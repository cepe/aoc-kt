package ml.szloch.aoc.e2015.day2

import ml.szloch.aoc.AoC
import java.util.Collections.min

data class Box(val l: Int, val w: Int, val h: Int) {
    fun sides(): List<Int> = listOf(h * l, h * w, l * w)
    fun dims(): List<Int> = listOf(l, h, w)
    fun volume(): Int = l * h * w
}

class Day2 : AoC<Int, Int> {

    override fun firstStar(): Int = boxes().map { min(it.sides()) + it.sides().sum() * 2 }.sum()

    override fun secondStar(): Int = boxes().map { it.volume() + it.dims().sorted().subList(0, 2).sum() * 2 }.sum()

    private fun boxes(): List<Box> = inputLines().map(this::parseLine)

    private fun parseLine(line: String): Box {
        val list = line.split("x").map(String::toInt)
        return Box(list[0], list[1], list[2])
    }
}

fun main() {
    Day2().solve()
}