package adt

fun main() {

    val l: Either<Boolean, String> = Either.Left(true)
    val r: Either<Boolean, String> = Either.Right("asdf")

    when(r) {
        is Either.Left -> if (r.left) println("yay") else println("boo")
        is Either.Right -> println("got a string: ${r.right}")
    }

    println(parse(Block(listOf(Tab, Identifier("a"), Space, Identifier("b")))))

}

sealed class Part

object Space: Part()
object Tab: Part()
data class Identifier(val name: String): Part()
data class Block(val parts: List<Part>): Part()

fun parse(part: Part): String {
    return when(part) {
        is Space -> " "
        is Tab -> "  "
        is Identifier -> part.name
        is Block -> part.parts.joinToString("", transform = ::parse)
    }
}
