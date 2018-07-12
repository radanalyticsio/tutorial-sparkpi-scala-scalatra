import sbt._
import Dependencies._

organization := "io.radanalytics"
name := "tutorial-sparkpi-scala-scalatra"
version := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.11"

// reccommended from: https://github.com/holdenk/spark-testing-base/blob/master/README.md
// fork in Test := true
// javaOptions ++= Seq( "-Xms512M", "-Xmx2048M", "-XX:MaxMetaspace=1024M", "-XX:+CMSClassUnloadingEnabled" )

// publishMavenStyle := true

resolvers += Resolver.sbtPluginRepo( "releases" )
resolvers += Classpaths.typesafeReleases
resolvers in ThisBuild ++= Seq( "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
                                "Spray IO Repository" at "http://repo.spray.io/",
                                "Maven Central" at "https://repo1.maven.org/maven2/",
                                "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/" )


// see project/Dependencies.scala for dependency management
libraryDependencies ++= slf4j ++ logback ++ scalatra ++ scalaTest

mainClass in(Compile, run) := Some( "io.radanalytics.Main" )

// docs for why this is necessary are here: https://github.com/scalatra/sbt-scalatra
enablePlugins( JavaAppPackaging )
enablePlugins( JettyPlugin )
enablePlugins( ScalatraPlugin )

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    //    case PathList( "META-INF", xs@_* ) => MergeStrategy.first
    case x => MergeStrategy.last
}