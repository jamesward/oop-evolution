scalaVersion := "2.13.1"

val monocleVersion = "2.0.0"

kotlinLib("stdlib")

libraryDependencies += "net.bytebuddy" % "byte-buddy" % "1.10.8"
libraryDependencies += "net.bytebuddy" % "byte-buddy-agent" % "1.10.8"
libraryDependencies += "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion
libraryDependencies += "com.github.julien-truffaut" %%  "monocle-unsafe"  % monocleVersion
libraryDependencies += "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"

javacOptions ++= Seq("-source", "11", "-target", "11")
