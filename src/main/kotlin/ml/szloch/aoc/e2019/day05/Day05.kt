package ml.szloch.aoc.e2019.day05

import ml.szloch.aoc.AoC

data class ExecutionContext(
    val ip: Int,
    val mem: List<Int>,
    val input: List<Int>,
    val output: List<Int>,
    val halted: Boolean
)

interface Operation {
    fun execute(executionContext: ExecutionContext): ExecutionContext
    fun opValue(mem: List<Int>, address: Int, mode: Boolean): Int {
        return if (mode) address else mem[address]
    }

    fun lop(executionContext: ExecutionContext, mode: Boolean): Int {
        return opValue(executionContext.mem, executionContext.mem[executionContext.ip + 1], mode)
    }

    fun rop(executionContext: ExecutionContext, mode: Boolean): Int {
        return opValue(executionContext.mem, executionContext.mem[executionContext.ip + 2], mode)
    }

}

class AddOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val target = executionContext.mem[executionContext.ip + 3]
        val newMem = executionContext.mem.toMutableList()
        newMem[target] = lop(executionContext, modes.first) + rop(executionContext, modes.second)
        return executionContext.copy(ip = executionContext.ip + 4, mem = newMem)
    }
}

class MulOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val target = executionContext.mem[executionContext.ip + 3]
        val newMem = executionContext.mem.toMutableList()
        newMem[target] = lop(executionContext, modes.first) * rop(executionContext, modes.second)
        return executionContext.copy(ip = executionContext.ip + 4, mem = newMem)
    }
}


class ReadOp : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val target = executionContext.mem[executionContext.ip + 1]
        val newMem = executionContext.mem.toMutableList()
        newMem[target] = executionContext.input.first()
        return executionContext.copy(
            ip = executionContext.ip + 2, mem = newMem, input = executionContext.input.drop(1)
        )
    }
}

class WriteOp(private val mode: Boolean) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        return executionContext.copy(
            ip = executionContext.ip + 2, output = executionContext.output.plus(lop(executionContext, mode))
        )
    }
}


class JumpIfTrueOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val newIp = if (lop(executionContext, modes.first) != 0) rop(
            executionContext,
            modes.second
        ) else executionContext.ip + 3
        return executionContext.copy(ip = newIp)
    }
}

class JumpIfFalseOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val newIp = if (lop(executionContext, modes.first) == 0) rop(
            executionContext,
            modes.second
        ) else executionContext.ip + 3
        return executionContext.copy(ip = newIp)
    }
}

class LessThanOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val target = executionContext.mem[executionContext.ip + 3]
        val newMem = executionContext.mem.toMutableList()
        newMem[target] = if (lop(executionContext, modes.first) < rop(executionContext, modes.second)) 1 else 0
        return executionContext.copy(ip = executionContext.ip + 4, mem = newMem)
    }
}

class EqualsOp(private val modes: Pair<Boolean, Boolean>) : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        val target = executionContext.mem[executionContext.ip + 3]
        val newMem = executionContext.mem.toMutableList()
        newMem[target] = if (lop(executionContext, modes.first) == rop(executionContext, modes.second)) 1 else 0
        return executionContext.copy(ip = executionContext.ip + 4, mem = newMem)
    }
}

class HaltOp : Operation {
    override fun execute(executionContext: ExecutionContext): ExecutionContext {
        return executionContext.copy(halted = true)
    }
}

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

        return runProgram(memory, listOf(5)).last()
    }

    private fun runProgram(mem: MutableList<Int>, input: List<Int>): List<Int> {
        var executionContext = ExecutionContext(0, mem, input, listOf(), false)

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
