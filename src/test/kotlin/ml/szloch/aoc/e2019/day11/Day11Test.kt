package ml.szloch.aoc.e2019.day11

import org.assertj.core.api.Assertions
import org.junit.Test

class Day11Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day11().firstStar()).isEqualTo(1894)
    }

    @Test
    fun secondStar() {
        val expected = listOf(
            "   ██ █  █ ████ █    ████   ██ ███  █  █   ",
            "    █ █ █     █ █       █    █ █  █ █  █   ",
            "    █ ██     █  █      █     █ ███  ████   ",
            "    █ █ █   █   █     █      █ █  █ █  █   ",
            " █  █ █ █  █    █    █    █  █ █  █ █  █   ",
            "  ██  █  █ ████ ████ ████  ██  ███  █  █   \n"
        ).joinToString("\n")
        Assertions.assertThat(Day11().secondStar()).isEqualTo(expected)
    }
}
