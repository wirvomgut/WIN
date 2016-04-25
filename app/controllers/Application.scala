package controllers

import javax.inject._
import play.api.mvc._

/**
  * Created by julianliebl on 25.04.16.
  */
class Application  @Inject() extends Controller{
  def hello()= Action {
    Ok(views.html.index("Your new application is ready."))
  }

  private val productMap = Map(1 -> "Keyboard", 2 -> "Mouse", 3 -> "Monitor")
  def listProducts() = Action {
    Ok(views.html.products(productMap.values.toSeq))
  }
}
