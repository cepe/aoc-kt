package ml.szloch.aoc.e2019.day1

import org.assertj.core.api.Assertions
import org.junit.Test

class Day1Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day1().firstStar()).isEqualTo(3399394)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day1().secondStar()).isEqualTo(5096223)
    }
}