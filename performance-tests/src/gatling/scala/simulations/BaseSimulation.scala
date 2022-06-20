package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder


class BaseSimulation extends Simulation {

  val httpConf: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")


}
