package com.github.tototoshi.play.json.generic

import play.api.libs.functional.syntax._
import play.api.libs.json._
import shapeless._
import shapeless.labelled.FieldType

trait HListReads {

  implicit def hnilReads: Reads[HNil] = Reads[HNil] { _ =>
    JsSuccess(HNil)
  }

  implicit def hlistReads[K <: Symbol, H, T <: HList](
      implicit
      config: JsonConfiguration,
      witness: Witness.Aux[K],
      hReads: Lazy[Reads[H]],
      tReads: Reads[T]): Reads[FieldType[K, H] :: T] =
    Reads[FieldType[K, H] :: T] { j =>
      val key = config.naming(witness.value.name)
      val h = hReads.value
        .reads(j(key))
        .map(r => (t: T) => labelled.field[K](r) +: t)
      val t: JsResult[T] = tReads.reads(j)
      h(t)
    }

}
