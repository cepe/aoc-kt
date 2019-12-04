package ml.szloch.aoc.e2015.day02

import org.assertj.core.api.Assertions
import org.junit.Test

class Day02Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day2().firstStar()).isEqualTo(1586300)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day2().secondStar()).isEqualTo(3737498)
    }
}