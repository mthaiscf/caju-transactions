import org.scalatest.funsuite.AnyFunSuite
import models.{Transaction, Account, Authorizer, AccountDatabase}

class AuthorizerSpec extends AnyFunSuite {

  test("Authorizer should approve a FOOD transaction when there is enough balance") {
    val account = new Account(foodBalance = 100.0, mealBalance = 50.0, cashBalance = 200.0)
    val transaction = Transaction(accountId = "123", totalAmount = 30.0, mcc = "5411", merchant = "Supermarket")

    val result = Authorizer.authorize(account, transaction)

    assert(result == "00") // Transação aprovada
    assert(account.foodBalance == 70.0) // O saldo de food deve ser atualizado
  }

  test("Authorizer should decline a FOOD transaction when there is not enough balance") {
    val account = new Account(foodBalance = 20.0, mealBalance = 50.0, cashBalance = 200.0)
    val transaction = Transaction(accountId = "123", totalAmount = 30.0, mcc = "5411", merchant = "Supermarket")

    val result = Authorizer.authorize(account, transaction)

    assert(result == "51") // Saldo insuficiente
    assert(account.foodBalance == 20.0) // O saldo de food não deve ser alterado
  }

  test("Authorizer should approve a MEAL transaction when there is enough balance") {
    val account = new Account(foodBalance = 100.0, mealBalance = 50.0, cashBalance = 200.0)
    val transaction = Transaction(accountId = "123", totalAmount = 20.0, mcc = "5812", merchant = "Restaurant")

    val result = Authorizer.authorize(account, transaction)

    assert(result == "00") // Transação aprovada
    assert(account.mealBalance == 30.0) // O saldo de meal deve ser atualizado
  }

  test("Authorizer should decline a CASH transaction when there is not enough balance") {
    val account = new Account(foodBalance = 100.0, mealBalance = 50.0, cashBalance = 10.0)
    val transaction = Transaction(accountId = "123", totalAmount = 50.0, mcc = "1234", merchant = "ATM")

    val result = Authorizer.authorize(account, transaction)

    assert(result == "51") // Saldo insuficiente
    assert(account.cashBalance == 10.0) // O saldo de cash não deve ser alterado
  }

  test("AccountDatabase should return the correct account for a valid accountId") {
    val accountOpt = AccountDatabase.getAccount("123")

    assert(accountOpt.isDefined) // A conta deve existir
    assert(accountOpt.get.foodBalance == 100.0)
  }

  test("AccountDatabase should return None for an invalid accountId") {
    val accountOpt = AccountDatabase.getAccount("999")

    assert(accountOpt.isEmpty) // A conta não deve existir
  }
}
