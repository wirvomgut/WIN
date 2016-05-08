package controllers

import javax.inject._

import org.pac4j.core.profile.{CommonProfile, ProfileManager}
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.play.PlayWebContext
import org.pac4j.play.scala.Security
import play.api.mvc._

import scala.collection.JavaConversions._

/**
  * Created by julianliebl on 25.04.16.
  */
class LoginController @Inject extends Controller with Security[CommonProfile] {


  private def getProfiles(implicit request: RequestHeader): List[CommonProfile] = {
    val webContext = new PlayWebContext(request, config.getSessionStore)
    val profileManager = new ProfileManager[CommonProfile](webContext)
    val profiles = profileManager.getAll(true)
    asScalaBuffer(profiles).toList
  }

  def protectedIndex = Secure { profiles =>
    Action { request =>
      Ok(views.html.protectedIndex(profiles))
    }
  }

  def index() = Action{
    val formClient = config.getClients.findClient("FormClient").asInstanceOf[FormClient]
    Ok(views.html.login(formClient.getCallbackUrl))
  }
}
