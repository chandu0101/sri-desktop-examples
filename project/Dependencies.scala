
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._


object Dependencies {

  val scalaAsyncVersion = "0.9.2"

  val upickleVersion = "0.3.1"

  val scalatestVersion = "3.0.0-M6"

  val utestVersion = "0.3.0"

   val sriVersion = "0.2.0"

  val utestJS = libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % Test

  val scalatestJS = libraryDependencies += "org.scalatest" %%% "scalatest" % scalatestVersion % Test

  val scalaAsync = libraryDependencies += "org.scala-lang.modules" %% "scala-async" % scalaAsyncVersion

  val scalaJSUpickle = libraryDependencies += "com.lihaoyi" %%% "upickle" % upickleVersion

   val sriDesktop = libraryDependencies += "com.github.chandu0101.sri" %%% "desktop" % sriVersion

  val mainProcessDeps = Seq(sriDesktop)

  val renderProcessDeps = Seq(sriDesktop)
  
}