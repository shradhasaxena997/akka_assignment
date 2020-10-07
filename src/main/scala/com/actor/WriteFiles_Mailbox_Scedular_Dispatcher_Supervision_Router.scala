package com.actor

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool

import scala.concurrent.duration.DurationInt

object WriteFiles_Mailbox_Scedular_Dispatcher_Supervision_Router {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem.create("file-reader")
    val scanner = system.actorOf(RoundRobinPool(2).props(Props[FolderScannerActor]).withDispatcher("my-dispatcher"), "scanner")
    val directoryPath = getClass.getResource("/read_file").getPath
    implicit val ec = system.dispatcher
    scanner ! directoryPath
    system.scheduler.scheduleOnce(1.second)(scanner ! directoryPath)
  }

}