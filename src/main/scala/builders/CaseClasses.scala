package builders

object CaseClasses extends App {

  case class Vehicle(name: String, numWheels: Int = 4, maybeNumCylinders: Option[Int] = None) {
    val vroom: String = {
      val baseVroom = s"Here goes $name with its $numWheels wheels"
      maybeNumCylinders.fold(baseVroom) { numCylinders =>
        baseVroom + s" and its $numCylinders cylinders"
      }
    }
  }

  println(Vehicle("Cadillac").vroom)
  println(Vehicle("Cadillac", 3).vroom)
  println(Vehicle(name = "Cadillac", maybeNumCylinders = Some(4)).vroom)
  println(Vehicle("Cadillac").copy(numWheels = 3, maybeNumCylinders = Some(4)).vroom)

}
