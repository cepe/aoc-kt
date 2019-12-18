package ml.szloch.aoc.e2019.day12

import org.assertj.core.api.Assertions
import org.junit.Test

class Day12Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day12().firstStar()).isEqualTo(7471)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day12().secondStar()).isEqualTo(376243355967784)
    }
}
