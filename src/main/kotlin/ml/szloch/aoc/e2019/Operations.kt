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
        while (!vm.input.hasNext()) {
            Thread.sleep(0)
        }
        vm.mem[ad1(vm)] = vm.input.next(); vm.ip += 2
    }
}

class WriteOp : Operation {
    override fun execute(vm: VM) {
        synchronized(vm.output) {
            vm.output.print(op1(vm)); vm.ip += 2
        }
    }
}

class JumpIfTrueOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) != 0L) op2(vm) else vm.ip + 3
    }
}

class JumpIfFalseOp : Operation {
    override fun execute(vm: VM) {
        vm.ip = if (op1(vm) == 0L) op2(vm) else vm.ip + 3
    }
}

class LessThanOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = if (op1(vm) < op2(vm)) 1L else 0; vm.ip += 4
    }
}

class EqualsOp : Operation {
    override fun execute(vm: VM) {
        vm.mem[ad3(vm)] = if (op1(vm) == op2(vm)) 1L else 0; vm.ip += 4
    }
}

class AdjustRelativeBaseOp : Operation {
    override fun execute(vm: VM) {
        vm.rb += op1(vm); vm.ip += 2
    }
}


class HaltOp : Operation {
    override fun execute(vm: VM) {
        vm.halted = true
    }
}
