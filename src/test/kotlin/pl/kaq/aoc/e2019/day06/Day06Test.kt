package pl.kaq.aoc.e2019.day06

import org.assertj.core.api.Assertions
import org.junit.Test

class Day06Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day06().firstStar()).isEqualTo(315757)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day06().secondStar()).isEqualTo(481)
    }
}
