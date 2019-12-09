package ml.szloch.aoc.e2019.day09

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import java.util.*

class Day09 : AoC<Long?, Long?> {

    override fun firstStar(): Long? {
        val memory = readMemory().toMutableMap()

        val vm = VM(memory, Vector(listOf(1L)), Vector())
        vm.startExecution()
        return vm.output.last()
    }

    override fun secondStar(): Long? {
        val memory = readMemory().toMutableMap()

        val vm = VM(memory, Vector(listOf(2L)), Vector())
        vm.startExecution()
        return vm.output.last()
    }

    private fun readMemory(): Map<Long, Long> {
        return inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
    }

}

fun main() {
    Day09().solve()
}
