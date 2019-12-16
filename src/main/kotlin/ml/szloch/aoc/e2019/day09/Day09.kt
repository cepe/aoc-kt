package ml.szloch.aoc.e2019.day09

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import ml.szloch.aoc.e2019.VectorIO
import java.util.*

class Day09 : AoC<Long?, Long?> {

    override fun firstStar(): Long? {
        val memory = readMemory().toMutableMap()

        val vm = VM(memory, VectorIO(Vector(listOf(1L))), VectorIO())
        vm.startExecution()
        return vm.output.last()
    }

    override fun secondStar(): Long? {
        val memory = readMemory().toMutableMap()

        val vm = VM(memory, VectorIO(Vector(listOf(2L))), VectorIO())
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
