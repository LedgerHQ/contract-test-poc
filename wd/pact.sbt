import com.itv.scalapact.plugin.ScalaPactPlugin._
import com.itv.scalapact.shared.ConsumerVersionSelector

// PACT ---------------------------------------------------------

// Broker
import scala.concurrent.duration._

val testTag = "v0.0.1-test"
val consumerName = "gate"
val providerVersion = "0.0.2"

pactBrokerAddress := "http://localhost"
pactBrokerCredentials := ("pactbroker", "PoC_P4CT!")

providerName := "wd"
providerVersionTags := List(providerVersion)
providerVersionTags := List("Test-provider")

consumerNames := Seq(consumerName)
consumerVersionSelectors := Seq(ConsumerVersionSelector(testTag, latest=false))

pactPublish := true

pactBrokerClientTimeout := 5.seconds

// Provider State
// Needed even if there is no required state...
// cf. http://io.itv.com/scala-pact/articles/verification-strategies.html#external-verification
// and http://io.itv.com/scala-pact/advanced/provider-states.html
providerStateMatcher := {
        // Useless state for demonstration purposes
  case key: String if key == "User Doe is 123 years old" =>
    true
      
  // TODO: create a default state?
}
// PACT ^-------------------------------------------------------^