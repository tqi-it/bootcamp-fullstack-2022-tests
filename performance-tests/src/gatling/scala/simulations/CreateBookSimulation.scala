package simulations

import io.gatling.core.Predef._
import scenarios.CreateBookScenario


class CreateBookSimulation extends BaseSimulation {
  setUp(
    CreateBookScenario.create.inject(rampUsers(2) during 2)
      .protocols(httpConf))
}
