package ml.szloch.aoc.e2020.day01

import org.assertj.core.api.Assertions
import org.junit.Test

class Day01Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day01().firstStar()).isEqualTo(974304)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day01().secondStar()).isEqualTo(236430480)
    }
}
