package pl.kaq.aoc.e2019.day12

import pl.kaq.aoc.AoC
import kotlin.math.abs

data class Moon(var x: Long, var y: Long, var z: Long, var vx: Long, var vy: Long, var vz: Long) {
    fun kinetic(): Long = abs(vx) + abs(vy) + abs(vz)
    fun potential(): Long = abs(x) + abs(y) + abs(z)
}

fun moon(list: List<Long>): Moon {
    return Moon(list[0], list[1], list[2], 0, 0, 0)
}


class Day12 : AoC<Long, Long> {


    override fun firstStar(): Long {
        val moons = coordinates().map { moon(it) }

        repeat(1000) {
            step(moons)
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

    fun gcd(a: Long, b: Long): Long = if (a == 0L) b else gcd(b % a, a)
    fun lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

    override fun secondStar(): Long {
        val moons = coordinates().map { moon(it) }

        val xs = moons.map { Pair(it.x, it.vx) }
        val ys = moons.map { Pair(it.y, it.vy) }
        val zs = moons.map { Pair(it.z, it.vz) }

        var cnt = 0L
        var xcycle = 0L
        var ycycle = 0L
        var zcycle = 0L

        var allDone = 0
        while (allDone != 7) {
            cnt += 1
            step(moons)

            if (xs == moons.map { Pair(it.x, it.vx) }) {
                allDone = allDone or 0x1
                xcycle = cnt
            }
            if (ys == moons.map { Pair(it.y, it.vy) }) {
                allDone = allDone or 0x2
                ycycle = cnt
            }
            if (zs == moons.map { Pair(it.z, it.vz) }) {
                allDone = allDone or 0x4
                zcycle = cnt
            }

        }

        return lcm(lcm(xcycle, ycycle), zcycle)
    }

}

fun main() {
    Day12().secondStar()
    Day12().solve()
}
