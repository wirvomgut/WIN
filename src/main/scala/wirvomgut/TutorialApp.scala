package wirvomgut

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery

import scala.scalajs.js.annotation.JSExport

object TutorialApp extends JSApp {
  @JSExport
  def addClickedMessage(): Unit = {
    appendPar("You clicked the button!")
  }

  def appendPar(text: String): Unit = {
    jQuery("body").append("<p>" + text + "</p>")
  }

  def setupUI(): Unit = {
    jQuery("body").append("<p>Hello World</p>")
    jQuery("body").append("""<button id="click-me-button" type="button"">Click me!</button>""")
    jQuery("#click-me-button").click(addClickedMessage _)
  }

  def main(): Unit = {
    jQuery(setupUI _)
  }
}
