package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC

class Day05 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        val input = listOf(1)

        return runProgram(memory, input).last()
    }

    override fun secondStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        val input = listOf(5)

        return runProgram(memory, input).last()
    }

    private fun runProgram(mem: MutableList<Int>, input: List<Int>): List<Int> {
        var ip = 0
        var i = 0

        val output = mutableListOf<Int>()

        while (true) {
            val opcodeWithModes = mem[ip]
            val opcode = opcodeWithModes % 100
            var modes = opcodeWithModes / 100
            val fom = modes % 10; modes /= 10;
            val som = modes % 10

            when (opcode) {
                99 -> {
                    return output
                }
                1 -> {
                    mem[mem[ip + 3]] = opv(mem, fom, mem[ip + 1]) + opv(mem, som, mem[ip + 2])
                    ip += 4
                }
                2 -> {
                    mem[mem[ip + 3]] = opv(mem, fom, mem[ip + 1]) * opv(mem, som, mem[ip + 2])
                    ip += 4
                }
                3 -> {
                    mem[mem[ip + 1]] = input[i]
                    i += 1
                    ip += 2
                }
                4 -> {
                    output.add(opv(mem, fom, mem[ip + 1]))
                    ip += 2
                }
                5 -> {
                    if (opv(mem, fom, mem[ip + 1]) != 0) {
                        ip = opv(mem, som, mem[ip + 2])
                    } else {
                        ip += 3
                    }
                }
                6 -> {
                    if (opv(mem, fom, mem[ip + 1]) == 0) {
                        ip = opv(mem, som, mem[ip + 2])
                    } else {
                        ip += 3
                    }
                }
                7 -> {
                    if (opv(mem, fom, mem[ip + 1]) < opv(mem, som, mem[ip + 2])) {
                        mem[mem[ip + 3]] = 1
                    } else {
                        mem[mem[ip + 3]] = 0
                    }
                    ip += 4
                }
                8 -> {
                    if (opv(mem, fom, mem[ip + 1]) == opv(mem, som, mem[ip + 2])) {
                        mem[mem[ip + 3]] = 1
                    } else {
                        mem[mem[ip + 3]] = 0
                    }
                    ip += 4

                }
                else -> throw IllegalStateException()
            }
        }

    }

    private fun opv(mem: MutableList<Int>, mode: Int, param: Int): Int {
        return when (mode) {
            0 -> mem[param]
            else -> param
        }
    }

}

fun main() {
    Day05().solve()
}
