package uk.gov.hmrc.professsionalbodiesfrontend

class HomePageSpec extends BaseSpec {

  feature("Home page") {

    scenario("Home page has a search box") {
      goTo(HomePage)
      HomePage.goToSearch

      HomePage.enterSearchTerm("AABC")
    }
  }
}
