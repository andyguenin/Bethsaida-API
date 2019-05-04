package org.downtowndailybread.bethsaida.controller.service.schedule

import akka.http.scaladsl.server.Directives.{JavaUUID, as, entity, path, post}
import org.downtowndailybread.bethsaida.controller.Directives.futureComplete
import org.downtowndailybread.bethsaida.json.JsonSupport
import org.downtowndailybread.bethsaida.model.ScheduleDetail
import org.downtowndailybread.bethsaida.request.ServiceRequest
import org.downtowndailybread.bethsaida.request.util.DatabaseSource
import org.downtowndailybread.bethsaida.service.AuthenticationProvider

trait New {
  this: JsonSupport with AuthenticationProvider =>

  val schedule_newRoute = path(JavaUUID / "schedule" / "new") {
    serviceId =>
      authorizeNotAnonymous {
        implicit user =>
          post {
            entity(as[ScheduleDetail]) {
              detail =>
                futureComplete(DatabaseSource.runSql(c => new ServiceRequest(c).insertSchedule(serviceId, detail)))
            }
          }
      }
  }
}