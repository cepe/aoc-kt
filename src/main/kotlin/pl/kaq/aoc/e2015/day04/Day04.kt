package pl.kaq.aoc.e2015.day04

import pl.kaq.aoc.AoC
import java.math.BigInteger
import java.security.MessageDigest


class Day04 : AoC<Int, Int> {

    private fun String.md5(): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        return BigInteger(1, messageDigest.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    override fun firstStar(): Int {
        val secret = inputTrimmed()
        return generateSequence(0) { it + 1 }
            .map { Pair(it, (secret + it).md5()) }
            .filter { it.second.startsWith("00000") }
            .first()
            .first
    }

    override fun secondStar(): Int {
        val secret = inputTrimmed()
        return generateSequence(0) { it + 1 }
            .map { Pair(it, (secret + it).md5()) }
            .filter { it.second.startsWith("000000") }
            .first()
            .first
    }
}

fun main() {
    Day04().solve()
}