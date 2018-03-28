package com.github.tototoshi.play.json

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.{Json, JsonConfiguration, JsonNaming}

case class Person(firstName: String, lastName: String, friends: List[Person])

class GenericTest extends FunSuite with Matchers {

  import com.github.tototoshi.play.json.generic._

  test("it can read and write Json") {
    val people = Person("John",
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

    Json.prettyPrint(Json.toJson(people)) should be(json)
    Json.parse(json).as[Person] should be(people)
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

}
