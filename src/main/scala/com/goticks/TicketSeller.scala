package com.goticks

import akka.actor.{PoisonPill, Actor}

class TicketSeller extends Actor {
  import TicketProtocol._
  import spray.json._

  var lunches = Vector[Event]()

  def receive = {

    case GetEvents => {
      val event = Event(event = "E", nrOfTickets = 10)
      println(event.toJson)
      val events = Events(Vector[Event](event))
      sender ! events
    }
  }
}

object TicketProtocol {
  import spray.json._

  case class Event(event:String, nrOfTickets:Int)

  case object GetEvents

  case class Events(events:Vector[Event])

  case object EventCreated

  //----------------------------------------------
  // JSON
  //----------------------------------------------

  object Event extends DefaultJsonProtocol {
    implicit val format = jsonFormat2(Event.apply)
  }

}


