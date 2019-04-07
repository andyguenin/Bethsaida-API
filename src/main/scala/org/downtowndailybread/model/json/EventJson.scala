package org.downtowndailybread.model.json

import org.downtowndailybread.model.Event
import spray.json.{JsArray, JsBoolean, JsNumber, JsObject, JsString, JsValue, RootJsonWriter}

trait EventJson {

  this: ServiceJson =>

  implicit val eventFormat = new RootJsonWriter[Event] {
    override def write(event: Event): JsValue = {
      JsObject(
        ("id", JsNumber(event.id)),
        ("service", serviceFormat.write(event.service)),
        ("eventTime", JsString(event.eventTime.toString))
      )
    }
  }

  implicit val eventSeqFormat = new RootJsonWriter[Seq[Event]] {
    override def write(events: Seq[Event]): JsValue = {
      JsArray(events.map(eventFormat.write).toVector)
    }
  }
}
