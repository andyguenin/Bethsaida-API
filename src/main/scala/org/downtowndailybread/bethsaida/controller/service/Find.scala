package org.downtowndailybread.bethsaida.controller.service

import akka.http.scaladsl.server.Directives._
import org.downtowndailybread.bethsaida.controller.ControllerBase
import org.downtowndailybread.bethsaida.json.JsonSupport
import org.downtowndailybread.bethsaida.providers.{AuthenticationProvider, DatabaseConnectionProvider, SettingsProvider}
import org.downtowndailybread.bethsaida.request.ServiceRequest

trait Find extends ControllerBase {
  this: JsonSupport with AuthenticationProvider with DatabaseConnectionProvider with SettingsProvider =>

  val service_findRoute = path(JavaUUID) {
    id =>
      authorizeNotAnonymous {
        implicit user =>
          get {
            futureComplete(runSql(c => new ServiceRequest(settings, c).getService(id)))
          }
      }
  }
}
