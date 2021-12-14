package pl.kaq.aoc.e2021.day14

import org.assertj.core.api.Assertions
import org.junit.Test

class Day14Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day14().firstStar()).isEqualTo(2509)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day14().secondStar()).isEqualTo(2827627697643)
    }
}
