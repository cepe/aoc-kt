package ml.szloch.aoc.e2019

class AddOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = op1(vm) + op2(vm); vm.ip += 4
    }
}

class MulOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = op1(vm) * op2(vm); vm.ip += 4
    }
}

class ReadOp : Operation {
    override fun execute(vm: VM) {
        while (vm.input.size - 1 < vm.iop) {
            Thread.sleep(0)
        }
        vm.mem[ad1(vm)] = vm.input[vm.iop]; vm.iop += 1; vm.ip += 2
    }
}

class WriteOp : Operation {
    override fun execute(vm: VM) {
        synchronized(vm.output) {
            vm.output.add(op1(vm)); vm.ip += 2
        }
    }
}

class JumpIfTrueOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) != 0) op2(vm) else vm.ip + 3
    }
}

class JumpIfFalseOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) == 0) op2(vm) else vm.ip + 3
    }
}

class LessThanOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = if (op1(vm) < op2(vm)) 1 else 0; vm.ip += 4
    }
}

class EqualsOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = if (op1(vm) == op2(vm)) 1 else 0; vm.ip += 4
    }
}

class HaltOp : Operation {
    override fun execute(vm: VM) {
        vm.halted = true
    }
}
