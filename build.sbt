import NativePackagerKeys._

enablePlugins(ScalaJSPlugin)

name := "WIN"

version := "1.0"

scalaVersion := "2.11.8"

scalaJSUseRhino in Global := false

skip in packageJSDependencies := false

val app = crossProject.settings(
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "scalatags" % "0.5.4",
    "com.lihaoyi" %%% "upickle" % "0.3.9"
  )
).jsSettings(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  )
).jvmSettings(
  libraryDependencies ++= Seq(
    "io.spray" %% "spray-can" % "1.3.3",
    "io.spray" %% "spray-routing" % "1.3.3",
    "com.typesafe.akka" %% "akka-actor" % "2.4.4"
  )
)

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
  (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data
)