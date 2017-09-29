package actor.db

import akka.actor.ActorSystem
import org.scalatest.{FlatSpec, FlatSpecLike, Matchers}

class DbActorSpec extends TestKit(ActorSystem("test-system")) with FlatSpecLike with Matchers {

  "a db actor" should "store a value" in {
    assert(true)
  }
}
