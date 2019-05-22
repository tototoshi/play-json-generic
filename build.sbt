val scalaVersion_2_11 = "2.11.12"
val scalaVersion_2_12 = "2.12.8"

lazy val root = project
  .in(file("."))
  .settings(
    name := """play-json-generic""",
    organization := "com.github.tototoshi",
    version := "0.2.0-SNAPSHOT",
    scalaVersion := scalaVersion_2_11,
    crossScalaVersions := Seq(scalaVersion_2_11, scalaVersion_2_12),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.6.9",
      "com.chuusai" %% "shapeless" % "2.3.3",
      "org.scalatest" %% "scalatest" % "3.0.7" % "test"
    ),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (version.value.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra :=
      <url>https://github.com/tototoshi/play-json-generic</url>
        <licenses>
          <license>
            <name>Apache License, Version 2.0</name>
            <url>https://github.com/tototoshi/play-json-generic/blob/master/LICENSE.txt</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:tototoshi/play-json-generic.git</url>
          <connection>scm:git:git@github.com:tototoshi/play-json-generic.git</connection>
        </scm>
        <developers>
          <developer>
            <id>tototoshi</id>
            <name>Toshiyuki Takahashi</name>
            <url>https://tototoshi.github.com</url>
          </developer>
        </developers>
  )
