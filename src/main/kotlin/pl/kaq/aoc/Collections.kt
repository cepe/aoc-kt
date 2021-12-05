package pl.kaq.aoc


fun <T> Iterable<T>.counted(): Map<T, Int> {
    return this.groupingBy { it }.eachCount()
}

fun <T> Iterable<T>.counters(): List<Int> {
    return this.groupingBy { it }.eachCount().map { it.value }
}
