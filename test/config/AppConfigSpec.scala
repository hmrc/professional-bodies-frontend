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

package config

import uk.gov.hmrc.test.unigration.UnigrationSpec

class AppConfigSpec extends UnigrationSpec {

  "app config" should {

    "have assets prefix" in {
      appConfig.assetsPrefix must be("http://localhost:9032/assets/3.2.4")
    }

    "have analytics token" in {
      appConfig.analyticsToken must be("N/A")
    }

    "have analytics host" in {
      appConfig.analyticsHost must be("auto")
    }

    "have report a problem partial URL" in {
      appConfig.reportAProblemPartialUrl must be("http://localhost:9250/contact/problem_reports_ajax?service=MyService")
    }

    "have report a problem non-JS URL" in {
      appConfig.reportAProblemNonJSUrl must be("http://localhost:9250/contact/problem_reports_nonjs?service=MyService")
    }

    "have professional bodies API URL" in {
      appConfig.professionalBodies must be("http://localhost:7401")
    }

    "know about list endpoint in professional bodies API configuration" in {
      appConfig.microservice.services.professionalBodies.listEndpointUrl must be(s"${appConfig.professionalBodies}/organisations")
    }

  }

}
