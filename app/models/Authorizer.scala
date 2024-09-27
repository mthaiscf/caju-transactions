package models

import scala.collection.mutable

// Classe que representa a transação
case class Transaction(accountId: String, totalAmount: Double, mcc: String, merchant: String)

// Classe que representa a conta, com saldos para cada categoria
class Account(var foodBalance: Double, var mealBalance: Double, var cashBalance: Double)

// Objeto Authorizer responsável por processar as transações
object Authorizer {
  // Mapeia MCCs para categorias
  def mccToCategory(mcc: String): String = mcc match {
    case "5411" | "5412" => "FOOD"
    case "5811" | "5812" => "MEAL"
    case _ => "CASH"
  }

  // Substitui MCCs de acordo com comerciante, aqui temos uma lista mas poderia ser uma consulta em banco onde podemos armazenar mais informações
  def checkEstablishmentCategory(mcc: String, establishmentCategory: String): String = establishmentCategory match {
    case "EATS" => "MEAL" 
    case "TRIP" => "CASH"
    case "BILHETEUNICO" => "CASH"
    case "HORTIFRUTI" => "FOOD"
    case _ => mcc
  }

  // Método para autorizar a transação
  def authorize(account: Account, transaction: Transaction): String = {
    val category = mccToCategory(transaction.mcc)

    // verificação L3 (Dependente do comerciante)
    val shopCategory = checkEstablishmentCategory(category, transaction.merchant)

    shopCategory match {
      case "FOOD" if account.foodBalance >= transaction.totalAmount =>
        account.foodBalance -= transaction.totalAmount
        "00" // Aprovada
      case "MEAL" if account.mealBalance >= transaction.totalAmount =>
        account.mealBalance -= transaction.totalAmount
        "00" // Aprovada
      case "CASH" if account.cashBalance >= transaction.totalAmount =>
        account.cashBalance -= transaction.totalAmount
        "00" // Aprovada
      case _ =>
        "51" // Saldo insuficiente
    }
  }
}

// Objeto que simula um banco de dados de contas
object AccountDatabase {
  val accounts: mutable.Map[String, Account] = mutable.Map(
    "123" -> new Account(100.0, 50.0, 200.0), // Conta com saldo em todas as categorias
    "456" -> new Account(300.0, 0.0, 0.0), // Conta apenas saldo food
    "789" -> new Account(0.0, 500.0, 0.0), // Conta apenas saldo meal
    "159" -> new Account(0.0, 0.0, 200.0), // Conta apenas saldo cash
  )

  // Busca uma conta pelo accountId
  def getAccount(accountId: String): Option[Account] = accounts.get(accountId)
}
