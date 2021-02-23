import com.itv.scalapact.plugin.ScalaPactPlugin._
import com.itv.scalapact.shared.ConsumerVersionSelector

// PACT ---------------------------------------------------------

// Broker
import scala.concurrent.duration._

val testTag = "v0.0.1-test"

pactBrokerAddress := "http://localhost"
//For publishing to pact-broker test server (these credentials are public knowledge)
pactBrokerCredentials := ("pactbroker", "PoC_P4CT!")
consumerVersionSelectors := Seq(ConsumerVersionSelector(testTag, latest = false))
consumerNames := Seq("gate")
providerName := "wd"
pactBrokerClientTimeout := 5.seconds

// Provider State
// Needed even if there is no required state...
// cf. http://io.itv.com/scala-pact/articles/verification-strategies.html#external-verification
// and http://io.itv.com/scala-pact/advanced/provider-states.html
providerStateMatcher := {
  case key: String if key == "User Doe is 123 years old" =>
    true

  case key: String if key == "User Smith is 10 years old" =>
    true

  case key: String if key == "User ALICE is 20 years old" =>
    true
      
  // TODO: create a default state?
}
// PACT ^-------------------------------------------------------^