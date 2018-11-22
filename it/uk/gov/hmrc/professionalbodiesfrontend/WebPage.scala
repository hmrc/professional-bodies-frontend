package uk.gov.hmrc.professionalbodiesfrontend

import org.openqa.selenium.WebDriver
import org.scalatest.Matchers
import org.scalatest.selenium.{Page, WebBrowser}

case class Link(href: String, text: String)

trait WebLink extends Page with WebBrowser with Matchers {
  implicit val webDriver: WebDriver = Env.driver

  override def toString = this.getClass.getSimpleName
}

trait WebPage extends WebLink {

  def isCurrentPage: Boolean

  def heading = tagName("h1").element.text

  def bodyText = tagName("body").element.text

}