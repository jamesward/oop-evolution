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

    val aORb: Either<A, B> = Either.Left(a)

    // 2 + 2

    val aORbORc: Trither<A, B, C> = Trither.Middle(b)

    // 2 + 2 + 2


    // Algebraic Laws

    // Associativity
    // A AND (B AND C) == (A AND B) AND C
    // A OR (B OR C) == (A OR B) OR C

    // Commutativity
    // A AND B == B AND A
    // A OR B == B OR A

    // Identity
    // A AND Any == A
    // A OR Nothing == A

    // Distributivity
    // A AND (B OR C) == (A AND B) OR (A AND C)
    // ??? A OR (B AND C) == (A OR B) AND (A OR C)

    // Annihilator
    // A AND Nothing == Nothing
    // ??? A OR Any == Any

    // Idempotence
    // ??? A AND A == A
    // ??? A OR A == A

    // Absorption
    // ??? A AND (A OR B) == A
    // ??? A OR (A AND B) == A
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
