name := "WIN"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.pac4j" % "play-pac4j" % "2.2.0-SNAPSHOT",
  "org.pac4j" % "pac4j-http" % "1.9.0-SNAPSHOT",
  "org.pac4j" % "pac4j-mongo" % "1.9.0-SNAPSHOT",
   "org.webjars.bower" % "semantic-ui" % "2.1.8",
  "com.typesafe.play" % "play-cache_2.11" % "2.4.6",
  "org.scalatestplus.play"  %% "scalatestplus-play" % "1.5.1"  % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/"

routesGenerator := InjectedRoutesGenerator

fork in run := true