package sri.desktop.examples.renderprocess.components

import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet
import sri.web.all._

import scala.scalajs.js.Dynamic.{literal => json}
import scala.scalajs.js.{UndefOr => U}

object HelloDesktop2 {


  val component = () => {
    View(style = styles.container)(
      Text(style = styles.text)(s"Welcome to Sri Desktop"),
      Image(style = styles.image, source = ImageSource(uri = "http://www.scala-js.org/images/scala-js-logo-256.png"))(),
      Text(style = styles.text)("Scala.js - Future of app development!")
    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexOne,
      backgroundColor := "rgb(175, 9, 119)",
      justifyContent.center,
      alignItems.center)

    val image = style(width := 256, height := 256, margin := 20)

    val text = style(fontWeight._500,
      fontSize := 18,
      color := "white")

  }


  def apply() = createStatelessFunctionElementNoProps(component)
}
