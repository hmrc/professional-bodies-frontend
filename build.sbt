import play.core.PlayVersion.current
import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings

val appName = "professional-bodies-frontend"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin, SbtArtifactory)
  .settings(majorVersion := 0)
  .settings(
    Concat.groups := Seq(
      "javascripts/pbatr.js" -> group(
        (baseDirectory.value / "app" / "assets" / "javascripts") ** "*.js"
      )
    ),
    UglifyKeys.compressOptions := Seq(
      "unused=false",
      "dead_code=true"
    ),
    includeFilter in uglify := GlobFilter("pbatr*.js"),
    pipelineStages := Seq(digest),
    pipelineStages in Assets := Seq(
      concat,
      uglify
    )
  )
  .settings(publishingSettings: _*)
  .configs(IntegrationTest)
  .settings(integrationTestSettings(): _*)
  .settings(unmanagedResourceDirectories in IntegrationTest += baseDirectory ( _ /"target/web/public/test" ).value)
  .settings(resolvers += Resolver.jcenterRepo)

libraryDependencies ++= Seq(
  "uk.gov.hmrc"             %% "govuk-template"           % "5.25.0-play-25",
  "uk.gov.hmrc"             %% "play-ui"                  % "7.33.0-play-25",
  "uk.gov.hmrc"             %% "bootstrap-play-25"        % "4.9.0",
  "com.github.pureconfig"   %% "pureconfig"               % "0.9.2",
  "eu.timepit"              %% "refined"                  % "0.9.2",
  "eu.timepit"              %% "refined-pureconfig"       % "0.9.2",

  "org.scalatest"           %% "scalatest"                % "3.0.4"                 % "test",
  "org.jsoup"               %  "jsoup"                    % "1.10.2"                % "test",
  "com.typesafe.play"       %% "play-test"                % current                 % "test",
  "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test",
  "org.scalatestplus.play"  %% "scalatestplus-play"       % "2.0.0"                 % "test",
  "org.mockito"             %  "mockito-core"             % "2.23.4"                % "test"
)
