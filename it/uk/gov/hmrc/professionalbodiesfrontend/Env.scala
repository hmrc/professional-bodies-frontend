package uk.gov.hmrc.professionalbodiesfrontend

import java.net.URL

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxProfile}
import org.openqa.selenium.remote.{DesiredCapabilities, RemoteWebDriver}

import scala.util.{Properties, Try}

trait Env {
  val driver: WebDriver = createWebDriver

  lazy val createWebDriver: WebDriver = {
    Properties.propOrElse("test_driver", "chrome") match {
      case "chrome" => createChromeDriver()
      case "firefox" => createFirefoxDriver()
      case "remote-chrome" => createRemoteChromeDriver()
      case "remote-firefox" => createRemoteFirefoxDriver()
      case other => throw new IllegalArgumentException(s"target browser $other not recognised")
    }
  }

  def createRemoteChromeDriver() = {
    new RemoteWebDriver(new URL(s"http://localhost:4444/wd/hub"), DesiredCapabilities.chrome)
  }

  def createRemoteFirefoxDriver() = {
    new RemoteWebDriver(new URL(s"http://localhost:4444/wd/hub"), DesiredCapabilities.firefox)
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
