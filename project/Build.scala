/**
 * Created by Lunang on 23.03.14.
 */

import sbt._
import Keys._
import play.Project._

 object ApplicationBuild extends {

  val appName = "MyProducts"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq (

    "net.sf.barcode4j" % "barcode4j" % "2.0"
  )

  val main = play.Project(appName,appVersion,appDependencies).settings(

  )
}





