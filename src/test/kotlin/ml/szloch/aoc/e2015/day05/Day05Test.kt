package ml.szloch.aoc.e2015.day05

import org.assertj.core.api.Assertions
import org.junit.Test

class Day05Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day05().firstStar()).isEqualTo(238)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day05().secondStar()).isEqualTo(69)
    }
}