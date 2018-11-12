package uk.gov.hmrc.professsionalbodiesfrontend

class HomePageSpec extends BaseSpec {

  feature("Home page") {

    scenario("Search for AABC gives a single match") {
      HomePage.goToPage()

      HomePage.clickInSearchBox()
      HomePage.enterSearchTerm("AABC")

      HomePage.assertCountOfMatchingOrganisations(1)
    }
  }
}
