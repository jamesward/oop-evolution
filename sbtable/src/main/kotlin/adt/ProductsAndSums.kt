package adt

typealias A = Boolean
typealias B = Boolean
typealias C = Boolean

fun main() {

    val a: A = true
    val b: B = false
    val c: C = true

    val aANDb: Pair<A, B> = Pair(a, b)

    // 2 * 2

    val aANDbANDc: Triple<A, B, C> = Triple(a, b, c)

    // 2 * 2 * 2

    // AND = intersection

    val aORb: Either<A, B> = Either.Left(a)

    // 2 + 2

    val aORbORc: Trither<A, B, C> = Trither.Middle(b)

    // 2 + 2 + 2

    // OR = union
}

sealed class Either<L, R> {
    class Left<L, R>(val left: L) : Either<L, R>()
    class Right<L, R>(val right: R) : Either<L, R>()
}

sealed class Trither<L, M, R> {
    class Left<L, M, R>(val left: L) : Trither<L, M, R>()
    class Middle<L, M, R>(val middle: M) : Trither<L, M, R>()
    class Right<L, M, R>(val right: R) : Trither<L, M, R>()
}
