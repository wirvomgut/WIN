name := "WIN"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

enablePlugins(SbtWeb)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.webjars.bower" % "semantic-ui" % "2.1.8",
  "org.webjars.bower" % "jquery" % "2.2.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"