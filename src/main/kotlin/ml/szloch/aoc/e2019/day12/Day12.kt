package ml.szloch.aoc.e2019.day12

import ml.szloch.aoc.AoC
import kotlin.math.abs

data class Moon(var x: Long, var y: Long, var z: Long, var vx: Long, var vy: Long, var vz: Long) {
    fun kinetic(): Long = abs(vx) + abs(vy) + abs(vz)
    fun potential(): Long = abs(x) + abs(y) + abs(z)
}

fun moon(list: List<Long>): Moon {
    return Moon(list[0], list[1], list[2], 0, 0, 0)
}


class Day12 : AoC<Long, Int> {


    override fun firstStar(): Long {
        val moons = coordinates().map { moon(it) }

        repeat(1000) {
            step(moons)
            println(moons)
        }

        return moons.map { it.potential() * it.kinetic() }.sum()
    }

    private fun coordinates(): List<List<Long>> {
        return inputLines().map {
            "<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>".toRegex().matchEntire(it)!!.groupValues.slice(1..3)
                .map(String::toLong)
        }
    }

    private fun step(moons: List<Moon>) {
        updateVelocity(moons)
        updatePosition(moons)
    }

    private fun updateVelocity(moons: List<Moon>) {
        for (m in moons) {
            for (n in moons) {
                m.vx += if (m.x < n.x) 1 else (if (m.x > n.x) -1 else 0)
                m.vy += if (m.y < n.y) 1 else (if (m.y > n.y) -1 else 0)
                m.vz += if (m.z < n.z) 1 else (if (m.z > n.z) -1 else 0)
            }
        }
    }

    private fun updatePosition(moons: List<Moon>) {
        moons.forEach {
            it.let {
                it.x += it.vx
                it.y += it.vy
                it.z += it.vz
            }
        }
    }


    override fun secondStar(): Int {
        return 0
    }

}

fun main() {
    Day12().solve()
}
