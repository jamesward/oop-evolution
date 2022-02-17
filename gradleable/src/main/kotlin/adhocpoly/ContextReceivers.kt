package adhocpoly

abstract class Summable<T> {
    abstract fun tplus(t1: T, t2: T): T
}

context(Summable<T>)
fun <T> Iterable<T>.sum(): T =
    reduce(::tplus)

fun main() {

    val summableChar = object: Summable<Char>() {
        override fun tplus(t1: Char, t2: Char): Char =
            (t1.code + t2.code - 'a'.dec().code).toChar()
    }

    val summableString = object: Summable<String>() {
        // you can't do:
        // context(Summable<Char>)
        override fun tplus(t1: String, t2: String): String = run {
            val t2Chars = t2.toCharArray()
            t1.toCharArray().mapIndexed { i, c ->
                summableChar.tplus(c, t2Chars[i])
            }.joinToString("")
        }
    }

    with(summableChar) {
        with(summableString) {
            println(listOf('a', 'b').sum())
            println(listOf("ah", "he").sum())

            // doesn't work because we don't have a Summable<Boolean>
            //println(listOf(true, false).sum())
        }
    }

}
