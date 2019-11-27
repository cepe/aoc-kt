package ml.szloch.aoc.e2015.day5

import ml.szloch.aoc.AoC


class Day5 : AoC<Int, Int> {
    private val letters = "abcdefghijklmnopqrstuvwxyz"
    private val vowels = "aeiou"

    override fun firstStar(): Int {

        val containsThreeVowels: (String) -> Boolean =
            { string -> string.filter { char -> char in vowels }.length >= 3 }

        val containsLetterTwice: (String) -> Boolean =
            { string: String -> letters.map { char -> (char.toString() + char.toString()) in string }.any { it } }

        val containsForbidden: (String) -> Boolean =
            { string: String -> listOf("ab", "cd", "pq", "xy").map { it in string }.any { it } }

        return input().trim().split("\n")
            .map { it.trim() }
            .filter(containsThreeVowels)
            .filter(containsLetterTwice)
            .filterNot(containsForbidden)
            .size
    }

    override fun secondStar(): Int {
        val letterPairs = sequence {
            letters.forEach { a -> letters.forEach { b -> yield(a.toString() + b.toString()) } }
        }.toList()

        val containsLetterPairTwice: (String) -> Boolean = { string ->
            letterPairs.map { pair -> ".*${pair}.*${pair}.*".toRegex().containsMatchIn(string) }.any { it }
        }

        val containsLetterBetweenSame: (String) -> Boolean = { string ->
            letters.map { ".*$it.$it.*".toRegex().containsMatchIn(string) }.any { it }
        }

        return input().trim().split("\n")
            .map { it.trim() }
            .filter(containsLetterPairTwice)
            .filter(containsLetterBetweenSame)
            .size
    }

}

fun main() {
    Day5().solve()
}