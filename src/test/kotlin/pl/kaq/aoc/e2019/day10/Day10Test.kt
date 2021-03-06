package pl.kaq.aoc.e2019.day10

import org.assertj.core.api.Assertions
import org.junit.Test

class Day10Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day10().firstStar()).isEqualTo(282)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day10().secondStar()).isEqualTo(1008)
    }
}
