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
		"org.hibernate" % "hibernate-entitymanager" % "3.6.10.Final",
		"org.hibernate" % "hibernate-envers" % "3.6.10.Final",
		"mysql" % "mysql-connector-java" % "5.1.18"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
		ebeanEnabled := false
    )

}
