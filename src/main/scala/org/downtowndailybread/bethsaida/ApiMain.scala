package org.downtowndailybread.bethsaida

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory


object ApiMain {
  def main(args: Array[String]): Unit = {
    val settings = new Settings(ConfigFactory.load())

    val server = new ApiMain(settings)

    server.run()
  }
}

class ApiMain(val settings: Settings) {

  val routes = path("") {
    complete("success")
  }

  def run() = {
    implicit val system = ActorSystem("bethsaida-api")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val workerSystem = ActorSystem("worker-api")

    val bindingFuture = Http().bindAndHandle(Route.handlerFlow(routes), settings.interface, settings.port)

    println(s"Server online at http://${settings.interface}:${settings.port}/")
  }
}
