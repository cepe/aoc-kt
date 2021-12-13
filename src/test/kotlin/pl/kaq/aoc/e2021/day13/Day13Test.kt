package pl.kaq.aoc.e2021.day13

import org.assertj.core.api.Assertions
import org.junit.Test

class Day13Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day13().firstStar()).isEqualTo(842)
    }

    @Test
    fun secondStar() {
        val expected = listOf(
            "███  ████ █  █ ███   ██    ██ ████ █  █",
            "█  █ █    █ █  █  █ █  █    █    █ █  █",
            "███  ███  ██   █  █ █       █   █  █  █",
            "█  █ █    █ █  ███  █       █  █   █  █",
            "█  █ █    █ █  █ █  █  █ █  █ █    █  █",
            "███  █    █  █ █  █  ██   ██  ████  ██ "
        ).joinToString("\n")

        Assertions.assertThat(Day13().secondStar()).isEqualTo(expected)
    }
}
