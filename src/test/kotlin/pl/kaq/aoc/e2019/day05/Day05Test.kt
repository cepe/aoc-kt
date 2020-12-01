package pl.kaq.aoc.e2019.day05

import org.assertj.core.api.Assertions
import org.junit.Test

class Day05Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day05().firstStar()).isEqualTo(16225258)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day05().secondStar()).isEqualTo(2808771)
    }
}
