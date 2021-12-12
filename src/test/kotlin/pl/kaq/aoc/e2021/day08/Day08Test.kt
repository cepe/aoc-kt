package pl.kaq.aoc.e2021.day08

import org.assertj.core.api.Assertions
import org.junit.Test

class Day08Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day08().firstStar()).isEqualTo(539)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day08().secondStar()).isEqualTo(1084606)
    }
}
