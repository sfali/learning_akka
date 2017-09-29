package actor.db

import akka.actor.{Actor, ActorLogging}
import akka.actor.Actor.Receive

import scala.collection.mutable

class DbActor extends Actor with ActorLogging {
  val db = new mutable.HashMap[String, Object]
  override def receive: Receive = {
    case (k: String, v: Object) => db.put(k, v)
    case o => log.info("unknown message ", o)
  }

}
