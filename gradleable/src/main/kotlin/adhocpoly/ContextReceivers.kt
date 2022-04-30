package adhocpoly

abstract class Jsonable<T> {
    abstract fun json(t: T): String
}

context(Jsonable<T>)
fun <T> T.toJson(): String =
    json(this)


fun main() {

    data class Bar(val name: String)

    val barJson = object: Jsonable<Bar>() {
        override fun json(t: Bar): String = """
            {
                "name": ${t.name}
            }
        """.trimIndent()
    }

    with(barJson) {
        println(Bar("asdf").toJson())
    }

    // now add another level: Jsonable<Foo> needs a Jsonable<Bar>

    data class Foo(val bar: Bar)

    val fooJson = object: Jsonable<Foo>() {
        context(Jsonable<Bar>)
        override fun json(t: Foo): String = """
            {
                "bar": ${t.bar.toJson()}
            }
        """.trimIndent()
    }

    with(barJson) {
        with(fooJson) {
            println(Foo(Bar("asdf")).toJson())
        }
    }

    /*
    Compile error:

    Caused by: java.lang.IllegalArgumentException: No argument for parameter VALUE_PARAMETER name:t index:1 type:adhocpoly.ContextReceiversKt.main.Foo:
CALL 'public open fun json (<this>: adhocpoly.Jsonable<adhocpoly.ContextReceiversKt.main.Bar>, t: adhocpoly.ContextReceiversKt.main.Foo): kotlin.String declared in adhocpoly.ContextReceiversKt.main.<no name provided>' type=kotlin.String origin=BRIDGE_DELEGATION
  $this: GET_VAR '<this>: adhocpoly.ContextReceiversKt.main.<no name provided> declared in adhocpoly.ContextReceiversKt.main.<no name provided>.json' type=adhocpoly.ContextReceiversKt.main.<no name provided> origin=null
  <this>: TYPE_OP type=adhocpoly.Jsonable<*> origin=IMPLICIT_CAST typeOperand=adhocpoly.Jsonable<*>
    GET_VAR 't: kotlin.Any? declared in adhocpoly.ContextReceiversKt.main.<no name provided>.json' type=kotlin.Any? origin=null
     */

}
