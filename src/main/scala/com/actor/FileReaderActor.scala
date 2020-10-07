package com.actor

import java.io.File

import akka.actor.SupervisorStrategy.{Restart, Resume}
import akka.actor.{Actor, OneForOneStrategy, PoisonPill, Props}
import akka.dispatch.{BoundedMessageQueueSemantics, RequiresMessageQueue}
import akka.event.Logging

import scala.collection.mutable.ListBuffer
import scala.io.Source

class FileReaderActor extends Actor with RequiresMessageQueue[BoundedMessageQueueSemantics] {

  val log = Logging.getLogger(context.system, this)

  def receive = {
    case f: File => {
      log.info(s"Reading file ${f.getName}")
      var words = new ListBuffer[String]
      Source.fromFile(f).getLines().foreach(line => words += line)
      sender() ! words.toList
      self ! PoisonPill
    }
    case _ => log.info("Still waiting for a text file")
  }

  override val supervisorStrategy = OneForOneStrategy(loggingEnabled = false) {
    case fnd: NullPointerException => Resume
    case _: Exception => Restart
  }
}

object FileReaderActor {

  def props = Props(new FileReaderActor)

}