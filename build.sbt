
name := "ui-hl7-connector"

version := "0.1"

scalaVersion := "2.12.13"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

val scalatestVersion = "3.2.9"

resolvers += "Sonatype OSS Snapshots" at "https://s01.oss.sonatype.org/content/repositories/snapshots"
resolvers += "Sonatype OSS Releases" at "https://s01.oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "ca.uhn.hapi" % "hapi-structures-v23" % "2.3" % Provided,
  "org.apache.camel" % "camel-mllp" % "3.12.0",
  "org.apache.camel" % "camel-core" % "3.12.0",
  "io.minio" % "minio" % "8.3.1",
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)

test / parallelExecution := false
fork := true
assembly / test := {}

assembly / assemblyShadeRules := Seq(
  ShadeRule.rename("shapeless.**" -> "shadeshapless.@1").inAll
)

assembly / assemblyMergeStrategy := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.last
  case "META-INF/native/libnetty_transport_native_epoll_x86_64.so" => MergeStrategy.last
  case "META-INF/DISCLAIMER" => MergeStrategy.last
  case "mozilla/public-suffix-list.txt" => MergeStrategy.last
  case "overview.html" => MergeStrategy.last
  case "git.properties" => MergeStrategy.discard
  case "mime.types" => MergeStrategy.first
  case PathList("scala", "annotation", "nowarn.class" | "nowarn$.class") => MergeStrategy.first
  case x =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}

assembly / assemblyJarName := s"ui-hl7-connector.jar"