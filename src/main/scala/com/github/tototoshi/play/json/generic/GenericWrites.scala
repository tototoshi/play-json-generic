package com.github.tototoshi.play.json.generic

import play.api.libs.json._
import shapeless._
import shapeless.labelled.FieldType

trait GenericWrites {

  trait HListWrites[HList] {
    def writes(o: HList): Seq[(String, JsValue)]
  }
  implicit def hnilWrites: HListWrites[HNil] = new HListWrites[HNil] {
    override def writes(o: HNil): Seq[(String, JsValue)] =
      Seq.empty[(String, JsValue)]
  }

  implicit def hlistWrites[K <: Symbol, H, T <: HList](
      implicit config: JsonConfiguration,
      witness: Witness.Aux[K],
      hWrites: Lazy[Writes[H]],
      tWrites: HListWrites[T]): HListWrites[FieldType[K, H] :: T] =
    new HListWrites[FieldType[K, H] :: T] {
      override def writes(o: FieldType[K, H] :: T): Seq[(String, JsValue)] = {
        o match {
          case h :: t =>
            (config.naming(witness.value.name) -> hWrites.value.writes(h)) +: tWrites
              .writes(t)
          case hl => Seq.empty[(String, JsValue)]
        }
      }
    }

  implicit def genericWrites[T, U](implicit
                                   config: JsonConfiguration,
                                   gen: LabelledGeneric.Aux[T, U],
                                   writes: Lazy[HListWrites[U]]): OWrites[T] =
    OWrites[T] { o =>
      JsObject(writes.value.writes(gen.to(o)))
    }

}
