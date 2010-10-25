package com.ravenxq.home.command

import com.ravenxq.home.voice._
import com.ravenxq.home.events._

object MainCommand {
	def exec(homeEvent: HomeEvent):Unit = {
		val event = new MainCommand()
		event.exec(homeEvent)
	}
}

class MainCommand {
  def exec(event: HomeEvent):Unit = {
	  Talker.say("Yes?")
  }
}