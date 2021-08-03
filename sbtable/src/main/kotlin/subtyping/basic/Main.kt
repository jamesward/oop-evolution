package subtyping.basic

interface Runner {
    fun run() {
        println("Default runner")
    }
}

fun runIt(runner: Runner) {
    runner.run()
}

fun main() {

    class FastRunner : Runner {
        override fun run() {
            println("Fast Runner")
        }
    }

    runIt(FastRunner())

}
