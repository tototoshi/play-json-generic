package com.github.tototoshi.play.json

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json._

class LowerPriorityTest extends FunSuite with Matchers {

  case object A {
    implicit val writes: Writes[A.type] = Writes { a =>
      JsString("A")
    }
    implicit val reads: Reads[A.type] = Reads {
      case JsString("A") => JsSuccess(A)
      case _             => JsError("error")
    }
  }

  import generic.LowPriority._

  test("LowerPriority implicit") {
    Json.toJson(A) should be(JsString("A"))
    Json.parse(""""A"""").as[A.type] should be(A)
  }

}
