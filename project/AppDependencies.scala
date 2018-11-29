import play.core.PlayVersion.current
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val compile = Seq(

    "uk.gov.hmrc"             %% "govuk-template"           % "5.25.0-play-25",
    "uk.gov.hmrc"             %% "play-ui"                  % "7.25.0-play-25",
    "uk.gov.hmrc"             %% "bootstrap-play-25"        % "3.14.0"
  )

  val test = Seq(
    "org.scalatest"           %% "scalatest"                % "3.0.4"                 % "test",
    "org.jsoup"               %  "jsoup"                    % "1.10.2"                % "test",
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test" % "0.2.0"                 % "test, it",
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "2.0.0"                 % "test, it",
    "org.seleniumhq.selenium" %  "selenium-java"            % "2.53.1"                % "test,it",
    "org.seleniumhq.selenium" %  "selenium-htmlunit-driver" % "2.52.0",
    "org.mockito"             %  "mockito-core"             % "2.23.4"                % "test, it",
    "com.github.tomakehurst"  %  "wiremock"                 % "1.58"                  % "test, it"

  )

}
