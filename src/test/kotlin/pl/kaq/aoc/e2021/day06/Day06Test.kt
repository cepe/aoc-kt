package pl.kaq.aoc.e2021.day06

import org.assertj.core.api.Assertions
import org.junit.Test

class Day06Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day06().firstStar()).isEqualTo(385391)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day06().secondStar()).isEqualTo(1728611055389)
    }
}
