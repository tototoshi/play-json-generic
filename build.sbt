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
    ),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (version.value.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra :=
      <url>http://github.com/tototoshi/play-json-generic</url>
        <licenses>
          <license>
            <name>Apache License, Version 2.0</name>
            <url>http://github.com/tototoshi/play-json-generic/blob/master/LICENSE.txt</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <developers>
          <developer>
            <id>tototoshi</id>
            <name>Toshiyuki Takahashi</name>
            <url>http://tototoshi.github.com</url>
          </developer>
        </developers>
  )
