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

package models

import org.scalatest.{MustMatchers, WordSpec}
import play.api.libs.json.Json

class ProfessionalBodySpec extends WordSpec with MustMatchers {

  "professional body" should {

    "sort by name" in {
      val bodies = Seq(ProfessionalBody("b"), ProfessionalBody("a"), ProfessionalBody("c"))
      bodies.sorted must be(Seq(ProfessionalBody("a"), ProfessionalBody("b"), ProfessionalBody("c")))
    }

    "deserialize from JSON" in {
      Json.parse(
        """
          |{"name":"foo"}
        """.stripMargin).validate[ProfessionalBody].get must be(ProfessionalBody("foo"))
    }

  }

}
