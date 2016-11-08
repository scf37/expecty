

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:implicitConversions",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xlint"
)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.0",
  crossScalaVersions := Seq("2.11.8", "2.12.0"),
  scalacOptions := compilerOptions
)


val expecty = project.settings(
  commonSettings,
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided
)

val expectyTest = (project in file("expecty-test"))
  .dependsOn(expecty)
  .settings(commonSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % Test)


val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(expecty, expectyTest)
