package ml.szloch.aoc.e2015.day04

import org.assertj.core.api.Assertions
import org.junit.Test

class Day04Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day04().firstStar()).isEqualTo(346386)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day04().secondStar()).isEqualTo(9958218)
    }
}