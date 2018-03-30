package com.github.tototoshi.play.json

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.Json

case class TwentyThree(
    field1: Int,
    field2: Int,
    field3: Int,
    field4: Int,
    field5: Int,
    field6: Int,
    field7: Int,
    field8: Int,
    field9: Int,
    field10: Int,
    field11: Int,
    field12: Int,
    field13: Int,
    field14: Int,
    field15: Int,
    field16: Int,
    field17: Int,
    field18: Int,
    field19: Int,
    field20: Int,
    field21: Int,
    field22: Int,
    field23: Int
)

class TwentyThreeTest extends FunSuite with Matchers {

  import com.github.tototoshi.play.json.generic._

  test("23") {
    val o = TwentyThree(
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    )
    val json = """{
                 |  "field1" : 1,
                 |  "field2" : 1,
                 |  "field3" : 1,
                 |  "field4" : 1,
                 |  "field5" : 1,
                 |  "field6" : 1,
                 |  "field7" : 1,
                 |  "field8" : 1,
                 |  "field9" : 1,
                 |  "field10" : 1,
                 |  "field11" : 1,
                 |  "field12" : 1,
                 |  "field13" : 1,
                 |  "field14" : 1,
                 |  "field15" : 1,
                 |  "field16" : 1,
                 |  "field17" : 1,
                 |  "field18" : 1,
                 |  "field19" : 1,
                 |  "field20" : 1,
                 |  "field21" : 1,
                 |  "field22" : 1,
                 |  "field23" : 1
                 |}""".stripMargin

    Json.prettyPrint(Json.toJson(o)) should be(json)
    Json.parse(json).as[TwentyThree] should be(o)
  }

}
