package pl.kaq.aoc.e2021.day04

object BingoSimulator {

    fun simulateBingo(numbers: List<Int>, boards: List<BingoBoard>): List<Int> {
        val scores = ArrayList<Int>()
        for (number in numbers) {
            for (board in boards) {
                if (board.isWon()) {
                    continue
                }

                board.cross(number)
                if (board.isWon()) {
                    scores.add(board.sumOfUncrossed() * number)
                }
            }
        }
        return scores
    }
}