package builders

object CaseClasses {

  case class Vehicle(name: String, numWheels: Int = 4, maybeNumCylinders: Option[Int] = None) {
    val vroom: String = {
      val baseVroom = s"Here goes $name with its $numWheels wheels"
      maybeNumCylinders.fold(baseVroom) { numCylinders =>
        baseVroom + s" and its $numCylinders cylinders"
      }
    }
  }

  case class Owner(name: String, age: Int, vehicles: Vector[Vehicle])


  def main(arg: Array[String]): Unit = {
    val cadillac = Vehicle("Cadillac")
    // cadillac.name = "asdf"

    println(Vehicle("Cadillac").vroom)
    println(Vehicle("Cadillac", 3).vroom)
    println(Vehicle(name = "Cadillac", maybeNumCylinders = Some(4)).vroom)
    println(Vehicle("Cadillac").copy(numWheels = 3, maybeNumCylinders = Some(4)).vroom)

    lenses()
  }

  def lenses(): Unit = {
    import monocle.macros.GenLens
    import monocle.function.all._
    import monocle.unsafe.UnsafeSelect

    val vehicles = GenLens[Owner](_.vehicles)
    val teslaFilter = UnsafeSelect.unsafeSelect[Vehicle](_.name == "Tesla")
    val numCylinders = GenLens[Vehicle](_.maybeNumCylinders)
    val optCylinderOfTesla =
      vehicles ^|->> each ^<-? teslaFilter ^|-> numCylinders
    val ownerInstallsV8OnTesla = optCylinderOfTesla set Some(8)
    val original = Owner(name = "Rich Guy",
      age = 43,
      vehicles = Vector(
        Vehicle("Cadillac"),
        Vehicle("Tesla")
      ))
    val newOwner = ownerInstallsV8OnTesla(original)
    println(s"Original owner: $original")
    println(s"New owner: $newOwner")
  }

}
