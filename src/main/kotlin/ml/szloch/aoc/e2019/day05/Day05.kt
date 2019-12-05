package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC

data class State(
    val ip: Int,
    val mem: List<Int>,
    val input: List<Int>,
    val output: List<Int>,
    val halted: Boolean
)

interface Operation {
    fun execute(state: State): State
    fun opValue(mem: List<Int>, address: Int, mode: Boolean): Int = if (mode) address else mem[address]
    fun op1(state: State, mode: Boolean): Int = opValue(state.mem, state.mem[state.ip + 1], mode)
    fun op2(state: State, mode: Boolean): Int = opValue(state.mem, state.mem[state.ip + 2], mode)
    fun op3(state: State, mode: Boolean): Int = opValue(state.mem, state.mem[state.ip + 3], mode)
}

class AddOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State {
        val newMem = state.mem.toMutableList()
        newMem[op3(state, true)] = op1(state, modes.first) + op2(state, modes.second)
        return state.copy(ip = state.ip + 4, mem = newMem)
    }
}

class MulOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State {
        val newMem = state.mem.toMutableList()
        newMem[op3(state, true)] = op1(state, modes.first) * op2(state, modes.second)
        return state.copy(ip = state.ip + 4, mem = newMem)
    }
}


class ReadOp : Operation {
    override fun execute(state: State): State {
        val newMem = state.mem.toMutableList()
        newMem[op1(state, true)] = state.input.first()
        return state.copy(
            ip = state.ip + 2, mem = newMem, input = state.input.drop(1)
        )
    }
}

class WriteOp(private val mode: Boolean) : Operation {
    override fun execute(state: State): State =
        state.copy(ip = state.ip + 2, output = state.output.plus(op1(state, mode)))
}


class JumpIfTrueOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State =
        state.copy(ip = if (op1(state, modes.first) != 0) op2(state, modes.second) else state.ip + 3)
}

class JumpIfFalseOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State =
        state.copy(ip = if (op1(state, modes.first) == 0) op2(state, modes.second) else state.ip + 3)
}

class LessThanOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State {
        val newMem = state.mem.toMutableList()
        newMem[op3(state, true)] = if (op1(state, modes.first) < op2(state, modes.second)) 1 else 0
        return state.copy(ip = state.ip + 4, mem = newMem)
    }
}

class EqualsOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(state: State): State {
        val newMem = state.mem.toMutableList()
        newMem[op3(state, true)] = if (op1(state, modes.first) == op2(state, modes.second)) 1 else 0
        return state.copy(ip = state.ip + 4, mem = newMem)
    }
}

class HaltOp : Operation {
    override fun execute(state: State): State = state.copy(halted = true)
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
        var executionContext = State(0, mem, input, listOf(), false)

        while (!executionContext.halted) {
            executionContext = operationAt(executionContext.ip, executionContext.mem).execute(executionContext)
        }
        return executionContext.output
    }

    private fun operationAt(ip: Int, mem: List<Int>): Operation {
        val opcodeAndModes = mem[ip]
        val opcode = opcodeAndModes % 100
        val modesEncoded = opcodeAndModes / 100
        val modes = Pair(modesEncoded % 10 == 1, (modesEncoded / 10) % 10 == 1)

        return when (opcode) {
            1 -> AddOp(modes)
            2 -> MulOp(modes)
            3 -> ReadOp()
            4 -> WriteOp(modes.first)
            5 -> JumpIfTrueOp(modes)
            6 -> JumpIfFalseOp(modes)
            7 -> LessThanOp(modes)
            8 -> EqualsOp(modes)
            99 -> HaltOp()
            else -> throw IllegalStateException()
        }
    }

}

fun main() {
    Day05().solve()
}
