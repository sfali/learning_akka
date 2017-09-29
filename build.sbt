name := "learning_akka"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= {

  val akkaVersion = "2.4.16"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test"
  )
}
