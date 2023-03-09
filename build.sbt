ThisBuild / version := "0.1.0-SNAPSHOT"

val swingVersion = "19"
val sl4jVersion = "2.0.6"
val loggingVersion = "3.9.4"


ThisBuild / scalaVersion := "3.0.2"

libraryDependencies += "org.openjfx" % "javafx-swing" % swingVersion
libraryDependencies += "org.slf4j" % "slf4j-api" % sl4jVersion
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % loggingVersion

lazy val root = (project in file("."))
  .settings(
    name := "EarTrainer"
  )
