package pl.kaq.aoc.e2021.day04

class BingoBoard(private val lines: List<List<Int>>) {
    private val crossed: MutableList<Int> = ArrayList();
    private var won = false;

    fun cross(num: Int) {
        if (won) {
            return;
        }
        crossed.add(num)
        checkBingo()
    }

    fun isWon(): Boolean {
        return won
    }

    private fun checkBingo() {
        for (i in 0 until 5) {
            var rowCrossed = true
            var colCrossed = true
            for (j in 0 until 5) {
                if (lines[i][j] !in crossed) {
                    rowCrossed = false;
                }
                if (lines[j][i] !in crossed) {
                    colCrossed = false
                }
            }
            if (rowCrossed or colCrossed) {
                won = true
            }
        }
    }

    fun sumOfUncrossed(): Int {
        var sum = 0
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (lines[i][j] !in crossed) {
                    sum += lines[i][j]
                }
            }
        }
        return sum
    }
}