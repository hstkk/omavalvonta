import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    import Tasks._

    val appName         = "omavalvonta"
    val appVersion      = "1.0-SNAPSHOT-6-test"

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
        generateAPIDocsTask,
		ebeanEnabled := false,
		javacOptions ++= Seq("-s", "metamodel"),
		javacOptions += "-Xlint:all",
		resolvers += Resolver.url("Violas Play Modules", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)
    )

    object Tasks {
        val generateAPIDocsTask = TaskKey[Unit]("app-docs") <<= (fullClasspath in Test, compilers, streams) map { (classpath, cs, s) => 
        
          IO.delete(file("documentation/api"))

          // Scaladoc
          var scalaVersionForSbt = Option(System.getProperty("scala.version")).getOrElse("2.10")

          val sourceFiles = 
            (file("app") ** "*.scala").get ++ 
            (file("test") ** "*.scala").get ++ 
            (file("target/scala-" + scalaVersionForSbt + "/src_managed/main/views/html") ** "*.scala").get
          new Scaladoc(10, cs.scalac)(appName + " " + appVersion + " Scala API", sourceFiles, classpath.map(_.data), file("documentation/api/scala"), Nil, s.log)
          
          // Javadoc
          val javaSources = Seq(file("app"), file("test")).mkString(":")
          val javaApiTarget = file("documentation/api/java")
          val javaClasspath = classpath.map(_.data).mkString(":")
          val javaPackages = "controllers:models:utils"

          val cmd = <x>javadoc -windowtitle {appName} -doctitle {appName + "&nbsp;" + appVersion + "&nbsp;Java&nbsp;API"} -sourcepath {javaSources} -d {javaApiTarget} -subpackages {javaPackages} -classpath {javaClasspath}</x>
          cmd ! s.log

          println(appName + " " + appVersion + " Documentation generated under documentation/api/")
        }
    }
}
