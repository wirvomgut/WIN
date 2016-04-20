enablePlugins(ScalaJSPlugin)

name := "WIN"

version := "1.0"

scalaVersion := "2.11.8"

scalaJSUseRhino in Global := false

skip in packageJSDependencies := false

persistLauncher in Compile := true

persistLauncher in Test := false

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.0"

jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"