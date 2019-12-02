package ml.szloch.aoc.e2019.day2

import org.assertj.core.api.Assertions
import org.junit.Test

class Day2Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day2().firstStar()).isEqualTo(5290681)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day2().secondStar()).isEqualTo(5741)
    }
}