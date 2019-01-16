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

package uk.gov.hmrc.professionalbodiesfrontend.connectors

import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.play.bootstrap.http.HttpClient
import uk.gov.hmrc.professionalbodiesfrontend.config.AppConfig

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProfessionalBodiesConnector @Inject()(appConfig: AppConfig, httpClient: HttpClient){
  def getOrganisations()(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[Seq[String]] = {
    httpClient.GET[Seq[String]](s"${appConfig.professionalBodies}/organisations")

  }
}
