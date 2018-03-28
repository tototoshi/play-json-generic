# play-json-generic

![travis](https://travis-ci.org/tototoshi/play-json-generic.svg?branch=master)

Shapeless magic for play-json


## Install

```scala
libraryDependencies += "com.github.tototoshi" %% "play-json-generic" % "0.1.0"
```

## Usage

```scala
scala> import play.api.libs.json._
import play.api.libs.json._

scala> case class Person(firstName: String, lastName: String, friends: List[Person])
defined class Person

scala> val people = Person("John",
     |                     "Lennon",
     |                     List(Person("Paul", "McCartney", Nil),
     |                          Person("George", "Harrison", Nil),
     |                          Person("Ringo", "Starr", Nil)))
people: Person = Person(John,Lennon,List(Person(Paul,McCartney,List()), Person(George,Harrison,List()), Person(Ringo,Starr,List())))

scala> Json.prettyPrint(Json.toJson(people))
<console>:18: error: No Json serializer found for type Person. Try to implement an implicit Writes or Format for this type.
       Json.prettyPrint(Json.toJson(people))
                                   ^

scala> import com.github.tototoshi.play.json.generic._
import com.github.tototoshi.play.json.generic._

scala> Json.prettyPrint(Json.toJson(people))
res1: String =
{
  "firstName" : "John",
  "lastName" : "Lennon",
  "friends" : [ {
    "firstName" : "Paul",
    "lastName" : "McCartney",
    "friends" : [ ]
  }, {
    "firstName" : "George",
    "lastName" : "Harrison",
    "friends" : [ ]
  }, {
    "firstName" : "Ringo",
    "lastName" : "Starr",
    "friends" : [ ]
  } ]
}

scala> Json.parse(res1).as[Person]
res2: Person = Person(John,Lennon,List(Person(Paul,McCartney,List()), Person(George,Harrison,List()), Person(Ringo,Starr,List())))

```

