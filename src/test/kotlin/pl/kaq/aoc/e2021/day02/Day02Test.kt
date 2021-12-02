package pl.kaq.aoc.e2021.day02

import org.assertj.core.api.Assertions
import org.junit.Test

class Day02Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day02().firstStar()).isEqualTo(1882980)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day02().secondStar()).isEqualTo(1971232560)
    }
}
