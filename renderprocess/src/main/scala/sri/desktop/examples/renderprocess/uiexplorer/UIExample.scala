package sri.desktop.examples.renderprocess.uiexplorer

import sri.core.ReactElement

trait UIExample {

  def title : String
  def description : String
  def element : ReactElement
}
