package org.downtowndailybread.bethsaida.service

import java.util.UUID

trait UUIDProvider {

  def getUUID(): UUID = UUID.randomUUID()

  def parseUUID(id: String): UUID = UUID.fromString(id)
}