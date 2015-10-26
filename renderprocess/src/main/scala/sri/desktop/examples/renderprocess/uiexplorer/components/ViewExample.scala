package sri.desktop.examples.renderprocess.uiexplorer.components

import sri.core.ElementFactory._
import sri.desktop.examples.renderprocess.uiexplorer.{UIExample, UIExplorerBlock, UIExplorerPage}
import sri.universal.components.{Text, View}
import sri.web.styles.WebStyleSheet

import scala.scalajs.js.Dynamic.{literal => json}


object ViewExample extends UIExample {


  val Component = () => {
    UIExplorerPage(View(style = styles.container)(
      UIExplorerBlock("Background Color")(
        View(style = styles.bgColorView)(
          Text(style = styles.text)("Blue background")
        )),
      UIExplorerBlock("Border")(
        View(style = styles.borderView)(
          Text(style = styles.text)("5px blue border")
        )),
      UIExplorerBlock("Border Radius")(
        View(style = styles.borderRadiusView)(
          Text(style = styles.text)("Too much use of `borderRadius` (especially large radii) on\n            anything which is scrolling may result in dropped frames.\n            Use sparingly.")
        )),
      UIExplorerBlock("Circle with Border Radius")(
        View(style = styles.borderRadiusCircle)())
    )
    )

  }


  val element = createStatelessFunctionElementNoProps(Component)

  object styles extends WebStyleSheet {
    val container = styleM(flex := 1,
      backgroundColor := "#F5FCFF")

    val text = styleM(fontSize := 11)

    val bgColorView = styleM(backgroundColor := "#527FE4",
      padding := 5)

    val borderView = styleM(borderColor := "#527FE4",
      padding := 10,
      borderWidth := 5)

    val borderRadiusView = styleM(borderRadius := 5,
      padding := 5,
      borderWidth := 0.5)

    val borderRadiusCircle = styleM(borderRadius := 10,
      borderWidth := 1,
      width := 20,
      height := 20)
  }


  override def title: String = "View"

  override def description: String = "Basic building block of all UI."
}
