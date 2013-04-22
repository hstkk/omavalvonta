import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "omavalvonta"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
		javaCore,
		javaJdbc,
		javaJpa,
		filters,
		"org.hibernate" % "hibernate-entitymanager" % "4.2.0.Final",
		"org.hibernate" % "hibernate-envers" % "4.2.0.Final",
		"mysql" % "mysql-connector-java" % "5.1.24",
		"org.hibernate" % "hibernate-jpamodelgen" % "1.2.0.Final"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
		ebeanEnabled := false,
		javacOptions ++= Seq("-s", "metamodel")
		,javacOptions += "-Xlint:all"
    )
}
