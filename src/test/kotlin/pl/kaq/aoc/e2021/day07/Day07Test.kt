package pl.kaq.aoc.e2021.day07

import org.assertj.core.api.Assertions
import org.junit.Test

class Day07Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day07().firstStar()).isEqualTo(352254)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day07().secondStar()).isEqualTo(99053143)
    }
}
