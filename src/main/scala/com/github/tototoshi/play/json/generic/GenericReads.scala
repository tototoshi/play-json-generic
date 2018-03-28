package com.github.tototoshi.play.json.generic

import play.api.libs.json.{
  JsResult,
  JsSuccess,
  JsValue,
  JsonConfiguration,
  Reads
}
import shapeless._
import shapeless.labelled.FieldType

trait GenericReads {

  trait HListReads[HList] {
    def reads(j: JsValue): JsResult[HList]
  }

  implicit def hnilReads: HListReads[HNil] = new HListReads[HNil] {
    override def reads(j: JsValue): JsResult[HNil] = JsSuccess(HNil)
  }

  implicit def hlistReads[K <: Symbol, H, T <: HList](
      implicit
      config: JsonConfiguration,
      witness: Witness.Aux[K],
      hReads: Lazy[Reads[H]],
      tReads: HListReads[T]): HListReads[FieldType[K, H] :: T] =
    new HListReads[FieldType[K, H] :: T] {
      override def reads(j: JsValue): JsResult[FieldType[K, H] :: T] = {
        import play.api.libs.json.{JsResult, _}
        import play.api.libs.functional.syntax._
        val key = config.naming(witness.value.name)
        val h = hReads.value
          .reads(j(key))
          .map(r => (t: T) => labelled.field[K](r) +: t)
        val t: JsResult[T] = tReads.reads(j)
        h(t)
      }
    }

  implicit def genericReads[T, U](implicit
                                  config: JsonConfiguration,
                                  gen: LabelledGeneric.Aux[T, U],
                                  reads: Lazy[HListReads[U]]): Reads[T] =
    Reads[T] { j =>
      reads.value.reads(j).map(gen.from)
    }

}
