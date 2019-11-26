package ml.szloch.aoc

import java.io.File

interface AoC<FS, SS> {

    fun input(fileName: String = "input.txt"): String {
        val inputDirectory = this.javaClass.`package`.name.replace('.', '/').toLowerCase()
        val resourcePath = "/${inputDirectory}/$fileName"
        return File(this::class.java.getResource(resourcePath).toURI()).readText()
    }

    fun firstStar(): FS
    fun secondStar(): SS
    fun solve() {
        println(firstStar())
        println(secondStar())
    }

}
