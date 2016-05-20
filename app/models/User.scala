package models

import java.util.UUID
import com.mohiva.play.silhouette.api.{ Identity, LoginInfo }
import scalikejdbc._

/**
 * The user object.
 *
 * @param userID The unique ID of the user.
 * @param loginInfo The linked login info.
 * @param firstName Maybe the first name of the authenticated user.
 * @param lastName Maybe the last name of the authenticated user.
 * @param fullName Maybe the full name of the authenticated user.
 * @param email Maybe the email of the authenticated provider.
 * @param avatarURL Maybe the avatar URL of the authenticated provider.
 */
case class User(
  userID: UUID,
  loginInfo: LoginInfo,
  firstName: Option[String],
  lastName: Option[String],
  fullName: Option[String],
  email: Option[String],
  avatarURL: Option[String]) extends Identity


object User extends SQLSyntaxSupport[User]{
  override val tableName = "users"
  override val columns = Seq("userID", "loginInfoId", "loginInfoKey", "firstName", "lastName", "fullName", "email", "avatarURL")

  def apply(o: ResultName[User])(rs: WrappedResultSet) = new User(
    userID          = UUID.fromString(rs.string(o.userID)),
    loginInfo       = LoginInfo(rs.string(o.loginInfoId),rs.string(o.loginInfoKey)),
    firstName       = rs.stringOpt(o.firstName),
    lastName        = rs.stringOpt(o.lastName),
    fullName        = rs.stringOpt(o.fullName),
    email           = rs.stringOpt(o.email),
    avatarURL       = rs.stringOpt(o.avatarURL)
  )
}
