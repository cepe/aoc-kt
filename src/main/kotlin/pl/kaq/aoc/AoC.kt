package pl.kaq.aoc

import java.io.File
import java.util.*

interface AoC<FS, SS> {

    fun input(fileName: String = "input.txt"): String {
        val inputDirectory = this.javaClass.`package`.name.replace('.', '/')
            .lowercase(Locale.getDefault())
        val resourcePath = "/${inputDirectory}/$fileName"
        return File(this::class.java.getResource(resourcePath).toURI()).readText()
    }

    fun inputTrimmed(fileName: String = "input.txt") = input(fileName).trim()
    fun inputLines(fileName: String = "input.txt") = inputTrimmed(fileName).split("\n").map { it.trim() }

    fun firstStar(): FS
    fun secondStar(): SS
    fun solve() {
        println(firstStar())
        println(secondStar())
    }

}
