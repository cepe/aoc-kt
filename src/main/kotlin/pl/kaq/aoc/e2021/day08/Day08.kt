package pl.kaq.aoc.e2021.day08

import pl.kaq.aoc.AoC
import pl.kaq.aoc.perms
import java.util.*

class Day08 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return inputLines()
            .map { it.split(" | ")[1].split(" ") }
            .sumOf { line -> line.filter { it.length in listOf(2, 4, 3, 7) }.size }
    }

    override fun secondStar(): Int {
        val notes = inputLines()
            .map { it.split(" | ") }
            .map { split ->
                Pair(
                    split[0].split(" ").map { it.toSortedSet() },
                    split[1].split(" ").map { it.toSortedSet() }
                )
            }

        return notes.sumOf(this::findRightNumber)
    }

    private fun findRightNumber(note: Pair<List<SortedSet<Char>>, List<SortedSet<Char>>>): Int {
        val digits = mapOf(
            '0' to listOf(1, 2, 3, 5, 6, 7),
            '1' to listOf(3, 6),
            '2' to listOf(1, 3, 4, 5, 7),
            '3' to listOf(1, 3, 4, 6, 7),
            '4' to listOf(2, 3, 4, 6),
            '5' to listOf(1, 2, 4, 6, 7),
            '6' to listOf(1, 2, 4, 5, 6, 7),
            '7' to listOf(1, 3, 6),
            '8' to listOf(1, 2, 3, 4, 5, 6, 7),
            '9' to listOf(1, 2, 3, 4, 6, 7)
        )

        for (perm in "abcdefg".toList().perms()) {
            val mapping = digits
                .map { (k, v) -> v.map { perm[it - 1] }.toSortedSet() to k }
                .toMap()

            val (left, right) = note

            if (left.all { it in mapping }) {
                return right.map { mapping.getValue(it) }.joinToString("").toInt()
            }
        }

        throw IllegalStateException()
    }


}

fun main() {
    Day08().solve()
}
