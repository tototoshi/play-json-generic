package com.github.tototoshi.play.json

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.{Json, JsonConfiguration, JsonNaming}

case class Person(firstName: String, lastName: String, friends: List[Person])

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

class GenericTest extends FunSuite with Matchers {

  import com.github.tototoshi.play.json.generic._

  test("it can read and write Json") {
    val person = Person("John",
                        "Lennon",
                        List(Person("Paul", "McCartney", Nil),
                             Person("George", "Harrison", Nil),
                             Person("Ringo", "Starr", Nil)))

    val json = """{
                  |  "firstName" : "John",
                  |  "lastName" : "Lennon",
                  |  "friends" : [ {
                  |    "firstName" : "Paul",
                  |    "lastName" : "McCartney",
                  |    "friends" : [ ]
                  |  }, {
                  |    "firstName" : "George",
                  |    "lastName" : "Harrison",
                  |    "friends" : [ ]
                  |  }, {
                  |    "firstName" : "Ringo",
                  |    "lastName" : "Starr",
                  |    "friends" : [ ]
                  |  } ]
                  |}""".stripMargin

    Json.prettyPrint(Json.toJson(person)) should be(json)
    Json.parse(json).as[Person] should be(person)
  }

  test("JsonNaming.SnakeCase") {
    implicit val config: JsonConfiguration =
      JsonConfiguration(JsonNaming.SnakeCase)

    val people = Person("John",
                        "Lennon",
                        List(Person("Paul", "McCartney", Nil),
                             Person("George", "Harrison", Nil),
                             Person("Ringo", "Starr", Nil)))

    val json = """{
                 |  "first_name" : "John",
                 |  "last_name" : "Lennon",
                 |  "friends" : [ {
                 |    "first_name" : "Paul",
                 |    "last_name" : "McCartney",
                 |    "friends" : [ ]
                 |  }, {
                 |    "first_name" : "George",
                 |    "last_name" : "Harrison",
                 |    "friends" : [ ]
                 |  }, {
                 |    "first_name" : "Ringo",
                 |    "last_name" : "Starr",
                 |    "friends" : [ ]
                 |  } ]
                 |}""".stripMargin

    Json.prettyPrint(Json.toJson(people)) should be(json)
    Json.parse(json).as[Person] should be(people)
  }

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
