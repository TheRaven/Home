package com.ravenxq.home.command

import com.ravenxq.home.voice._
import com.ravenxq.home.events._
import java.util.Calendar
import java.util.GregorianCalendar

object TimeCommand {
	
	def exec(homeEvent: HomeEvent) {
		val event = new TimeCommand
		event.exec(homeEvent)
	}
}

class TimeCommand {

  def exec(event: HomeEvent) {
	  println(event.name)
	  val calendar:Calendar = new GregorianCalendar

      Talker.say("Its <sayas class=\"time:hm\">" + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + "</sayas>")
  }

}