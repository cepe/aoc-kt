package ml.szloch.aoc.e2019.day09

import org.assertj.core.api.Assertions
import org.junit.Test

class Day09Test {

    @Test
    fun firstStar() {
        Assertions.assertThat(Day09().firstStar()).isEqualTo(3429606717)
    }

    @Test
    fun secondStar() {
        Assertions.assertThat(Day09().secondStar()).isEqualTo(33679)
    }
}
