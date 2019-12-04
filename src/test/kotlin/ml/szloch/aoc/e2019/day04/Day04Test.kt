package ml.szloch.aoc.e2019.day04

import org.assertj.core.api.Assertions
import org.junit.Test

class Day04Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day04().firstStar()).isEqualTo(511)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day04().secondStar()).isEqualTo(316)
    }
}
