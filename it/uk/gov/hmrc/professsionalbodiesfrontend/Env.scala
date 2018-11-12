package uk.gov.hmrc.professsionalbodiesfrontend

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxProfile}

import scala.util.{Properties, Try}

trait Env {
  val driver: WebDriver = createWebDriver

  lazy val createWebDriver: WebDriver = {
    Properties.propOrElse("test_driver", "chrome") match {
      case "chrome" => createChromeDriver()
      case "firefox" => createFirefoxDriver()
      case other => throw new IllegalArgumentException(s"target browser $other not recognised")
    }
  }

  def createChromeDriver(): WebDriver = {
    new ChromeDriver()
  }

  def createFirefoxDriver(): WebDriver = {
    val profile = new FirefoxProfile
    profile.setAcceptUntrustedCertificates(true)
    new FirefoxDriver(profile)
  }

  sys addShutdownHook {
    Try(driver.quit())
  }
}

object Env extends Env
