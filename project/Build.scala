import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "omavalvonta"
    val appVersion      = "1.0-SNAPSHOT-6"

    val appDependencies = Seq(
		javaCore,
		javaJdbc,
		javaJpa,
		filters,
		"org.hibernate" % "hibernate-entitymanager" % "4.2.1.Final",
		"org.hibernate" % "hibernate-envers" % "4.2.1.Final",
		"mysql" % "mysql-connector-java" % "5.1.25",
		"org.hibernate" % "hibernate-jpamodelgen" % "1.2.0.Final",
		"pdf" % "pdf_2.10" % "0.4.1"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
		ebeanEnabled := false,
		javacOptions ++= Seq("-s", "metamodel")
		,javacOptions += "-Xlint:all",
		resolvers += Resolver.url("Violas Play Modules", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)
    )
}
