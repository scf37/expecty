

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
  releaseCrossBuild := true,

  scalacOptions := compilerOptions,
  releaseTagComment := s"[ci skip]Releasing ${(version in ThisBuild).value}",
  releaseCommitMessage := s"[ci skip]Setting version to ${(version in ThisBuild).value}",
  resourceGenerators in Compile <+= buildProperties,

  bintrayOmitLicense := true,
  bintrayVcsUrl := Some("git@github.com:scf37/expecty.git")

)


val expecty = project.settings(
  commonSettings,
  name := "expecty",
  organization := "me.scf37.expecty",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided
)

val expectyTest = (project in file("expecty-test"))
  .dependsOn(expecty)
  .settings(commonSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % Test,
    publish := {})


val root = (project in file("."))
  .settings(commonSettings)
  .settings(publish := {})
  .aggregate(expecty, expectyTest)
