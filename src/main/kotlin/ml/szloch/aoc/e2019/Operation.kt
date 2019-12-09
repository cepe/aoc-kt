package ml.szloch.aoc.e2019

interface Operation {
    fun execute(vm: VM)

    fun op1(vm: VM): Int {
        return when ((vm.mem[vm.ip] / 100) % 10) {
            0 -> vm.mem[vm.mem[vm.ip + 1]]
            1 -> vm.mem[vm.ip + 1]
            else -> throw IllegalStateException()
        }
    }

    fun op2(vm: VM): Int {
        return when ((vm.mem[vm.ip] / 1000) % 10) {
            0 -> vm.mem[vm.mem[vm.ip + 2]]
            1 -> vm.mem[vm.ip + 2]
            else -> throw IllegalStateException()
        }
    }

    fun ad1(vm: VM): Int = when ((vm.mem[vm.ip] / 100) % 10) {
        0 -> vm.mem[vm.ip + 1]
        1 -> vm.ip + 1
        else -> throw IllegalStateException()
    }


    fun ad3(vm: VM): Int = when ((vm.mem[vm.ip] / 10000) % 10) {
        0 -> vm.mem[vm.ip + 3]
        1 -> vm.ip + 3
        else -> throw IllegalStateException()
    }
}
