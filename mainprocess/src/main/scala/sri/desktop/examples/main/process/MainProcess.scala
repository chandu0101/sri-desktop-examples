package sri.desktop.examples.main.process

import sri.desktop.electron.{ElectronApp, ElectronBrowserWindow}
import sri.desktop.node.process

import scala.scalajs.js.Dynamic.{global => g, literal => json}
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object MainProcess extends JSApp {

  @JSExport
  override def main(): Unit = {
    var mainWindow: ElectronBrowserWindow = null

    ElectronApp.on("window-all-closed", () => {
      if (process.platform == "darwin") ElectronApp.quit()
    })

    ElectronApp.on("ready", () => {
      mainWindow = new ElectronBrowserWindow(json(width = 800, height = 600))
      println(s"global electron $ElectronApp")
      mainWindow.loadUrl(s"file://${g.__dirname}/index.html")
      mainWindow.openDevTools()
      mainWindow.on("closed", () => {
        mainWindow = null
      })
    })
  }
}
