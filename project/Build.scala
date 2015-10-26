import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt.{Build, _}

object SriDesktopExamples extends Build {

  import Dependencies._
  import LauncherConfigs._

  val Scala211 = "2.11.7"

  lazy val commonSettings =
    Seq(
      organization := "com.github.chandu0101.sri",
      version := "0.2.0-SNAPSHOT",
      homepage := Some(url("https://github.com/chandu0101/sri-desktop-examples")),
      licenses +=("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0")),
      scalaVersion := Scala211,
      scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature",
        "-language:postfixOps", "-language:implicitConversions",
        "-language:higherKinds", "-language:existentials"))


  def DefProject(name: String, id: String = "") = {
    Project(if (id.isEmpty) name else id, file(name))
      .settings(commonSettings: _*)
      .enablePlugins(ScalaJSPlugin)
  }

  def addCommandAliases(m: (String, String)*) = {
    val s = m.map(p => addCommandAlias(p._1, p._2)).reduce(_ ++ _)
    (_: Project).settings(s: _*)
  }


  /** ===================  Test frameworks settings   */

  val scalatestJSSettings = Seq(scalatestJS,
    scalaJSStage in Global := FastOptStage,
    jsDependencies += RuntimeDOM,
    jsDependencies += ProvidedJS / "test-bundle.js" % Test
    //    jsEnv in Test := new PhantomJS2Env(scalaJSPhantomJSClassLoader.value, addArgs = Seq("--web-security=no"))
    //    jsEnv in Test := new NodeJSEnv()
  )


  val utestSettings = Seq(utestJS,
    scalaJSStage in Global := FastOptStage,
    jsDependencies += RuntimeDOM,
    testFrameworks += new TestFramework("utest.runner.Framework")
    // jsEnv in Test := new PhantomJS2Env(scalaJSPhantomJSClassLoader.value, addArgs = Seq("--web-security=no"))
  )


  // ================================ Module definitions  ================================ //
  lazy val SriDesktopExamples = DefProject(".", "root")
    .aggregate(mainProcess,renderprocess)
    .configure(addCommandAliases(
    "tt" -> "; test:compile ; test/test",
    "T" -> "; clean ;t",
    "TT" -> ";+clean ;tt"))

  lazy val mainProcess = DefProject("mainprocess")
    .settings(mainProcessDeps)
    .settings(desktopMainLauncher)
    

  lazy val renderprocess = DefProject("renderprocess")
    .settings(renderProcessDeps)
    .settings(desktopRenderLauncher)

}