package ml.szloch.aoc.e2019

interface Operation {
    fun execute(vm: VM)
    fun opValue(mem: List<Int>, address: Int, mode: Boolean): Int = if (mode) address else mem[address]
    fun op1(vm: VM, mode: Boolean = false) =
        opValue(vm.mem, vm.mem[vm.ip + 1], mode || (vm.mem[vm.ip] / 100) % 10 == 1)

    fun op2(vm: VM): Int = opValue(vm.mem, vm.mem[vm.ip + 2], vm.mem[vm.ip] / 100 >= 10)
    fun op3(vm: VM): Int = opValue(vm.mem, vm.mem[vm.ip + 3], true)
}
