package com.ravenxq.home.events

class HomeEvent(pname:String, prule:String, ptags:Array[String], ptokens:List[String]) {
	var name:String = pname
	var rule:String = prule
	var tags:Array[String] = ptags
	var tokens:List[String] = ptokens
}