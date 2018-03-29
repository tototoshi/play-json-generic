package com.github.tototoshi.play.json

import play.api.libs.json._
import shapeless._

package object generic extends HListWrites with HListReads {

  implicit def reads[T, U](implicit
                           config: JsonConfiguration,
                           gen: LabelledGeneric.Aux[T, U],
                           reads: Lazy[Reads[U]]): Reads[T] =
    Reads[T] { j =>
      reads.value.reads(j).map(gen.from)
    }

  implicit def writes[T, U](implicit
                            config: JsonConfiguration,
                            gen: LabelledGeneric.Aux[T, U],
                            writes: Lazy[Writes[U]]): Writes[T] =
    Writes[T] { o =>
      writes.value.writes(gen.to(o))
    }

}
