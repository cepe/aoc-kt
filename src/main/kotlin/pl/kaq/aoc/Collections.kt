package pl.kaq.aoc


fun <T> Iterable<T>.counted(): Map<T, Int> {
    return this.groupingBy { it }.eachCount()
}

fun <T> Iterable<T>.counters(): List<Int> {
    return this.groupingBy { it }.eachCount().map { it.value }
}

fun <T> List<T>.perms(): Sequence<List<T>> {
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
