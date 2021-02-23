import com.itv.scalapact.plugin.ScalaPactPlugin._
import com.itv.scalapact.shared.ConsumerVersionSelector

// PACT ---------------------------------------------------------

// Broker
import scala.concurrent.duration._

val testTag = "v0.0.1-test"
val consumerName = "gate"

pactBrokerAddress := "http://localhost"
pactBrokerCredentials := ("pactbroker", "PoC_P4CT!")

consumerNames := Seq(consumerName)
providerName := "wd"

// Select the tag to test
consumerVersionSelectors := Seq(ConsumerVersionSelector(testTag, latest=false))

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