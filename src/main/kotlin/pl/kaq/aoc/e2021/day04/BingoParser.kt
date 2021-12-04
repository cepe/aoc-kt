package pl.kaq.aoc.e2021.day04

object BingoParser {

    fun parseInput(input: String): Pair<List<Int>, List<BingoBoard>> {
        val split = input.split("\n\n")
        val numbers = split[0].split(",").map { it.toInt() }
        val boards = split.drop(1).map(::parseBingoBoard)
        return Pair(numbers, boards)
    }

    private fun parseBingoBoard(boardString: String): BingoBoard {
        val lines = boardString
            .split("\n")
            .map { line ->
                line.split(" ")
                    .map(String::trim)
                    .filter(String::isNotBlank)
                    .map(String::toInt)
            }
        return BingoBoard(lines)
    }
}