package pl.kaq.aoc.e2021.day01

import org.assertj.core.api.Assertions
import org.junit.Test

class Day01Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day01().firstStar()).isEqualTo(1292)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day01().secondStar()).isEqualTo(1262)
    }
}
