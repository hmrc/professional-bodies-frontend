package uk.gov.hmrc.professsionalbodiesfrontend

import org.openqa.selenium.WebDriver
import org.scalatest._
import org.scalatestplus.play.OneServerPerSuite
import play.api.inject.guice.GuiceApplicationBuilder


trait BaseSpec extends FeatureSpec with BeforeAndAfterAll with BeforeAndAfterEach with Matchers with OneServerPerSuite
  with GivenWhenThen with NavigationSugar {

  override lazy val port = 6001
  implicit val webDriver: WebDriver = Env.driver

  implicit override lazy val app = GuiceApplicationBuilder().configure(Map(
    "auditing.enabled" -> false,
    "auditing.traceRequests" -> false)).build()

  override protected def afterEach(): Unit = {
    webDriver.manage().deleteAllCookies()
  }
}
