package pl.kaq.aoc.e2019.day13

import pl.kaq.aoc.AoC
import pl.kaq.aoc.e2019.VM
import pl.kaq.aoc.e2019.VMInput
import pl.kaq.aoc.e2019.VMOutput
import pl.kaq.aoc.e2019.VectorIO

class Day13 : AoC<Int, Long> {

    class GameIO : VMInput, VMOutput {
        private var phase: Int = 0
        private var board = mutableMapOf<Pair<Long, Long>, Long>()

        private var x: Long = 0
        private var y: Long = 0
        var score: Long = 0

        override fun hasNext(): Boolean {
            return true
        }

        override fun next(): Long {
            var ballPosition = Pair(0L, 0L)
            var paddlePosition = Pair(0L, 0L)
            for ((k, v) in board) {
                if (v == 4L) {
                    ballPosition = k
                }
                if (v == 3L) {
                    paddlePosition = k
                }
            }
            if (paddlePosition.first < ballPosition.first) {
                return 1
            }
            if (paddlePosition.first > ballPosition.first) {
                return -1
            }
            return 0
        }

        override fun print(v: Long) {
            if (phase == 0) {
                x = v
            }
            if (phase == 1) {
                y = v
            }
            if (phase == 2) {
                board[Pair(x, y)] = v
                if (x == -1L && y == 0L) {
                    score = v
                }
            }

            phase += 1
            phase %= 3
        }

        override fun last(): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun firstStar(): Int {
        val memory = readMemory().toMutableMap()
        val output = VectorIO()
        VM(memory, VectorIO(), output).startExecution()
        return output.vec.chunked(3).map { it[2] }.count { it == 2L }
    }

    private fun readMemory(): Map<Long, Long> {
        return inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
    }

    override fun secondStar(): Long {
        val memory = readMemory().toMutableMap()
        memory[0] = 2
        val gameIO = GameIO()
        VM(memory, gameIO, gameIO).startExecution()
        return gameIO.score
    }

}

fun main() {
    Day13().solve()
}
