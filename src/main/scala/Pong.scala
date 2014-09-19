import akka.actor.{Props, Actor, ActorSystem}
import util.Random




  class Pong extends Actor {

    override def receive: Receive = {

      case Stop_Pong => println("  pong: Stopping")
                        context.stop(self)

      case Hello=>
        println("  pong")
        sender ! Hello
        val rndFloat = Random.nextFloat()
        if (rndFloat < 0.1){
        throw new Exception("OMG, not again!")
        }
    }

  }
  object PingPongTest extends App {
    val system = ActorSystem("PingPongSystem")
    val pong = system.actorOf(Props[Pong], name = "pong")
    val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
    ping ! Hello

  }



