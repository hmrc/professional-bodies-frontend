package uk.gov.hmrc.professionalbodiesfrontend

import uk.gov.hmrc.professionalbodiesfrontend.ProfessionalBodiesStub.givenSomeOrganisationsAreReturned

class HomePageSpec extends BaseSpec {

  feature("Home page") {

    scenario("Page initially shows all organisations") {
      Given("the backend returns a list of organisations")
      givenSomeOrganisationsAreReturned()

      When("the user navigates to the page")
      HomePage.goToPage()

      Then("the count of organisations shown is 29")
      HomePage.countOfMatchingOrganisations() should be (29)
    }

    scenario("Search for AABC gives a single match") {
      Given("the backend returns a list of organisations")
      givenSomeOrganisationsAreReturned()

      And("the user navigates to the page")
      HomePage.goToPage()

      When("the user enters AABC into the search box")
      HomePage.clickInSearchBox()
      HomePage.enterSearchTerm("AABC")

      Then("the count of organisations shown is 1")
      HomePage.countOfMatchingOrganisations() should be (1)
    }

    scenario("Search for Am gives six matches") {
      Given("the backend returns a list of organisations")
      givenSomeOrganisationsAreReturned()

      And("the user navigates to the page")
      HomePage.goToPage()

      When("the user enters Am into the search box")
      HomePage.clickInSearchBox()
      HomePage.enterSearchTerm("Am")

      Then("the count of organisations shown is 6")
      HomePage.countOfMatchingOrganisations() should be (6)

    }
  }
}
