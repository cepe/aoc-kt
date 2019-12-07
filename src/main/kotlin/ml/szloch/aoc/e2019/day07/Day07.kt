package ml.szloch.aoc.e2019.day07

import ml.szloch.aoc.AoC
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random

data class VM(
    val id: Int,
    var ip: Int,
    var iop: Int,
    val mem: MutableList<Int>,
    val input: Vector<Int>,
    val output: Vector<Int>,
    var halted: Boolean
)

interface Operation {
    fun execute(vm: VM)
    fun opValue(mem: List<Int>, address: Int, mode: Boolean): Int = if (mode) address else mem[address]
    fun op1(vm: VM, mode: Boolean = false) =
        opValue(vm.mem, vm.mem[vm.ip + 1], mode || (vm.mem[vm.ip] / 100) % 10 == 1)

    fun op2(vm: VM): Int = opValue(vm.mem, vm.mem[vm.ip + 2], vm.mem[vm.ip] / 100 >= 10)
    fun op3(vm: VM): Int = opValue(vm.mem, vm.mem[vm.ip + 3], true)
}

class AddOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[op3(vm)] = op1(vm) + op2(vm); vm.ip += 4
    }
}

class MulOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[op3(vm)] = op1(vm) * op2(vm); vm.ip += 4
    }
}

class ReadOp : Operation {
    override fun execute(vm: VM) {
        while (vm.input.size - 1 < vm.iop) {
            Thread.sleep(0)
        }
        vm.mem[op1(vm, true)] = vm.input[vm.iop]; vm.iop += 1; vm.ip += 2
    }
}

class WriteOp : Operation {
    override fun execute(vm: VM) {
        synchronized(vm.output) {
            vm.output.add(op1(vm)); vm.ip += 2
        }
    }
}

class JumpIfTrueOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) != 0) op2(vm) else vm.ip + 3
    }
}

class JumpIfFalseOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) == 0) op2(vm) else vm.ip + 3
    }
}

class LessThanOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[op3(vm)] = if (op1(vm) < op2(vm)) 1 else 0; vm.ip += 4
    }
}

class EqualsOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[op3(vm)] = if (op1(vm) == op2(vm)) 1 else 0; vm.ip += 4
    }
}

class HaltOp : Operation {
    override fun execute(vm: VM) {
        vm.halted = true
    }
}

private fun List<Int>.perms(): Sequence<List<Int>> {
    val list = this

    return sequence {
        if (list.isEmpty()) {
            yield(listOf())
        } else {
            for (elem in list) {
                for (tailPerm in list.filter { it != elem }.perms()) {
                    yield(listOf(elem).plus(tailPerm))
                }
            }
        }
    }
}

class Day07 : AoC<Int?, Int?> {


    override fun firstStar(): Int? {
        val memory = readMemory()

        fun runProgram(input: Vector<Int>): Int {
            val mutableMemory = memory.toMutableList()
            val output = Vector<Int>()

            runProgram(mutableMemory, input, output)
            return output.last()
        }

        val perms = listOf(0, 1, 2, 3, 4).perms()

        return perms.map {
            run {
                val a1Out = runProgram(Vector(mutableListOf(it[0], 0)))
                val a2Out = runProgram(Vector(mutableListOf(it[1], a1Out)))
                val a3Out = runProgram(Vector(mutableListOf(it[2], a2Out)))
                val a4Out = runProgram(Vector(mutableListOf(it[3], a3Out)))
                val a5Out = runProgram(Vector(mutableListOf(it[4], a4Out)))
                a5Out
            }
        }.max()
    }

    private fun readMemory(): List<Int> {
        return inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toInt)
    }

    override fun secondStar(): Int? {
        val memory = readMemory()

        fun runProgram(input: Vector<Int>, output: Vector<Int>) {
            val mutableMemory = memory.toMutableList()
            runProgram(mutableMemory, input, output)
        }

        val perms = listOf(5, 6, 7, 8, 9).perms()

        return perms.toList().map {
            run {
                val a1Input = Vector(mutableListOf(it[0], 0))
                val a2Input = Vector(mutableListOf(it[1]))
                val a3Input = Vector(mutableListOf(it[2]))
                val a4Input = Vector(mutableListOf(it[3]))
                val a5Input = Vector(mutableListOf(it[4]))
                thread { runProgram(a1Input, a2Input) }
                thread { runProgram(a2Input, a3Input) }
                thread { runProgram(a3Input, a4Input) }
                thread { runProgram(a4Input, a5Input) }
                thread { runProgram(a5Input, a1Input) }.join()

                a1Input.last()
            }
        }.max()
    }


    private fun runProgram(mem: MutableList<Int>, input: Vector<Int>, output: Vector<Int>) {
        val state = VM(Random.nextInt(0, 100), 0, 0, mem, input, output, false)

        while (!state.halted) {
            currentOperation(state).execute(state)
        }
    }

    private fun currentOperation(vm: VM): Operation {
        return when (vm.mem[vm.ip] % 100) {
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
                throw IllegalStateException()
            }
        }
    }
}

fun main() {
    Day07().solve()
}
