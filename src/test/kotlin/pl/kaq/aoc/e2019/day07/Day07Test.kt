package pl.kaq.aoc.e2019.day07

import org.assertj.core.api.Assertions
import org.junit.Test

class Day07Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day07().firstStar()).isEqualTo(368584)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day07().secondStar()).isEqualTo(35993240)
    }
}
