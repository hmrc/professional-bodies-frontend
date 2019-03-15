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
