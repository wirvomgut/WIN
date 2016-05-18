package models.daos

import java.sql.ResultSet
import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import models.User

import scala.concurrent.Future
import play.api.db._
import play.api.Play.current

/**
 * Give access to the user object.
 */
class  UserDAOImpl extends UserDAO {

  /**
    * Finds a user by its login info.
    *
    * @param loginInfo The login info of the user to find.
    * @return The found user or None if no user for the given login info could be found.
    */
  def find(loginInfo: LoginInfo) = {
    println(loginInfo)
    var user: Option[User] = None

    DB.withConnection(conn => {
      val r = conn.createStatement().executeQuery(
        s"""
            SELECT * FROM users WHERE loginInfoId='${loginInfo.providerID}' AND loginInfoKey='${loginInfo.providerKey}'
          """.stripMargin)

      user = extractUser(r)
    })

    Future.successful(user)
  }

  /**
   * Finds a user by its user ID.
   *
   * @param userID The ID of the user to find.
   * @return The found user or None if no user for the given ID could be found.
   */
  def find(userID: UUID) = {
    println(userID)
    var user:Option[User] = None

    DB.withConnection(conn => {
      val r = conn.createStatement().executeQuery(
        s"""
        select * from users where userID='${userID.toString}'
      """.stripMargin)

      user = extractUser(r)
    })

    Future.successful(user)
  }

  def extractUser(r:ResultSet) = {
    var user:Option[User] = None

    if(r.first()){
      user = Option(User(
        userID = UUID.fromString(r.getString("userID")),
        loginInfo = LoginInfo(providerID = r.getString("loginInfoId"), providerKey = r.getString("loginInfoKey")),
        firstName = Option(r.getString("firstName")),
        lastName = Option(r.getString("lastName")),
        fullName = Option(r.getString("fullName")),
        email = Option(r.getString("email")),
        avatarURL = Option(r.getString("avatarURL"))
      ))
    }

    user
  }

  /**
   * Saves a user.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: User) = {
    DB.withConnection(conn => {
      conn.createStatement().execute(
        s"""
        INSERT INTO users (
          userID, loginInfoId, loginInfoKey, firstName, lastName, fullName, email, avatarURL
        ) values (
          '${user.userID}', '${user.loginInfo.providerID}', '${user.loginInfo.providerKey}', '${user.firstName.orNull}',
          '${user.lastName.orNull}', '${user.fullName.orNull}', '${user.email.orNull}', '${user.avatarURL.orNull}'
        )

      """.stripMargin)
    })

    Future.successful(user)
  }
}
