package ml.szloch.aoc.e2019.day06

import ml.szloch.aoc.AoC
import java.util.*

class Day06 : AoC<Int, Int> {

    override fun firstStar(): Int {

        val galaxyMap = inputLines()
            .map { it.split(")") }
            .map { Pair(it[0], it[1]) }

        val galaxy = mutableMapOf<String, String>()

        galaxyMap.forEach {
            galaxy[it.second] = it.first
        }

        return galaxy.keys.map { obj -> countOrbits(galaxy, obj) }.sum()

    }

    private fun countOrbits(galaxy: Map<String, String>, obj: String): Int {
        var mutableObj = obj
        var counter = 0

        while (mutableObj != "COM") {
            mutableObj = galaxy[mutableObj] ?: error("Should never happen!")
            counter += 1
        }

        return counter
    }

    override fun secondStar(): Int {
        val galaxyMap = inputLines()
            .map { it.split(")") }
            .map { Pair(it[0], it[1]) }

        val galaxy = mutableMapOf<String, MutableList<String>>()

        galaxyMap.forEach {
            galaxy.putIfAbsent(it.first, mutableListOf())
            galaxy.putIfAbsent(it.second, mutableListOf())

            galaxy[it.first]!!.add(it.second)
            galaxy[it.second]!!.add(it.first)
        }

        return countOrbitalTransfersToSanta(galaxy)

    }

    private fun countOrbitalTransfersToSanta(galaxy: Map<String, List<String>>): Int {
        val visited = mutableSetOf<String>()
        val stack = Stack<Pair<String, Int>>()

        stack.add(Pair("YOU", -2))

        while (!stack.empty()) {
            val current = stack.pop()

            if (current.first == "SAN") {
                return current.second
            }

            visited.add(current.first)

            galaxy[current.first]?.forEach {
                if (it !in visited) {
                    stack.push(Pair(it, current.second + 1))
                }
            }
        }

        throw IllegalStateException("Should never happen!")
    }

}

fun main() {
    Day06().solve()
}
