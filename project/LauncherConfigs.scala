
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._
import sbt.Keys._
import sbt._


object LauncherConfigs {

  val fastOptDesktopMain = Def.taskKey[File]("Generate desktop app main process faatopt bundles")

  val fullOptDesktopMain = Def.taskKey[File]("Generate desktop app main process fullopt bundles")

  val rootPath = new java.io.File(".")

  val mainJSFilePath = rootPath / "main.js"

  val renderJSFilePath = rootPath / "assets/render.js"

  lazy val desktopMainLauncher =
    Seq(
      artifactPath in Compile in fastOptDesktopMain :=
        mainJSFilePath,
      artifactPath in Compile in fullOptDesktopMain :=
        mainJSFilePath,
      fastOptDesktopMain in Compile := {
        val mainOutFile = (artifactPath in Compile in fastOptDesktopMain).value
        val mainLoaderFile = (resourceDirectory in Compile).value / "loader.js"
        IO.copyFile(mainLoaderFile, mainOutFile)
        val fastOptCode = IO.read((fastOptJS in Compile).value.data)
        IO.append(mainOutFile, fastOptCode)
        val launcher = (scalaJSLauncher in Compile).value.data.content
        IO.append(mainOutFile, launcher)
        mainOutFile
      },

      fullOptDesktopMain in Compile := {
        val mainOutFile = (artifactPath in Compile in fullOptDesktopMain).value
        val mainLoaderFile = (resourceDirectory in Compile).value / "loader.js"
        IO.copyFile(mainLoaderFile, mainOutFile)
        val fullOptCode = IO.read((fullOptJS in Compile).value.data)
        IO.append(mainOutFile, fullOptCode)
        val launcher = (scalaJSLauncher in Compile).value.data.content
        IO.append(mainOutFile, launcher)
        mainOutFile
      }
    )

  val fastOptDesktopRender = Def.taskKey[File]("Generate desktop app render process fastopt bundles")

  val fullOptDesktopRender = Def.taskKey[File]("Generate desktop app render process fullopt bundles")

  lazy val desktopRenderLauncher =
    Seq(
      artifactPath in Compile in fastOptDesktopRender :=
        renderJSFilePath,
      artifactPath in Compile in fullOptDesktopRender :=
        renderJSFilePath,
      fastOptDesktopRender in Compile := {
        val renderOutFile = (artifactPath in Compile in fastOptDesktopRender).value
        val fastOptFile = (fastOptJS in Compile).value.data
        IO.copyFile(fastOptFile, renderOutFile)
        val launcher = (scalaJSLauncher in Compile).value.data.content
        IO.append(renderOutFile, launcher)
        renderOutFile
      },

      fullOptDesktopRender in Compile := {
        val renderOutFile = (artifactPath in Compile in fullOptDesktopRender).value
        val fullOptFile = (fullOptJS in Compile).value.data
        IO.copyFile(fullOptFile, renderOutFile)
        val launcher = (scalaJSLauncher in Compile).value.data.content
        IO.append(renderOutFile, launcher)
        renderOutFile
      }
    )

}

  