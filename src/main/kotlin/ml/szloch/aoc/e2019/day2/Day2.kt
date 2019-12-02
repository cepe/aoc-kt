package ml.szloch.aoc.e2019.day2

import ml.szloch.aoc.AoC

class Day1 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::toInt)
            .toMutableList()
        memory[1] = 12
        memory[2] = 2

        return runProgram(memory)
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
                if (runProgram(mutableMemory) == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        throw IllegalStateException()
    }

    private fun runProgram(mem: MutableList<Int>): Int {
        var pos = 0
        while (true) {
            when (mem[pos]) {
                99 -> return mem[0];
                1 -> mem[mem[pos + 3]] = mem[mem[pos + 1]] + mem[mem[pos + 2]]
                2 -> mem[mem[pos + 3]] = mem[mem[pos + 1]] * mem[mem[pos + 2]]
                else -> throw IllegalStateException()
            }
            pos += 4
        }
    }

}

fun main() {
    Day1().solve()
}