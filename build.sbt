val scalaVersion_2_11 = "2.11.8"
val scalaVersion_2_12 = "2.12.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := """play-json-generic""",
    organization := "com.github.tototoshi",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scalaVersion_2_11,
    crossScalaVersions := Seq(scalaVersion_2_11, scalaVersion_2_12),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.6.9",
      "com.chuusai" %% "shapeless" % "2.3.3",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
