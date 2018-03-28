package com.github.tototoshi.play.json

import play.api.libs.json.{JsObject, JsonConfiguration, OWrites, Reads}
import shapeless.{LabelledGeneric, Lazy}

package object generic {

  implicit def reads[T, U](implicit
                           config: JsonConfiguration,
                           gen: LabelledGeneric.Aux[T, U],
                           reads: Lazy[HListReads[U]]): Reads[T] =
    Reads[T] { j =>
      reads.value.reads(j).map(gen.from)
    }

  implicit def writes[T, U](implicit
                            config: JsonConfiguration,
                            gen: LabelledGeneric.Aux[T, U],
                            writes: Lazy[HListWrites[U]]): OWrites[T] =
    OWrites[T] { o =>
      JsObject(writes.value.writes(gen.to(o)))
    }

}
