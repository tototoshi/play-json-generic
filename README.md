# play-json-generic

Shapeless magic for play-json


```scala
scala> import com.github.tototoshi.play.json.generic._
import com.github.tototoshi.play.json.generic._

scala> case class Person(firstName: String, lastName: String, friends: List[Person])
defined class Person

scala> val people = Person("John",
     |                     "Lennon",
     |                     List(Person("Paul", "McCartney", Nil),
     |                          Person("George", "Harrison", Nil),
     |                          Person("Ringo", "Starr", Nil)))
people: Person = Person(John,Lennon,List(Person(Paul,McCartney,List()), Person(George,Harrison,List()), Person(Ringo,Starr,List())))

scala> import play.api.libs.json._
import play.api.libs.json._

scala> Json.prettyPrint(Json.toJson(people))
<console>:18: error: No Json serializer found for type Person. Try to implement an implicit Writes or Format for this type.
       Json.prettyPrint(Json.toJson(people))
                                   ^

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
