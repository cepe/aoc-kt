package pl.kaq.aoc.e2019.day05

import pl.kaq.aoc.AoC
import pl.kaq.aoc.e2019.VM
import pl.kaq.aoc.e2019.VectorIO
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

        val vm = VM(memory, VectorIO(Vector(listOf(1L))), VectorIO()); vm.startExecution()
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

        val vm = VM(memory, VectorIO(Vector(listOf(5L))), VectorIO()); vm.startExecution()
        return vm.output.last()
    }
}

fun main() {
    Day05().solve()
}
