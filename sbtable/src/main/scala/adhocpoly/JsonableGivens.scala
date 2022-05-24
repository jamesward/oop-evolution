package adhocpoly

trait Jsonable[T]:
  def json(t: T): String

extension [T: Jsonable](t: T)
  def toJson: String =
    summon[Jsonable[T]].json(t)

case class Bar(name: String)

// in an object so it is not auto-imported
object JsonableBar:
  given Jsonable[Bar] with
    override def json(t: Bar): String =
      s"""{
         |  "name": "${t.name}"
         |}""".stripMargin

@main
def mainJsonBar =
  import JsonableBar.given Jsonable[Bar]
  println(Bar("bar").toJson)


// now add another level: Jsonable<Foo> needs a Jsonable<Bar>


case class Foo(bar: Bar)

// in an object so it is not auto-imported
object JsonableFoo:
  given (using Jsonable[Bar]): Jsonable[Foo] with
    override def json(t: Foo): String =
      s"""{
         |  "foo": ${t.bar.toJson}
         |}""".stripMargin

@main
def mainJsonFoo =
  import JsonableFoo.given Jsonable[Foo]
  import JsonableBar.given Jsonable[Bar]
  println(Foo(Bar("foobar")).toJson)
