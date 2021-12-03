package pl.kaq.aoc.e2019.day07

import pl.kaq.aoc.AoC
import pl.kaq.aoc.e2019.VM
import pl.kaq.aoc.e2019.VectorIO
import java.util.*
import kotlin.concurrent.thread

private fun List<Long>.perms(): Sequence<List<Long>> {
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

class Day07 : AoC<Long?, Long?> {

    override fun firstStar(): Long? {
        val memory = readMemory()

        fun runProgram(input: Vector<Long>): Long {
            val mutableMemory = memory.toMutableMap()
            val output = Vector<Long>()

            VM(mutableMemory, VectorIO(input), VectorIO(output)).startExecution()
            return output.last()
        }

        val perms = listOf(0L, 1, 2, 3, 4).perms()

        return perms.map {
            run {
                val a1Out = runProgram(Vector(mutableListOf(it[0], 0)))
                val a2Out = runProgram(Vector(mutableListOf(it[1], a1Out)))
                val a3Out = runProgram(Vector(mutableListOf(it[2], a2Out)))
                val a4Out = runProgram(Vector(mutableListOf(it[3], a3Out)))
                val a5Out = runProgram(Vector(mutableListOf(it[4], a4Out)))
                a5Out
            }
        }.maxOrNull()
    }

    private fun readMemory(): Map<Long, Long> {
        return inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
    }

    override fun secondStar(): Long? {
        val memory = readMemory()

        fun runProgram(input: Vector<Long>, output: Vector<Long>) {
            val mutableMemory = memory.toMutableMap()
            VM(mutableMemory, VectorIO(input), VectorIO(output)).startExecution()
        }

        val perms = listOf(5L, 6, 7, 8, 9).perms()

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
        }.maxOrNull()
    }
}

fun main() {
    Day07().solve()
}
