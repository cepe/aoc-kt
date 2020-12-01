package pl.kaq.aoc.e2019.day08

import pl.kaq.aoc.AoC

class Day08 : AoC<Int, String> {

    override fun firstStar(): Int {
        val layer = inputTrimmed()
            .chunked(25 * 6)
            .minBy { it.count { c -> c == '0' } }
        return layer!!.count { it == '1' } * layer.count { it == '2' }
    }

    override fun secondStar(): String {
        val layers = inputTrimmed()
            .chunked(25 * 6)

        val transparentLayer = "2".repeat(25 * 6)

        return layers
            .fold(transparentLayer) { l1, l2 -> l1.zip(l2) { a, b -> if (a == '2') b else a }.joinToString("") }
            .replace('1', '█')
            .replace('0', ' ')
            .chunked(25)
            .joinToString("\n")
    }
}

fun main() {
    Day08().solve()
}
