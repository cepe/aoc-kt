package ml.szloch.aoc.e2019

import java.util.*

class VM(val mem: MutableList<Int>, val input: Vector<Int>, val output: Vector<Int>) {
    var ip = 0
    var iop = 0
    var halted = false

    fun startExecution(): VM {
        while (!halted) {
            currentOperation().execute(this)
        }
        return this
    }

    private fun currentOperation(): Operation {
        return when (mem[ip] % 100) {
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
