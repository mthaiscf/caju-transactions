package controllers

import play.api.libs.json._
import play.api.mvc._
import javax.inject._
import scala.concurrent.ExecutionContext
import models.{Transaction, AccountDatabase, Authorizer}

@Singleton
class TransactionController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  implicit val transactionReads: Reads[Transaction] = Json.reads[Transaction]

  def authorizeTransaction: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Transaction] match {
      case JsSuccess(transaction, _) =>
        AccountDatabase.getAccount(transaction.accountId) match {
          case Some(account) =>
            val resultCode = Authorizer.authorize(account, transaction)
            Ok(Json.obj("code" -> resultCode))
          case None =>
            Ok(Json.obj("code" -> "07")) // Problema com a conta
        }
      case JsError(errors) =>
        BadRequest(Json.obj("code" -> "07")) // Problema no payload
    }
  }
}
