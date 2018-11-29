package uk.gov.hmrc.professionalbodiesfrontend

import org.openqa.selenium.WebDriver
import org.scalatest._
import org.scalatestplus.play.OneServerPerSuite
import play.api.inject.guice.GuiceApplicationBuilder


trait BaseSpec extends FeatureSpec with BeforeAndAfterAll with BeforeAndAfterEach with Matchers with OneServerPerSuite
  with GivenWhenThen with NavigationSugar {

  override def beforeAll(): Unit = {
    ProfessionalBodiesStub.server.start()
  }

  override def afterAll(): Unit = {
    ProfessionalBodiesStub.server.stop()
  }

  override lazy val port = 6001
  implicit val webDriver: WebDriver = Env.driver

  implicit override lazy val app = GuiceApplicationBuilder().configure(Map(
    "auditing.enabled" -> false,
    "auditing.traceRequests" -> false,
    "microservice.services.professional-bodies.port" -> ProfessionalBodiesStub.port
  )).build()


  override protected def afterEach(): Unit = {
    ProfessionalBodiesStub.server.resetMappings()
    webDriver.manage().deleteAllCookies()
  }
}
