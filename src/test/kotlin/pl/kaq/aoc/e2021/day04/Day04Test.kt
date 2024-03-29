package pl.kaq.aoc.e2021.day04

import org.assertj.core.api.Assertions
import org.junit.Test

class Day04Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day04().firstStar()).isEqualTo(39984)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day04().secondStar()).isEqualTo(8468)
    }
}
