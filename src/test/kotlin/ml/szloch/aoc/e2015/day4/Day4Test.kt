package ml.szloch.aoc.e2015.day4

import org.assertj.core.api.Assertions
import org.junit.Test

class Day4Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day4().firstStar()).isEqualTo(346386)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day4().secondStar()).isEqualTo(9958218)
    }
}