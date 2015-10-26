package sri.desktop.examples.renderprocess.components

import org.scalajs.dom
import sri.desktop.all._
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

object HelloDesktop {

  val component = () => {
    dom.window.console.log(process.env)
    View(style = styles.container)(
      Image(source = ImageSource(uri = "http://s1.postimg.org/w4v0g3ei7/sample.jpg"), style = styles.img)(
        Text(style = styles.text)("Welcome to Sri Desktop")
      )
    )
  }

  object styles extends UniversalStyleSheet {
    val container = style(flexOne)
    val img = style(flexOne,
      alignItems.center)
    val text = style(fontWeight._500,
      fontSize := 18,
      paddingTop := 20)
  }

  def apply() = createStatelessFunctionElementNoProps(component)
}
