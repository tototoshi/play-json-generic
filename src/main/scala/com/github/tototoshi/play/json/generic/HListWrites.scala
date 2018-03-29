package com.github.tototoshi.play.json.generic

import play.api.libs.json._
import shapeless._
import shapeless.labelled.FieldType

trait HListWrites {

  implicit val hnilWrites: OWrites[HNil] = OWrites[HNil] { hnil =>
    JsObject.empty
  }

  implicit def hlistWrites[K <: Symbol, H, T <: HList](
      implicit config: JsonConfiguration,
      witness: Witness.Aux[K],
      hWrites: Lazy[Writes[H]],
      tWrites: OWrites[T]): OWrites[FieldType[K, H] :: T] =
    OWrites[FieldType[K, H] :: T] {
      case (h :: t) =>
        val path = config.naming(witness.value.name)
        Json.obj(path -> hWrites.value.writes(h)) ++ tWrites.writes(t)
    }

}
