package ml.szloch.aoc.e2015.day1

import org.assertj.core.api.Assertions
import org.junit.Test

class Day1Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day1().firstStar()).isEqualTo(74)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day1().secondStar()).isEqualTo(1795)
    }
}