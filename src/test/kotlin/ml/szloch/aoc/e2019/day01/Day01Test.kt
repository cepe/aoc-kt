package ml.szloch.aoc.e2019.day01

import org.assertj.core.api.Assertions
import org.junit.Test

class Day01Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day01().firstStar()).isEqualTo(3399394)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day01().secondStar()).isEqualTo(5096223)
    }
}