import play.Project._

name := "omavalvonta"

version := "1.0"

playJavaSettings

libraryDependencies ++= Seq(
	javaCore,
	javaJdbc,
	javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
	filters,
	"org.hibernate" % "hibernate-core" % "4.3.5.Final",
	"org.hibernate" % "hibernate-envers" % "4.3.5.Final",
	"mysql" % "mysql-connector-java" % "5.1.30",
	"org.hibernate" % "hibernate-jpamodelgen" % "4.3.5.Final",
	"org.xhtmlrenderer" % "core-renderer" % "R8",
	"net.sf.jtidy" % "jtidy" % "r938",
	"pdf" % "pdf_2.10" % "0.5" notTransitive()
)

resolvers += Resolver.url("Violas Play Modules", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)

javacOptions ++= Seq("-s", "metamodel")

javacOptions += "-Xlint:all"

scalaJavaUnidocSettings