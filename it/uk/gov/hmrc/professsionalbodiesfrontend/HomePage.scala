package uk.gov.hmrc.professsionalbodiesfrontend

import org.openqa.selenium.{By, WebDriver}

object HomePage extends WebPage with NavigationSugar {

  override val url: String = "http://localhost:6001/professional-bodies"

  override def isCurrentPage: Boolean = find(cssSelector("h1"))
    .fold(false)(_.text == "Approved professional organisations and learned societies for Tax Relief")

  def goToSearch = {
    clickOnElement(By.id("search"))
  }

  def enterSearchTerm(term: String)(implicit age: Int) = {
    webDriver.findElement(By.id("search")).sendKeys(term)
  }
}
