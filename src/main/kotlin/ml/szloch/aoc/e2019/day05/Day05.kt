package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import java.util.*

class Day05 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        val vm = VM(memory, Vector(listOf(1)), Vector()); vm.startExecution()
        return vm.output.last()
    }

    override fun secondStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        val vm = VM(memory, Vector(listOf(5)), Vector()); vm.startExecution()
        return vm.output.last()
    }
}

fun main() {
    Day05().solve()
}
