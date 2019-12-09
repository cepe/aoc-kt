package ml.szloch.aoc.e2019.day02

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import java.util.*

class Day02 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::toInt)
            .toMutableList()

        memory[1] = 12
        memory[2] = 2

        return VM(memory, Vector(), Vector()).startExecution().mem[0]
    }

    override fun secondStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::toInt)

        for (noun in 0..99) {
            for (verb in 0..99) {
                val mutableMemory = memory.toMutableList()
                mutableMemory[1] = noun
                mutableMemory[2] = verb
                if (VM(mutableMemory, Vector(), Vector()).startExecution().mem[0] == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        throw IllegalStateException()
    }
}

fun main() {
    Day02().solve()
}
