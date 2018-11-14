import sbt.Tests.{Group, SubProcess}
import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings

import scala.util.Properties

val appName = "professional-bodies-frontend"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin, SbtArtifactory)
  .settings(
    majorVersion                     := 0,
    libraryDependencies              ++= AppDependencies.compile ++ AppDependencies.test
  )
  .settings(publishingSettings: _*)
  .configs(IntegrationTest)
  .settings(integrationTestSettings(): _*)
  .settings(unmanagedResourceDirectories in IntegrationTest +=  baseDirectory ( _ /"target/web/public/test" ).value)
  .settings(testGrouping in IntegrationTest := oneForkedJvmPerTest((definedTests in IntegrationTest).value))
  .settings(resolvers += Resolver.jcenterRepo)

def oneForkedJvmPerTest(tests: Seq[TestDefinition]) =
  tests map {
    test => Group(
      test.name,
      Seq(test),
      SubProcess(ForkOptions(runJVMOptions = Seq(s"-Dtest.name=${test.name}", s"-Dtest_driver=${Properties.propOrElse("test_driver", "chrome")}")))
    )
  }
