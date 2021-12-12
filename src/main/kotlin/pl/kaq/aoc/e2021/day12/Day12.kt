package pl.kaq.aoc.e2021.day12

import pl.kaq.aoc.AoC

typealias Graph = Map<String, List<String>>

class Day12 : AoC<Int, Int> {

    override fun firstStar(): Int {
        return countPaths(parseCaveGraph(inputLines()), "start", setOf("start"), true)
    }

    override fun secondStar(): Int {
        return countPaths(parseCaveGraph(inputLines()), "start", setOf("start"), false)
    }

    private fun countPaths(caveGraph: Graph, cave: String, seenCaves: Set<String>, singleCaveVisited: Boolean): Int {
        if (cave == "end") {
            return 1
        }

        var counter = 0

        val caveNeighborhood = caveGraph.getValue(cave)

        for (neighborCave in caveNeighborhood) {
            if (neighborCave == neighborCave.lowercase()) {
                if (neighborCave != "start") {
                    if (neighborCave in seenCaves && !singleCaveVisited) {
                        counter += countPaths(caveGraph, neighborCave, seenCaves, true)
                    }
                    if (neighborCave !in seenCaves) {
                        counter += countPaths(caveGraph, neighborCave, seenCaves.plus(neighborCave), singleCaveVisited)
                    }
                }
            } else {
                counter += countPaths(caveGraph, neighborCave, seenCaves, singleCaveVisited)
            }
        }


        return counter
    }

    private fun parseCaveGraph(lines: List<String>) = lines
        .map { it.split("-") }
        .map { Pair(it[0], it[1]) }
        .flatMap { listOf(Pair(it.first, it.second), Pair(it.second, it.first)) }
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second } }

}

fun main() {
    Day12().solve()
}
