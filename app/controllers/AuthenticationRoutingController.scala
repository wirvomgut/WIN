package controllers

import org.pac4j.core.context.{HttpConstants, WebContext}
import org.pac4j.play.http.DefaultHttpActionAdapter
import play.mvc.Results

class AuthenticationRoutingController extends DefaultHttpActionAdapter {

  override def adapt(code: Int, context: WebContext): Object = {
    if (code == HttpConstants.UNAUTHORIZED) {
      Results.unauthorized(views.html.error401.render().toString()).as(HttpConstants.HTML_CONTENT_TYPE)
    } else if (code == HttpConstants.FORBIDDEN) {
      Results.forbidden(views.html.error403.render().toString()).as(HttpConstants.HTML_CONTENT_TYPE)
    } else {
      super.adapt(code, context)
    }
  }
}
