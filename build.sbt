ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies += "org.openjfx" % "javafx-swing" % "19"
libraryDependencies += "com.explodingart" % "jmusic" % "1.6.4"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.10"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"


lazy val root = (project in file("."))
  .settings(
    name := "UI"
  )
