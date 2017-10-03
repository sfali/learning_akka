package api

import actor.db.DbActor
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes.{Created, OK}
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, parameters, path, post, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import models.{DbEntry, DbEntryProtocol}

import scala.concurrent.duration._

object Main extends App with DbEntryProtocol {

  implicit val system = ActorSystem("webserver-actor-system")
  implicit val materializer = ActorMaterializer()
  implicit def executionContext = system.dispatcher

  implicit val timeout = Timeout(2.seconds)

  val db = system.actorOf(Props[DbActor], "db")

  val routes: Route =
    path("db") {
      get {
        parameters('key.as[String]) { key =>
          val value = db ? key
          complete(value.mapTo[String])
        }
      } ~
        post {
          parameters('key.as[String], 'value.as[String]) { (key, value) =>
            db ! (key, value)
            complete(Created, s"$key stored.")
          }
        }
    } ~
      path("quit") {
        post {
          system.terminate()
          complete(OK, s"article database system exiting\n")
        }
      }

  val config = ConfigFactory.load()
  val bindingFuture =
    Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}
