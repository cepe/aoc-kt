package ml.szloch.aoc.e2015.day3

import org.assertj.core.api.Assertions
import org.junit.Test

class Day3Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day3().firstStar()).isEqualTo(2565)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day3().secondStar()).isEqualTo(2639)
    }
}