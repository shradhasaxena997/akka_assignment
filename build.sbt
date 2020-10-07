name := "akka_file_read_write"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.9",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.9",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.9" % "Test"
)