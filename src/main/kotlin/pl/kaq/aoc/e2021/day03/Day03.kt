package pl.kaq.aoc.e2021.day03

import pl.kaq.aoc.AoC

class Day03 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val lines = inputLines()

        val counters = (0 until lines[0].length)
            .map { idx -> lines.map { it[idx] } }
            .map { col ->
                col
                    .groupingBy { it }
                    .eachCount()
                    .withDefault { 0 }
            }

        val gamma = counters.map { if (it['0']!! < it['1']!!) 1 else 0 }
        val epsilon = gamma.map { 1 - it }

        return gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2)
    }

    override fun secondStar(): Int {
        val lines = inputLines()

        val mostCommon: (col: String) -> Char = { col ->
            val counter = col.groupingBy { it }.eachCount().withDefault { 0 }
            if (counter['1']!! >= counter['0']!!) '1' else '0'
        }

        val leastCommon: (col: String) -> Char = { col ->
            val counter = col.groupingBy { it }.eachCount().withDefault { 0 }
            if (counter['0']!! <= counter['1']!!) '0' else '1'
        }

        fun filterLines(lines: List<String>, idx: Int, criteria: (String) -> Char): String {
            if (lines.size == 1) {
                return lines.first()
            }

            val expectedBit = criteria(lines.map { it[idx] }.toString())

            return filterLines(lines.filter { it[idx] == expectedBit }, idx + 1, criteria)
        }

        val oxygen = filterLines(lines, 0, mostCommon)
        val carbon = filterLines(lines, 0, leastCommon)

        return oxygen.toInt(2) * carbon.toInt(2)
    }

}

fun main() {
    Day03().solve()
}
