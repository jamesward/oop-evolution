package serialization

import builders.CaseClasses._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads.{minLength, verifying}
import play.api.libs.json.{JsError, Json, Reads, __}

object JsonCombinators {

  def main(arg: Array[String]): Unit = {

    val startsWithUpper = verifying[String](_.headOption.forall(_.isUpper))
        .orElse(Reads(_ => JsError("Must start with an upper case letter")))

    val nameValidator = minLength[String](2) andKeep startsWithUpper

    implicit val jsonReads: Reads[Vehicle] = (
      (__ \ "name").read[String](nameValidator) and
      (__ \ "num_wheels").read[Int] and
      (__ \ "num_cylinders").readNullable[Int]
    )(Vehicle.apply _)

    val volvoJson = """
      |{
      |  "name": "v",
      |  "num_wheels": 3
      |}
      |""".stripMargin

    val result = Json.parse(volvoJson).validate[Vehicle]

    result.fold(invalid = printErrors, valid = println)
  }

}
