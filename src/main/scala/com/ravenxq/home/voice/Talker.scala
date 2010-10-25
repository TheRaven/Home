package com.ravenxq.home.voice


import com.ravenxq.home.events._
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.JSMLException;
import javax.speech.synthesis.Synthesizer;

object Talker 
{
	def say(toSay: String) {

		HomeEvents.trigger("beforeTalk",new HomeEvent("beforeTalk",null,null,null))
		
        val synth = Central.createSynthesizer(null)
        synth.allocate
        synth.resume

        synth.speak(toSay, null)
        synth.waitEngineState(Synthesizer.QUEUE_EMPTY)
        synth.deallocate

        HomeEvents.trigger("afterTalk",new HomeEvent("afterTalk",null,null,null))
	}
}
