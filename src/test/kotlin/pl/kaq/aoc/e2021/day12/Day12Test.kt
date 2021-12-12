package pl.kaq.aoc.e2021.day12

import org.assertj.core.api.Assertions
import org.junit.Test

class Day12Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day12().firstStar()).isEqualTo(4167)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day12().secondStar()).isEqualTo(98441)
    }
}
