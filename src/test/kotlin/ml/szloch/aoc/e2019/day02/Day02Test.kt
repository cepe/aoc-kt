package ml.szloch.aoc.e2019.day02

import org.assertj.core.api.Assertions
import org.junit.Test

class Day02Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day02().firstStar()).isEqualTo(5290681)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day02().secondStar()).isEqualTo(5741)
    }
}