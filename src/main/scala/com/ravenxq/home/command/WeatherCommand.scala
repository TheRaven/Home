package com.ravenxq.home.command

import com.ravenxq.home.voice._
import com.ravenxq.home.events._

import java.io.File;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

object WeatherCommand {
	
	def exec(homeEvent: HomeEvent) {
		val event = new WeatherCommand
		event.exec(homeEvent)
	}
}

class WeatherCommand {

  def exec(event: HomeEvent) {
	  println (event.name)
		val xmlReader = new SAXReader

        val doc:Document = xmlReader.read(new URL("http://weather.yahooapis.com/forecastrss?w=3534&u=c"))

        val node:Node = doc.selectSingleNode("//yweather:condition/@temp")
        val nodeCondition:Node = doc.selectSingleNode("//yweather:condition/@text")

        var temperature = Integer.parseInt(node.getStringValue())

        val modifier = if (temperature < 0)  "minus" else ""
        temperature = Math.abs(temperature)

        Talker.say("Condition is<break size=\"small\"/> " + nodeCondition.getStringValue() + " with a current temperature of<break size=\"small\"/> " + modifier + " " + temperature + " selsius")
  }

}