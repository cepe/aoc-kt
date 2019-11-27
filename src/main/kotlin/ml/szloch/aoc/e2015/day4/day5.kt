package ml.szloch.aoc.e2015.day4

import ml.szloch.aoc.AoC
import java.math.BigInteger
import java.security.MessageDigest


class Day4 : AoC<Int, Int> {

    private fun String.md5(): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        return BigInteger(1, messageDigest.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    override fun firstStar(): Int {
        val secret = input().trim()
        return generateSequence(0) { it + 1 }
            .map { Pair(it, (secret + it).md5()) }
            .filter { it.second.startsWith("00000") }
            .first()
            .first
    }

    override fun secondStar(): Int {
        val secret = input().trim()
        return generateSequence(0) { it + 1 }
            .map { Pair(it, (secret + it).md5()) }
            .filter { it.second.startsWith("000000") }
            .first()
            .first
    }
}

fun main() {
    Day4().solve()
}