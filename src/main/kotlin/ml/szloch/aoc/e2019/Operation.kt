package ml.szloch.aoc.e2019

interface Operation {
    fun execute(vm: VM)

    fun op1(vm: VM): Long {
        return when ((vm.memAt(vm.ip) / 100) % 10) {
            0L -> vm.memAt(vm.memAt(vm.ip + 1))
            1L -> vm.memAt(vm.ip + 1)
            2L -> vm.memAt(vm.memAt(vm.ip + 1) + vm.rb)
            else -> throw IllegalStateException()
        }
    }

    fun op2(vm: VM): Long {
        return when ((vm.memAt(vm.ip) / 1000) % 10) {
            0L -> vm.memAt(vm.memAt(vm.ip + 2))
            1L -> vm.memAt(vm.ip + 2)
            2L -> vm.memAt(vm.memAt(vm.ip + 2) + vm.rb)
            else -> throw IllegalStateException()
        }
    }

    fun ad1(vm: VM): Long = when ((vm.memAt(vm.ip) / 100) % 10) {
        0L -> vm.memAt(vm.ip + 1)
        1L -> vm.ip + 1
        2L -> vm.memAt(vm.ip + 1) + vm.rb
        else -> throw IllegalStateException()
    }


    fun ad3(vm: VM): Long = when ((vm.memAt(vm.ip) / 10000) % 10) {
        0L -> vm.memAt(vm.ip + 3)
        1L -> vm.ip + 3
        2L -> vm.memAt(vm.ip + 3) + vm.rb
        else -> throw IllegalStateException()
    }
}
