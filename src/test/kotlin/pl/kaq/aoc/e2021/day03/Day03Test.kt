package pl.kaq.aoc.e2021.day03

import org.assertj.core.api.Assertions
import org.junit.Test

class Day03Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day03().firstStar()).isEqualTo(1540244)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day03().secondStar()).isEqualTo(4203981)
    }
}
