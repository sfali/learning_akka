package models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

case class DbEntry(key: String, value: String)

trait DbEntryProtocol extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val dbEntryFormat: RootJsonFormat[DbEntry] = jsonFormat2(DbEntry.apply)
}
