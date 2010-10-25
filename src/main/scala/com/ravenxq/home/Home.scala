package com.ravenxq.home
import scala.collection.mutable.Map

import com.ravenxq.home.voice._
import com.ravenxq.home.events._
import com.ravenxq.home.command._

object Home {
	def main(args : Array[String]) : Unit = {
		HomeEvents.register("com.ravenxq.home.time",  TimeCommand.exec )
		HomeEvents.register("com.ravenxq.home.weather", WeatherCommand.exec)
		HomeEvents.register("com.ravenxq.home.itunes", ITunesCommand().exec)
		HomeEvents.register("beforeTalk", ITunesGeneralVolumeCommand().exec)
		HomeEvents.register("afterTalk", ITunesGeneralVolumeCommand().exec)

		new Listener(List("grammars/time.txt","grammars/weather.txt","grammars/itunes.txt"))
	}
}
