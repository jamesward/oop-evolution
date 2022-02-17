package adhocpoly

fun main() {

    fun Iterable<Char>.sum(): String {
        val asciibase = 'a'.dec().code

        return fold(asciibase) { acc, l ->
            (l.code - asciibase) + acc
        }.toChar().toString()
    }

    println(listOf('a', 'b', 'c').sum())


    fun Iterable<String>.sum(): String {
        val asciibase = 'a'.dec().code

        return fold(asciibase) { acc, l ->
            (l.first().code - asciibase) + acc
        }.toChar().toString()
    }

    println(listOf("a", "b").sum())

    abstract class ToChar<T> {
        abstract operator fun invoke(t: T): Char
    }

    val stringToChar = object: ToChar<String>() {
        override operator fun invoke(t: String): Char = t.first()
    }

    val charToChar = object: ToChar<Char>() {
        override operator fun invoke(t: Char): Char = t
    }

    fun <T> Iterable<T>.sumT(toChar: ToChar<T>): String {
        val asciibase = 'a'.dec().code

        return fold(asciibase) { acc, l ->
            (toChar(l).code - asciibase) + acc
        }.toChar().toString()
    }

    println(listOf('a', 'b', 'c').sumT(charToChar))
    println(listOf("a", "b").sumT(stringToChar))

    fun <T> iterableToChar(toChar: ToChar<T>): ToChar<Iterable<T>> {
        return object: ToChar<Iterable<T>>() {
            override fun invoke(t: Iterable<T>): Char = toChar(t.first())
        }
    }

    println(listOf(listOf('a'), listOf('b', 'c')).sumT(iterableToChar(charToChar)))
    println(listOf(listOf("a"), listOf("b", "c")).sumT(iterableToChar(stringToChar)))

}
