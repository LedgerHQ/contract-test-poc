import com.itv.scalapact.plugin.ScalaPactPlugin._

// PACT ---------------------------------------------------------

// Provider State
// Needed even if there is no required state...
// cf. http://io.itv.com/scala-pact/articles/verification-strategies.html#external-verification
// and http://io.itv.com/scala-pact/advanced/provider-states.html
providerStateMatcher := {
  case key: String if key == "User Doe is 123 years old" =>
    true

  case key: String if key == "User Smith is 10 years old" =>
    true

  // TODO: create a default state?
}
// PACT ^-------------------------------------------------------^