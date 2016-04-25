package controllers

import javax.inject._

import play.api.mvc._

/**
  * Created by julianliebl on 25.04.16.
  */
class LoginController @Inject extends Controller{
  def index() = Action{
    Ok(views.html.login())
  }
}
