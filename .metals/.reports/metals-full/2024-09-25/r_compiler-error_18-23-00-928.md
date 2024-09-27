file://<WORKSPACE>/app/repository/TransactionRepository.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 941
uri: file://<WORKSPACE>/app/repository/TransactionRepository.scala
text:
```scala
package repository

import models.Transaction
import slick.jdbc.PostgresProfile.api._
import javax.inject.{Inject, Singleton}
import scala.concurrent.{Future, ExecutionContext}

class TransactionTable(tag: Tag) extends Table[Transaction](tag, "transactions") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def account = column[Long]("account")
  def totalAmount = column[Double]("totalAmount")
  def mcc = column[String]("mcc")
  def merchant = column[String]("merchant")

  (id: Option[Long], account: Option[Long], totalAmount: Option[Double], mcc: String, merchant: String)

  def * = (id.?, name, color) <> ((Transaction.apply _).tupled, Transaction.unapply)
}

@Singleton
class FruitRepository @Inject()(db: Database)(implicit ec: ExecutionContext) {
  val transactions = TableQuery[TransactionTable]

  def create(name: String, color: String): Future[Long] = {
    val transaction = Transaction(None, account, totalAmount,@@)
    db.run((transactions returning transactions.map(_.id)) += transaction)
  }

  def list(): Future[Seq[Fruit]] = db.run(transactions.result)
}

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0