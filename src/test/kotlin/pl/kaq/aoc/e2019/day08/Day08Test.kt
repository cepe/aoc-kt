package pl.kaq.aoc.e2019.day08

import org.assertj.core.api.Assertions
import org.junit.Test

class Day08Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day08().firstStar()).isEqualTo(1441)
    }

    @Test
    fun secondStar() {
        val expected = listOf(
            "███  █  █ ████ ███  ███  ",
            "█  █ █  █    █ █  █ █  █ ",
            "█  █ █  █   █  ███  █  █ ",
            "███  █  █  █   █  █ ███  ",
            "█ █  █  █ █    █  █ █    ",
            "█  █  ██  ████ ███  █    "
        ).joinToString("\n")

        Assertions.assertThat(Day08().secondStar()).isEqualTo(expected)
    }
}
