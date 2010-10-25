package com.ravenxq.home.command

import com.ravenxq.home.voice._
import com.ravenxq.home.events._

import java.io.File
import java.net.URL
import org.dom4j.Document
import org.dom4j.Node
import org.dom4j.io.SAXReader
import com.dt.iTunesController._


object ITunesCommand {
	lazy val INSTANCE = new ITunesCommand();
	def apply() = INSTANCE
}
	
	
class ITunesCommand private {
  var player:iTunes = null
  def exec(event: HomeEvent) {
	  
	  player = new iTunes
	  println("Rule is:"+event.rule)
	  
	  //TODO turn commands into individual case classes
	  event.rule match {
	 	  case "itunesPlayPauseCommand" => playPause(event.tags) 
	 	  case "itunesTrackChangeCommand" => trackChange(event.tags)
	 	  case "itunesCurrentSongCommand" => currentTrack
	 	  case "itunesVolumeCommand" => volume(event.tags)
	 	  case "itunesPlayRepeatCommand" => repeat(event.tags)
	 	  case "itunesMuteCommand" => mute
	 	  case "itunesPlayRandomCommand" => random(event.tags)
	  }
  }
  
  def playPause(tags:Array[String]) {
	  if(tags(0) == "PLAY") {
		  player.playPause
	  }
	  else {
	 	  player.pause
	  }
  }

  def trackChange(tags:Array[String]) {
		if (tags(0) == "PREVIOUS") {
            player.previousTrack
        } else if (tags(0) == "NEXT") {
            player.nextTrack
        }
  }
  
  
  def currentTrack() {
        val track = player.getCurrentTrack
        Talker.say("Currently playing is<break size=\"small\"/> " + track.getName + " by " + track.getArtist)
  }
 
  def volume(tags:Array[String]) {
	  if(tags(0) == "UP") {
		  player.setSoundVolume(player.getSoundVolume+10) 
	  }
	  else if(tags(0) == "DOWN") {
	 	  player.setSoundVolume(player.getSoundVolume-10)
	  }
  }
  
  def repeat(tags:Array[String]) {
	  if(tags(0) == "REPEAT_ALL") {
		  player.getCurrentPlaylist.setSongRepeat(ITPlaylistRepeatMode.ITPlaylistRepeatModeAll) 
	  }
	  else if(tags(0) == "REPEAT_ONE") {
	 	  player.getCurrentPlaylist.setSongRepeat(ITPlaylistRepeatMode.ITPlaylistRepeatModeOne)
	  }
	  else if(tags(0) == "REPEAT_NONE") {
	 	  player.getCurrentPlaylist.setSongRepeat(ITPlaylistRepeatMode.ITPlaylistRepeatModeOff)
	  }
  }
  
  def mute = player.setMute(!player.getMute)
  
  def random(tags:Array[String]) {
	  val shuffle = !(tags.length > 0 && tags(0) == "NOT")
	  player.getCurrentPlaylist.setShuffle(shuffle)
  }
  
}