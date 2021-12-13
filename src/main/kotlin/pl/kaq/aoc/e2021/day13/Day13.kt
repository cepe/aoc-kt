package pl.kaq.aoc.e2021.day13

import pl.kaq.aoc.AoC

typealias Page = Set<Pair<Int, Int>>

class Day13 : AoC<Int, String> {

    override fun firstStar(): Int {
        val inputLines = inputLines()
        val page = parsePage(inputLines)
        val folds = parseFolds(inputLines)

        return folds[0](page).size
    }

    override fun secondStar(): String {
        val inputLines = inputLines()
        val page = parsePage(inputLines)
        val folds = parseFolds(inputLines)

        return folds.fold(page) { p, fold -> fold(p) }.stringRepresentation()
    }

    private fun Page.stringRepresentation(): String {
        val maxX = this.maxOf { it.first }
        val maxY = this.maxOf { it.second }

        return (0..maxY).joinToString("\n") { y ->
            (0..maxX)
                .map { x -> if (Pair(x, y) in this) '█' else ' ' }
                .joinToString("")
        }

    }

    private fun parseFolds(inputLines: List<String>) = inputLines
        .asSequence()
        .filter { it.startsWith("fold") }
        .map { it.split(" ")[2] }
        .map { it.split("=") }
        .map { Pair(it[0], it[1].toInt()) }
        .map(::makeFold)
        .toList()

    private fun parsePage(inputLines: List<String>) = inputLines
        .asSequence()
        .filter { !it.startsWith("fold") }
        .filter { it != "" }
        .map { it.split(",") }
        .map { Pair(it[0].toInt(), it[1].toInt()) }
        .toSet()

    private fun makeFold(foldDef: Pair<String, Int>): (Page) -> Page {
        val cordFold = { cord: Pair<Int, Int> ->
            val (x, y) = cord
            when (foldDef.first) {
                "x" -> if (x > foldDef.second) Pair(2 * foldDef.second - x, y) else cord
                else -> if (y > foldDef.second) Pair(x, 2 * foldDef.second - y) else cord
            }
        }

        return { page: Page -> page.map(cordFold).toSet() }
    }

}

fun main() {
    Day13().solve()
}
