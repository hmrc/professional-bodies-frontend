package uk.gov.hmrc.professsionalbodiesfrontend

import org.openqa.selenium.{By, WebDriver}
import org.scalatest.{Assertions, Matchers}
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.WebBrowser

trait NavigationSugar extends WebBrowser with Eventually with Assertions with Matchers {

  def goTo(page: WebPage)(implicit webDriver: WebDriver) = {
    go to page
    on(page)
  }

  def clickOnSubmit() (implicit webDriver: WebDriver) = {
    webDriver.findElement(By.id("submit")).click()
  }

  def clickOnElement(locator: By)(implicit webDriver: WebDriver) = {
    webDriver.findElement(locator).click()
  }

  def verifyText(selectorId: String, expected: String)(implicit webDriver: WebDriver) = {
    webDriver.findElement(By.cssSelector(s"[$selectorId]")).getText shouldBe expected
  }

  def verifyHasLink(linkText: String)(implicit webDriver: WebDriver) = {
    webDriver.findElement(By.linkText(linkText)).isDisplayed shouldBe true
  }

  def on(page: WebPage)(implicit webDriver: WebDriver) = {
    eventually {
      webDriver.findElement(By.tagName("body"))
    }
    withClue(s"Currently in page: $currentUrl " + find(tagName("h1")).map(_.text).fold(" - ")(h1 => s", with title '$h1' - ")) {
      assert(page.isCurrentPage, s"Page was not loaded: ${page.url}")
    }
  }
}
