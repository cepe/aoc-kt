package ml.szloch.aoc.e2015.day5

import org.assertj.core.api.Assertions
import org.junit.Test

class Day5Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day5().firstStar()).isEqualTo(238)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day5().secondStar()).isEqualTo(69)
    }
}