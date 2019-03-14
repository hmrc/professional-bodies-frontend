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

package uk.gov.hmrc.test.unigration

import org.mockito.Mockito.when
import org.mockito.ArgumentMatchers._
import org.scalatest.mockito.MockitoSugar
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import uk.gov.hmrc.http.HeaderCarrier
import connectors.ProfessionalBodiesConnector

import scala.concurrent.Future

trait ProfessionalBodiesApiBehaviours extends UnigrationSpec with MockitoSugar {

  private lazy val mockConnector: ProfessionalBodiesConnector = mock[ProfessionalBodiesConnector]

  protected val defaultProfessionalBodies: Seq[String] = Seq(
    "AABC Register Ltd (Architects accredited in building conservation),from year 2016 to 2017",
    "Academic and Research Surgery Society of",
    "Academic Gaming and Simulation in Education and Training Society for",
    "Academic Primary Care Society for",
    "Access Consultants National Register of"
  )

  override protected def customise(builder: GuiceApplicationBuilder): GuiceApplicationBuilder =
    super.customise(builder).overrides(bind[ProfessionalBodiesConnector].to(mockConnector))

  def withProfessionalBodies(professionalBodies: Seq[String] = defaultProfessionalBodies)
                            (test: => Unit)
                            (implicit hc: HeaderCarrier): Unit = {
    when(mockConnector.getOrganisations()(any(), any())).thenReturn(Future.successful(professionalBodies))
    test
  }

}
