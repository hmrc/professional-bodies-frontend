/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package connectors

import config._
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{MustMatchers, WordSpec}
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.play.bootstrap.http.HttpClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProfessionalBodiesConnectorSpec extends WordSpec with MustMatchers with MockitoSugar with ScalaFutures {

  val someProfessionalBodies = Seq("foo", "bar", "baz")

  class Scenario(professionalBodies: Seq[String] = Seq.empty) {

    implicit def hc: HeaderCarrier = HeaderCarrier()

    val http = mock[HttpClient]
    val cfg = AppConfig(
      appName = "appName",
      contactFrontend = ContactFrontend("localhost"),
      assets = Assets("2.3.4"),
      googleAnalytics = GoogleAnalytics(),
      microservice = Microservice(
        services = Services(
          professionalBodies = ProfessionalBodies("localhost", 7401)
        )
      )
    )
    when(http.GET[Seq[String]](ArgumentMatchers.eq(cfg.professionalBodies + "/organisations"))
      (ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
    ).thenReturn(Future.successful(professionalBodies))
    val connector = new ProfessionalBodiesConnector(cfg, http)
  }

  "the connector" should {

    "return organisations" in new Scenario(someProfessionalBodies) {
      connector.getOrganisations().futureValue must be(someProfessionalBodies)
    }

  }

}
