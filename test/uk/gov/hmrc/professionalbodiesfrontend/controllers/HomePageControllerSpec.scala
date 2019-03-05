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

package uk.gov.hmrc.professionalbodiesfrontend.controllers

import com.gu.scalatest.JsoupShouldMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.i18n.{DefaultLangs, DefaultMessagesApi}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.{Configuration, Environment}
import uk.gov.hmrc.professionalbodiesfrontend.config.AppConfig
import uk.gov.hmrc.professionalbodiesfrontend.connectors.ProfessionalBodiesConnector

import scala.concurrent.Future


class HomePageControllerSpec extends WordSpec with MustMatchers with GuiceOneAppPerSuite with MockitoSugar with JsoupShouldMatchers {
  val fakeRequest = FakeRequest("GET", "/")

  val env = Environment.simple()
  val configuration = Configuration.load(env)

  val messageApi = new DefaultMessagesApi(env, configuration, new DefaultLangs(configuration))
  val appConfig = new AppConfig(configuration, env)

  val mockConnector = mock[ProfessionalBodiesConnector]

  val controller = new HomePageController(mockConnector,messageApi, appConfig)

  def theConnectorWillReturnSomeOrganisations () = {

    val organisations = Seq("AABC Register Ltd (Architects accredited in building conservation),from year 2016 to 2017",
      "Academic and Research Surgery Society of",
      "Academic Gaming and Simulation in Education and Training Society for",
      "Academic Primary Care Society for",
      "Access Consultants National Register of")

    when(mockConnector.getOrganisations()(any(),any())).thenReturn(Future.successful(organisations))

  }

  "GET /" should {
    "return 200" in {
      theConnectorWillReturnSomeOrganisations()
      val result = controller.fetchProfessionalBodies()(fakeRequest)
      status(result) must be(Status.OK)

    }

    "return HTML" in {
      theConnectorWillReturnSomeOrganisations()
      val result = controller.fetchProfessionalBodies()(fakeRequest)
      contentType(result) must be(Some("text/html"))
      charset(result) must be(Some("utf-8"))
    }

    "markup letter heading with expected ID attribute" in {
      theConnectorWillReturnSomeOrganisations()
      val result = controller.fetchProfessionalBodies()(fakeRequest)
      contentAsString(result).asBodyFragment should include element withName("h2").withAttrValue("id", "a")
    }

  }
}