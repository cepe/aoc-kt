package ml.szloch.aoc.e2019.day08

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
            "1110010010111101110011100",
            "1001010010000101001010010",
            "1001010010001001110010010",
            "1110010010010001001011100",
            "1010010010100001001010000",
            "1001001100111101110010000"
        ).joinToString("\n")

        Assertions.assertThat(Day08().secondStar()).isEqualTo(expected)
    }
}
