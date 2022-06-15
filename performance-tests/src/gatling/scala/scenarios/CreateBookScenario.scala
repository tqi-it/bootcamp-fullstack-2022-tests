package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import simulations.BaseSimulation

object CreateBookScenario extends BaseSimulation {

  val create: ScenarioBuilder = scenario("Create Book")
    .exec(http("Create Book")
      .post("/books")
      .basicAuth("bootcamp", "vempratqi")
      .header("Content-type", "multipart/form-data")
      .formParam("name", "Teste de Software")
      .formParam("price", "999")
      .formParam("author_code", "A3ED5F82-EB8F-11EC-8EA0-0242AC120002")
      .formUpload("file", "src/gatling/resources/images/automacao-testes.png")
      .check(status.is(201))
      .check(jsonPath("$.code").saveAs("bookCode"))
    )

    .exec { session =>
      println("Código do Livro: --> " + session("bookCode").as[String].trim)
      session
    }
}
