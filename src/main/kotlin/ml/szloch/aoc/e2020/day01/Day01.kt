package ml.szloch.aoc.e2020.day01

import ml.szloch.aoc.AoC

class Day01 : AoC<Int, Int> {

    override fun firstStar(): Int {
        val nums = inputLines().map(String::toInt)
        for (i in 1 until nums.size)
            for (j in 1 until nums.size)
                if (nums[i] + nums[j] == 2020)
                    return nums[i] * nums[j]
        throw IllegalStateException("Should never happen!")
    }

    override fun secondStar(): Int {
        val nums = inputLines().map(String::toInt)
        for (i in 1 until nums.size)
            for (j in 1 until nums.size)
                for (k in 1 until nums.size) {
                    if (nums[i] + nums[j] + nums[k] == 2020)
                        return nums[i] * nums[j] * nums[k]
                }
        throw IllegalStateException("Should never happen!")
    }

}

fun main() {
    Day01().solve()
}
