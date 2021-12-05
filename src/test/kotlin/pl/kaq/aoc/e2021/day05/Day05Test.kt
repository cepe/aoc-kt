package pl.kaq.aoc.e2021.day05

import org.assertj.core.api.Assertions
import org.junit.Test

class Day05Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day05().firstStar()).isEqualTo(7297)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day05().secondStar()).isEqualTo(21038)
    }
}
