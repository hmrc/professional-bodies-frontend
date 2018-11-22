package uk.gov.hmrc.professionalbodiesfrontend

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.client.WireMock._
import play.api.http.Status._
import play.api.http.HeaderNames._
import play.api.http.MimeTypes._
import play.api.libs.json.Json



object ProfessionalBodiesStub {

  val port = 11111
  val server = new WireMockServer(WireMockConfiguration.wireMockConfig().port(port))
  val mock = new WireMock("localhost", port)
  val url = s"http://localhost:$port"

  val organisations = Seq(
    "AABC Register Ltd (Architects accredited in building conservation),from year 2016 to 2017",
    "Academic and Research Surgery Society of",
    "Academic Gaming and Simulation in Education and Training Society for",
    "Academic Primary Care Society for",
    "Access Consultants National Register of",
    "Accident and Emergency Medicine British Association for",
    "Accountancy Association of Lecturers in",
    "Accountants and Auditors British Association of",
    "Accountants Association of International",
    "Accountants Natal Society of",
    "Accountants South African Institute of Professional (SAIPA)",
    "Accountants Transvaal Society of",
    "Accounting Association American",
    "Accounting Association British (Standing Committee for British Accounting Association)",
    "Accounting Association European",
    "Accounting Conference of Professors of",
    "Accounting Technicians Association of",
    "Accounting Technicians in Ireland Institute of",
    "Acoustical Society British",
    "Acoustical Society of America",
    "Acoustics Institute of",
    "Acquisition of Latin American Library Materials Seminar on the (SALALM)",
    "Actuarial Profession The (name for the Institute and Faculty of Actuaries)",
    "Back Pain Research Society for",
    "Bacteriologists Society of American (J - Applied Microbiology)",
    "Bakers Institute of British",
    "Radiation Biology European Society for",
    "Zoological Society of London (J - Proceedings)",
    "Zoological Society of Scotland Royal"
  )

  def givenSomeOrganisationsAreReturned () = {
    mock.register(get(urlPathEqualTo("/organisations"))
        .willReturn(aResponse()
          .withStatus(OK)
          .withHeader(CONTENT_TYPE, JSON)
            .withBody(Json.toJson(organisations).toString())
        ))
  }
}
