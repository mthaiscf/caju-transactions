name := """caju-transactions"""
organization := "tcfm.com"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.15"

//libraryDependencies += guice
//libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test

// Dependências necessárias para o projeto
libraryDependencies ++= Seq(
  guice, // Injeção de dependências usando o Guice
  ws, // Para suporte a WebServices
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,  // Biblioteca de testes Play
  "com.typesafe.play" %% "play-slick" % "5.0.0",                      // Play Slick para integração com banco de dados
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",           // Suporte a evoluções de banco de dados no Play
  "org.postgresql" % "postgresql" % "42.5.0",                         // Driver PostgreSQL
  "com.typesafe.slick" %% "slick" % "3.3.3",                          // Slick para manipulação do banco de dados
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",                 // HikariCP para gerenciamento de conexão
  "com.typesafe.play" %% "play-json" % "2.9.2",                       // Play JSON para trabalhar com JSON
  "org.playframework.twirl" %% "twirl-api" % "1.5.1",                  // Atualizar o Twirl API para evitar conflitos
  "org.mockito" %% "mockito-scala" % "1.16.42" % Test,
  "com.typesafe.play" %% "play-json" % "2.9.2", // Para JSON
  "com.typesafe.play" %% "play-test" % "2.9.2" % Test, // Para testes
  "org.mockito" %% "mockito-scala" % "1.16.37" % Test, // Mockito para testes unitários
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test // ScalaTest com Play
)

// Forçar a versão correta do scala-xml
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "tcfm.com.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "tcfm.com.binders._"
