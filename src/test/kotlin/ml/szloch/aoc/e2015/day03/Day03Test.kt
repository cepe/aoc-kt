package ml.szloch.aoc.e2015.day03

import org.assertj.core.api.Assertions
import org.junit.Test

class Day03Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day03().firstStar()).isEqualTo(2565)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day03().secondStar()).isEqualTo(2639)
    }
}