package pl.kaq.aoc


fun <T> Iterable<T>.counted(): Map<T, Int> {
    return this.groupingBy { it }.eachCount()
}

fun <T> Iterable<T>.counters(): List<Int> {
    return this.groupingBy { it }.eachCount().map { it.value }
}

fun List<Long>.perms(): Sequence<List<Long>> {
    val list = this

    return sequence {
        if (list.isEmpty()) {
            yield(listOf())
        } else {
            for (elem in list) {
                for (tailPerm in list.filter { it != elem }.perms()) {
                    yield(listOf(elem).plus(tailPerm))
                }
            }
        }
    }
}
