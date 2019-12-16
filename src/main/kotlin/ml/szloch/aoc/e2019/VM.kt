package ml.szloch.aoc.e2019

import java.util.*

interface VMInput {
    fun hasNext(): Boolean
    fun next(): Long
}

interface VMOutput {
    fun print(v: Long)
    fun last(): Long
}

class VectorIO(private val vec: Vector<Long> = Vector()) : VMInput, VMOutput {

    private var idx = 0

    override fun hasNext(): Boolean {
        return idx < vec.size
    }

    override fun next(): Long {
        val toReturn = vec[idx]
        idx += 1
        return toReturn
    }

    override fun print(v: Long) {
        vec.add(v)
    }

    override fun last(): Long = vec.last()

}

class VM(val mem: MutableMap<Long, Long>, val input: VMInput, val output: VMOutput) {
    var ip = 0L
    var rb = 0L
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
            9L -> AdjustRelativeBaseOp()
            99L -> HaltOp()
            else -> {
                throw IllegalStateException()
            }
        }
    }
}
