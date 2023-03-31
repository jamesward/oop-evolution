package adhocpoly

abstract class Jsonable<T> {
    abstract fun json(t: T): String
}

context(Jsonable<T>)
fun <T> T.toJson(): String =
    json(this)

val intJson = object: Jsonable<Int>() {
    override fun json(t: Int): String = t.toString()
}

data class Bar(val name: String)

val barJson = object: Jsonable<Bar>() {
    override fun json(t: Bar): String = """
            {
                "name": ${t.name}
            }
        """.trimIndent()
}

data class Foo(val bar: Bar)

context(Jsonable<Bar>)
fun fooJson() = object: Jsonable<Foo>() {
    override fun json(t: Foo): String = """
            {
                "bar": ${t.bar.toJson()}
            }
        """.trimIndent()
}

fun main() {

    with(barJson) {
        println(Bar("asdf").toJson())
    }

    // now add another level: Jsonable<Foo> needs a Jsonable<Bar>

    with(barJson) {
        with(fooJson()) {
            println(Foo(Bar("asdf")).toJson())
        }
    }

}
