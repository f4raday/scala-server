package com.f4raday.scala.server

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport

import scala.util.{Failure, Success, Try}

class MyServer extends ScalatraServlet with JacksonJsonSupport {

 protected implicit lazy val jsonFormats: Formats = DefaultFormats
 var messages = scala.collection.mutable.Map[Int, String]()

//  case class Message(id: Int, text: String)

  before() {
    contentType = formats("json")
  }

  get("/") {
    messages
  }
  
  
  post("/") {
    //parsedBody.extract[Message]
    val jsonString = request.body

    implicit val formats = DefaultFormats

    val jValue = parse(jsonString)
    println(jValue)

    //val mes = jValue.extract[Message]
    //println(mes)

    
    val id = pretty(render(jValue\"id")).toInt
    val text = pretty(render(jValue\"text"))

    messages += (id -> text) 


  }

  get("/:id") {
    Try {
      params("id").toInt
    } match {
      case Success(id) => messages(id)
      case Failure(ex) => pass()
    }
  }

  put("/:id") {
    Try {
      params("id").toInt
    } match {
      case Success(id) => messages(id)
      case Failure(ex) => pass()
    }
  }

  delete("/:id") {
    Try {
      params("id").toInt
    } match {
      case Success(id) => messages -= id
      case Failure(ex) => pass()
    }
  } 

  class Message(id: Int, text: String) {

  }

}
