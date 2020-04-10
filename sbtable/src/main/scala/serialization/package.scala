import play.api.libs.json.{JsPath, JsonValidationError}

package object serialization {
  def printErrors(e: collection.Seq[(JsPath, collection.Seq[JsonValidationError])]) =
    for {
      (path, errors) <- e
      error <- errors
    } println(s"$path ${error.message} ${error.args.mkString}")
}
