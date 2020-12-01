package pl.kaq.aoc.e2019.day02

import pl.kaq.aoc.AoC
import pl.kaq.aoc.e2019.VM
import pl.kaq.aoc.e2019.VectorIO

class Day02 : AoC<Long?, Long?> {

    override fun firstStar(): Long? {
        val memory = inputTrimmed()
            .split(",")
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
            .toMutableMap()

        memory[1] = 12L
        memory[2] = 2L


        return VM(memory, VectorIO(), VectorIO()).startExecution().mem[0]
    }

    override fun secondStar(): Long? {
        val memory = inputTrimmed()
            .split(",")
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()

        for (noun in 0..99L) {
            for (verb in 0..99L) {
                val mutableMemory = memory.toMutableMap()
                mutableMemory[1] = noun
                mutableMemory[2] = verb
                if (VM(mutableMemory, VectorIO(), VectorIO()).startExecution().mem[0] == 19690720L) {
                    return 100L * noun + verb
                }
            }
        }
        throw IllegalStateException()
    }
}

fun main() {
    Day02().solve()
}
