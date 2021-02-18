package com.example

import com.example.UserRegistry.{JudgmentPerformed}

import spray.json.DefaultJsonProtocol

object JsonFormats  {
  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat2(User)

  implicit val judgementPerformedJsonFormat = jsonFormat2(JudgmentPerformed)
}