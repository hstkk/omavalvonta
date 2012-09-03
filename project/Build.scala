import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "omavalvonta"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
		"org.hibernate" % "hibernate-entitymanager" % "3.6.10.Final",
		"org.hibernate" % "hibernate-envers" % "3.6.10.Final",
		"mysql" % "mysql-connector-java" % "5.1.18",
		"pdf" % "pdf_2.9.1" % "0.3"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
		ebeanEnabled := false,
		resolvers += Resolver.url("My GitHub Play Repository", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)
    )

}
