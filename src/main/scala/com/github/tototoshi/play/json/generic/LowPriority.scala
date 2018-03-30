package com.github.tototoshi.play.json.generic

import shapeless._
import com.github.tototoshi.play.json.generic
import play.api.libs.json.{JsonConfiguration, Reads, Writes}

object LowPriority extends HListWrites with HListReads {

  implicit def reads[T, U](implicit
                           config: JsonConfiguration,
                           ev: LowPriority,
                           gen: LabelledGeneric.Aux[T, U],
                           reads: Lazy[Reads[U]]): Reads[T] =
    generic.reads(config, gen, reads)

  implicit def writes[T, U](implicit
                            config: JsonConfiguration,
                            ev: LowPriority,
                            gen: LabelledGeneric.Aux[T, U],
                            writes: Lazy[Writes[U]]): Writes[T] =
    generic.writes(config, gen, writes)

}
