package com.ravenxq.home.events
import scala.collection.mutable.Map

object HomeEvents {
	private val homeEvents:HomeEvents = new HomeEvents()
	
	def register (eventName:String, callBack: (HomeEvent) => Unit) = homeEvents.register(eventName,callBack)
	
	def trigger (eventName:String, event:HomeEvent) = homeEvents.trigger(eventName, event)
}

class HomeEvents {

	val callBacks = Map.empty[String,List[(HomeEvent) => Unit]]

	def register (eventName:String, callBack: (HomeEvent) => Unit) {
		if(!callBacks.contains(eventName)) {
			callBacks(eventName) = List()
		}
		callBacks(eventName) = callBack :: callBacks(eventName);
	}
	
	def trigger(eventName: String, event:HomeEvent) {
		println(event.name)
		if(callBacks.contains(eventName)) {
			callBacks(eventName).reverse.foreach(_(event))	
		}
	}
}