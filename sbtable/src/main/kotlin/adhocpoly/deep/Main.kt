package adhocpoly.deep


interface CanRun

fun CanRun.doIt() {
    println("Base")
}

class FastRun : CanRun

fun FastRun.doIt() {
    println("Fast Run")
}

fun Any.ok(canRun: CanRun) {
    canRun.doIt()
}

fun main() {
    "asdf".ok(FastRun())
}
