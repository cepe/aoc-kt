package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC

data class State(
    var ip: Int,
    val mem: MutableList<Int>,
    var inp: Int,
    val input: List<Int>,
    val output: MutableList<Int>,
    var halted: Boolean
)

interface Operation {
    fun execute(state: State)
    fun opValue(mem: List<Int>, address: Int, mode: Boolean): Int = if (mode) address else mem[address]
    fun op1(state: State, mode: Boolean = false) =
        opValue(state.mem, state.mem[state.ip + 1], mode || (state.mem[state.ip] / 100) % 10 == 1)

    fun op2(state: State): Int = opValue(state.mem, state.mem[state.ip + 2], state.mem[state.ip] / 100 >= 10)
    fun op3(state: State): Int = opValue(state.mem, state.mem[state.ip + 3], true)
}

class AddOp : Operation {
    override fun execute(state: State) {
        state.mem[op3(state)] = op1(state) + op2(state); state.ip += 4
    }
}

class MulOp : Operation {
    override fun execute(state: State) {
        state.mem[op3(state)] = op1(state) * op2(state); state.ip += 4
    }
}

class ReadOp : Operation {
    override fun execute(state: State) {
        state.mem[op1(state, true)] = state.input[state.inp]; state.inp += 1; state.ip += 2
    }
}

class WriteOp : Operation {
    override fun execute(state: State) {
        state.output.add(op1(state)); state.ip += 2
    }
}

class JumpIfTrueOp : Operation {
    override fun execute(state: State) {
        state.ip = if (op1(state) != 0) op2(state) else state.ip + 3
    }
}

class JumpIfFalseOp : Operation {
    override fun execute(state: State) {
        state.ip = if (op1(state) == 0) op2(state) else state.ip + 3
    }
}

class LessThanOp : Operation {
    override fun execute(state: State) {
        state.mem[op3(state)] = if (op1(state) < op2(state)) 1 else 0; state.ip += 4
    }
}

class EqualsOp : Operation {
    override fun execute(state: State) {
        state.mem[op3(state)] = if (op1(state) == op2(state)) 1 else 0; state.ip += 4
    }
}

class HaltOp : Operation {
    override fun execute(state: State) {
        state.halted = true
    }
}

class Day05 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        return runProgram(memory, listOf(1)).last()
    }

    override fun secondStar(): Int {
        val memory = inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
            .toMutableList()

        return runProgram(memory, listOf(5)).last()
    }

    private fun runProgram(mem: MutableList<Int>, input: List<Int>): List<Int> {
        val state = State(0, mem, 0, input, mutableListOf(), false)

        while (!state.halted) {
            currentOperation(state).execute(state)
        }
        return state.output
    }

    private fun currentOperation(state: State): Operation {
        return when (state.mem[state.ip] % 100) {
            1 -> AddOp()
            2 -> MulOp()
            3 -> ReadOp()
            4 -> WriteOp()
            5 -> JumpIfTrueOp()
            6 -> JumpIfFalseOp()
            7 -> LessThanOp()
            8 -> EqualsOp()
            99 -> HaltOp()
            else -> {
                println(state.mem[state.ip])
                throw IllegalStateException()
            }
        }
    }
}

fun main() {
    Day05().solve()
}
