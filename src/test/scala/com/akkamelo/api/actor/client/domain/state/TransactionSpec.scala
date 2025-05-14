package com.akkamelo.api.actor.client.domain.state

import org.scalatest.flatspec.AnyFlatSpec

class TransactionSpec extends AnyFlatSpec {

  "A Debit" should "be a Transaction" in {
    val debit = Debit(100,"desc")
    val debitIsATransaction = debit match {
      case t: Transaction => true
      case _ => false
    }
    assert(debitIsATransaction)

  }

  "A Credit" should "be a Transaction" in {
    val credit = Credit(100,"desc")
    val creditIsATransaction = credit match {
      case t: Transaction => true
      case _ => false
    }
    assert(creditIsATransaction)
  }

  "A Transaction" should "have a positive value" in {
    assertThrows[Exception] {
      Debit(-100, "desc")
    }
    assertThrows[InvalidTransactionException] {
      Credit(-100, "desc")
    }
    val credit = Credit(200, "desc")
    assert(credit.value == 200)
  }

  it should "have a description with size between 1 and 10" in {
    assertThrows[Exception] {
      Debit(100, "")
    }
    assertThrows[Exception] {
      Debit(100, "12345678901")
    }
    val credit = Credit(200, "1234567890")
    assert(credit.description == "1234567890")
  }

  it should "have a timestamp" in {
    val transaction: Transaction = Credit(200, "desc")
    assert(transaction.timestamp)
  }
}
