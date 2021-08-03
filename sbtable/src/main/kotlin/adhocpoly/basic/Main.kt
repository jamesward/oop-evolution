package adhocpoly.basic


interface CanRun

fun CanRun.doIt() {
    println("Base")
}

class FastRun : CanRun

fun FastRun.doIt() {
    println("Fast Run")
}

fun main() {
    FastRun().doIt()
}
