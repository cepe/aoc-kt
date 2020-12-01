package pl.kaq.aoc.e2019.day13

import org.assertj.core.api.Assertions
import org.junit.Test

class Day13Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day13().firstStar()).isEqualTo(414)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day13().secondStar()).isEqualTo(20183)
    }
}
