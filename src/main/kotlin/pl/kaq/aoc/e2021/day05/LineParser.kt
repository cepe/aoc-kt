package pl.kaq.aoc.e2021.day05

object LineParser {
    fun parseLine(line: String): Line {
        val (left, right) = line.split("->").map(String::trim)
        val (x1, y1) = left.split(",")
        val (x2, y2) = right.split(",")
        return Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
    }
}