package com.ravenxq.home.command

import com.ravenxq.home.voice._
import com.ravenxq.home.events._
import com.dt.iTunesController.iTunes;


object ITunesGeneralVolumeCommand {
	lazy val INSTANCE = new ITunesGeneralVolumeCommand
	def apply() = INSTANCE
}

class ITunesGeneralVolumeCommand private {
  var beforeVolume:int = 0	

  def exec(event: HomeEvent) {
	 val player = new iTunes
     
     player.setSoundVolume(if(event.name == "beforeTalk") {
    	 						beforeVolume = player.getSoundVolume
    	 						if(beforeVolume > 25) 25 else beforeVolume 
    	 				   }
    		 			   else beforeVolume)
  }

}