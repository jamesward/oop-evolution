scalaVersion := "3.1.2"

val monocleVersion = "3.1.0"

kotlinLib("stdlib")

libraryDependencies += "net.bytebuddy" % "byte-buddy" % "1.10.8"
libraryDependencies += "net.bytebuddy" % "byte-buddy-agent" % "1.10.8"
libraryDependencies += "dev.optics" %%  "monocle-core"  % monocleVersion
libraryDependencies += "dev.optics" %%  "monocle-unsafe"  % monocleVersion
libraryDependencies += "dev.optics" %%  "monocle-macro" % monocleVersion
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC6"

javacOptions ++= Seq("-source", "11", "-target", "11")
