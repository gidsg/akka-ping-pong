/**
 * Created by zurabkakabadze on 18/09/2014.
 */

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, Actor, ActorLogging}

import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

case object Hello
case object Stop_Pong


class Ping(pong: ActorRef) extends Actor with ActorLogging {

    var counter = 100
  override def receive: Receive = {

    case Hello =>
      println("ping counter="+counter)
      counter = counter - 1
      if(counter ==0){
        pong ! Stop_Pong
        context.stop(self)
        context.system.shutdown
      }

      context.system.scheduler.scheduleOnce(Duration.create(500, TimeUnit.MILLISECONDS)){

        pong ! Hello

      }


  }
}


