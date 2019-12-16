package ml.szloch.aoc.e2019.day11

import ml.szloch.aoc.AoC
import ml.szloch.aoc.e2019.VM
import ml.szloch.aoc.e2019.VMInput
import ml.szloch.aoc.e2019.VMOutput


class Day11 : AoC<Int, String> {

    enum class Direction(val x: Long, val y: Long) {
        UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);

        fun left(): Direction {
            return when (this) {
                UP -> LEFT
                DOWN -> RIGHT
                LEFT -> DOWN
                RIGHT -> UP
            }
        }

        fun right(): Direction {
            return when (this) {
                UP -> RIGHT
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
            }
        }

    }

    class RobotIO(val board: MutableMap<Pair<Long, Long>, Long> = mutableMapOf()) : VMInput, VMOutput {

        private fun Pair<Long, Long>.move(direction: Direction): Pair<Long, Long> =
            Pair(this.first + direction.x, this.second + direction.y)

        var position: Pair<Long, Long> = Pair(0, 0)
        var direction = Direction.UP
        var colorPrinting = true

        override fun hasNext(): Boolean {
            return true
        }

        override fun next(): Long = board.getOrDefault(position, 0)

        override fun print(v: Long) {
            if (colorPrinting) {
                board[position] = v
                colorPrinting = false
            } else {
                when (v) {
                    0L -> {
                        direction = direction.left()
                        position = position.move(direction)
                    }
                    1L -> {
                        direction = direction.right()
                        position = position.move(direction)
                    }
                    else -> throw IllegalStateException()
                }
                colorPrinting = true
            }
        }

        override fun last(): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun firstStar(): Int {
        val io = RobotIO()
        val vm = VM(readMemory(), io, io)
        vm.startExecution()
        return io.board.size
    }

    override fun secondStar(): String {
        val io = RobotIO(mutableMapOf(Pair(Pair(0L, 0L), 1L)))
        val vm = VM(readMemory(), io, io)
        vm.startExecution()
        val minX = io.board.keys.map { it.first }.min()!!
        val maxX = io.board.keys.map { it.first }.max()!!
        val minY = io.board.keys.map { it.second }.min()!!
        val maxY = io.board.keys.map { it.second }.max()!!

        val sb: StringBuilder = StringBuilder()
        for (y in maxY downTo minY) {
            for (x in minX..maxX) {
                if (io.board.getOrDefault(Pair(x, y), 0) == 0L) {
                    sb.append(' ')
                } else {
                    sb.append('█')
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    private fun readMemory(): MutableMap<Long, Long> {
        return inputTrimmed()
            .split(",")
            .map(String::trim)
            .map(String::toLong)
            .mapIndexed { i, e -> i.toLong() to e }
            .toMap()
            .toMutableMap()
    }

}


fun main() {
    Day11().solve()
}
