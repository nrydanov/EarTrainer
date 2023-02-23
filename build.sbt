ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies += "org.openjfx" % "javafx-swing" % "19"
libraryDependencies += "com.explodingart" % "jmusic" % "1.6.4"

lazy val root = (project in file("."))
  .settings(
    name := "UI"
  )
