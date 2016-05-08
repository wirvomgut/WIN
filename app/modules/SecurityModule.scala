package modules

import com.google.inject.AbstractModule
import controllers.{AuthenticationValidationController, AuthenticationAuthorizationController, AuthenticationRoutingController}
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer
import org.pac4j.core.client.Clients
import org.pac4j.core.config.Config
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.play.{ApplicationLogoutController, CallbackController}
import play.api.{Configuration, Environment}

/**
 * Guice DI module to be included in application.conf
 */
class SecurityModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {
    val baseUrl = configuration.getString("baseUrl").get

    // HTTP
    val formClient = new FormClient(baseUrl + "/login", new AuthenticationValidationController())

    val clients = new Clients(baseUrl + "/callback", formClient)

    val config = new Config(clients)
    config.addAuthorizer("admin", new RequireAnyRoleAuthorizer[Nothing]("ROLE_ADMIN"))
    config.addAuthorizer("custom", new AuthenticationAuthorizationController)
    config.setHttpActionAdapter(new AuthenticationRoutingController())
    bind(classOf[Config]).toInstance(config)

    // for test purposes: profile timeout = 60 seconds
    // config.getSessionStore.asInstanceOf[PlayCacheStore].setProfileTimeout(60)

    // callback
    val callbackController = new CallbackController()
    callbackController.setDefaultUrl("/?defaulturlafterlogout")
    bind(classOf[CallbackController]).toInstance(callbackController)

    // logout
    val logoutController = new ApplicationLogoutController()
    logoutController.setDefaultUrl("/")
    bind(classOf[ApplicationLogoutController]).toInstance(logoutController)
  }
}
