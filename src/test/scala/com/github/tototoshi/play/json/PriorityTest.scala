package com.github.tototoshi.play.json

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json._

class PriorityTest extends FunSuite with Matchers {

  case object A {
    implicit val writes: Writes[A.type] = Writes { a =>
      JsString("A")
    }
    implicit val reads: Reads[A.type] = Reads {
      case JsString("A") => JsSuccess(A)
      case _             => JsError("error")
    }
  }

  test("LowPriority implicit") {
    import generic.LowPriority._
    Json.toJson(A) should be(JsString("A"))
    Json.parse(""""A"""").as[A.type] should be(A)
  }

  test("HighPriority implicit") {
    import generic._
    Json.toJson(A) should be(JsObject.empty)
    Json.parse("""{}""").as[A.type] should be(A)
  }

}
