package ml.szloch.aoc.e2019

import java.util.*

class VM(val mem: MutableMap<Long, Long>, val input: Vector<Long>, val output: Vector<Long>) {
    var ip = 0L
    var iop = 0
    var halted = false

    fun startExecution(): VM {
        while (!halted) {
            currentOperation().execute(this)
        }
        return this
    }

    fun memAt(pos: Long) = mem.getOrDefault(pos, 0)

    private fun currentOperation(): Operation {
        return when (memAt(ip) % 100L) {
            1L -> AddOp()
            2L -> MulOp()
            3L -> ReadOp()
            4L -> WriteOp()
            5L -> JumpIfTrueOp()
            6L -> JumpIfFalseOp()
            7L -> LessThanOp()
            8L -> EqualsOp()
            99L -> HaltOp()
            else -> {
                throw IllegalStateException()
            }
        }
    }
}
