package subtyping.deep

interface Runner {
    fun run() {
        println("Default runner")
    }
}

class FastRunner : Runner {
    override fun run() {
        println("Fast Runner")
    }
}

class Holder {
    private val runner: Runner = FastRunner()

    fun runIt() {
        runner.run()
    }
}

fun main() {
    val holder = Holder()
    holder.runIt()
}
