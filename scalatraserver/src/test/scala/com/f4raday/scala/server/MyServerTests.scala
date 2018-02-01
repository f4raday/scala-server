package com.f4raday.scala.server

import org.scalatra.test.scalatest._

class MyServerTests extends ScalatraFunSuite {

  addServlet(classOf[MyServer], "/*")

  test("GET / on MyServer should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
