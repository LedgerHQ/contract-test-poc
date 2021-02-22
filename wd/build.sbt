lazy val akkaHttpVersion = "10.2.3"
lazy val akkaVersion    = "2.6.12"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.ledger",
      scalaVersion    := "2.13.4"
    )),
    name := "wd_provider",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "ch.qos.logback"    % "logback-classic"           % "1.2.3",
      "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
    )
  )


// PACT ---------------------------------------------------------
// Verify: External Approach

enablePlugins(ScalaPactPlugin)

// https://mvnrepository.com/artifact/com.itv/scalapact-scalatest
val scalaPactVersion = "3.2.0"
val scalaTest = "3.3.0-SNAP3"
libraryDependencies ++= Seq(
  "org.http4s"    %% "http4s-blaze-server"   % "0.21.7",
  "org.http4s"    %% "http4s-dsl"            % "0.21.7",
  "org.http4s"    %% "http4s-circe"          % "0.21.7",
  "org.slf4j"     % "slf4j-simple"           % "1.6.4",
  "org.scalatest" %% "scalatest"             % scalaTest % "test",
  "com.itv"       %% "scalapact-circe-0-13"  % scalaPactVersion % "test",
  "com.itv"       %% "scalapact-http4s-0-21" % scalaPactVersion % "test",
  "com.itv"       %% "scalapact-scalatest"   % scalaPactVersion % "test",
  // Optional for auto-derivation of JSON codecs
  "io.circe" %% "circe-generic" % "0.13.0",
  // Optional for string interpolation to JSON model
  "io.circe" %% "circe-literal" % "0.13.0",
  )
// PACT ^-------------------------------------------------------^
