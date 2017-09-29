package actor.db

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, FlatSpec, FlatSpecLike, Matchers}

class DbActorSpec()
    extends TestKit(ActorSystem("test-system"))
    with FlatSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    super.afterAll()
    TestKit.shutdownActorSystem(system)
  }

  "a db actor" should "store a value" in {
    val db = system.actorOf(Props[DbActor])
  }
}
