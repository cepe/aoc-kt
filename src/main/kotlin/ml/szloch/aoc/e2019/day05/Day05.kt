package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import java.util.*

class Day05 : AoC<Long, Long> {

    override fun firstStar(): Long {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
            .toMutableMap()

        val vm = VM(memory, Vector(listOf(1L)), Vector()); vm.startExecution()
        return vm.output.last()
    }

    override fun secondStar(): Long {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
            .toMutableMap()

        val vm = VM(memory, Vector(listOf(5L)), Vector()); vm.startExecution()
        return vm.output.last()
    }
}

fun main() {
    Day05().solve()
}
