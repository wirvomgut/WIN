package controllers

import org.pac4j.core.context.Pac4jConstants
import org.pac4j.core.credentials.UsernamePasswordCredentials
import org.pac4j.core.credentials.authenticator.UsernamePasswordAuthenticator
import org.pac4j.core.exception.{CredentialsException, RequiresHttpAction}
import org.pac4j.core.profile.CommonProfile
import org.pac4j.core.util.CommonHelper

/**
  * Created by julia on 07.05.2016.
  */
class AuthenticationValidationController extends UsernamePasswordAuthenticator {

  @throws[RequiresHttpAction]
  def validate(credentials: UsernamePasswordCredentials) {
    if (credentials == null) {
      throwsException("No credential")
    }

    val username: String = credentials.getUsername
    val password: String = credentials.getPassword

    if (CommonHelper.isBlank(username)) {
      throwsException("Username cannot be blank")
    }
    if (CommonHelper.isBlank(password)) {
      throwsException("Password cannot be blank")
    }
    if (CommonHelper.areNotEquals(username, password)) {
      throwsException("Username : '" + username + "' does not match password")
    }
    val profile: CommonProfile = new CommonProfile
    profile.setId(username)
    profile.addAttribute(Pac4jConstants.USERNAME, username)
    credentials.setUserProfile(profile)
  }

  protected def throwsException(message: String) {
    throw new CredentialsException(message)
  }
}
