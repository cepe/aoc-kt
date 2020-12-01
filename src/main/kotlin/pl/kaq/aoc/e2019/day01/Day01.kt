package pl.kaq.aoc.e2019.day01

import pl.kaq.aoc.AoC
import kotlin.math.max

class Day01 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputLines()
            .map(String::toInt)
            .map { it / 3 - 2 }
            .sum()
    }

    override fun secondStar(): Int {
        return inputLines()
            .map(String::toInt)
            .map(this::fuelForMass)
            .sum()
    }

    private fun fuelForMass(mass: Int): Int {
        val fuel = max(0, mass / 3 - 2)
        return if (fuel > 0) fuel + fuelForMass(fuel) else 0
    }
}

fun main() {
    Day01().solve()
}