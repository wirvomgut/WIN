import sbt.Keys._

enablePlugins(ScalaJSPlugin)

name := "WIN"

version := "1.0"

scalaVersion := "2.11.8"




val app = crossProject
  .enablePlugins(SbtWeb)
  .settings(
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % "0.5.4",
      "com.lihaoyi" %%% "upickle" % "0.3.9"
    )
  ).jsSettings(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  ),
  jsDependencies ++= Seq(
    "org.webjars.bower" % "semantic-ui" % "2.1.8" / "semantic.js" minified "semantic.min.js"
  )
).jvmSettings(
  scalaJSUseRhino in Global := false,
  skip in packageJSDependencies := false,
  LessKeys.compress in Assets  := true,
  libraryDependencies ++= Seq(
    "io.spray" %% "spray-can" % "1.3.3",
    "io.spray" %% "spray-routing" % "1.3.3",
    "com.typesafe.akka" %% "akka-actor" % "2.4.4",
    "org.webjars.bower" % "semantic-ui" % "2.1.8"
  )
)

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
  (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data,
  (resources in Compile) ++= (WebKeys.webJarsDirectory in Assets).map { path =>
    path.listFiles().map { src =>
      val tgt = new File("app/jvm/target/scala-2.11/classes") / src.getName
      if(tgt.isFile){
        IO.copyFile(src, tgt)
      }else{
        IO.copyDirectory(src, tgt)
      }
      tgt
    }.toSeq
  }.value
)