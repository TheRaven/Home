package com.ravenxq.home.voice

import com.ravenxq.home.events._
import java.io.FileReader;
import java.util.Locale;

import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.FinalRuleResult;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.ResultAdapter;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultToken;


class Listener(grammars: List[String]) extends ResultAdapter {
	val rec:Recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH));
	
	rec.allocate

	grammars.foreach(file => rec.loadJSGF(new FileReader(file)).setEnabled(true))
	rec.addResultListener(this)
	
	rec.commitChanges
	
	rec.requestFocus
	rec.resume
	        
    override def resultAccepted(e:ResultEvent) {
    	
        val result = e.getSource().asInstanceOf[FinalRuleResult]; 
        //we will remove the < and > from the rule name
        val rule = result.getRuleName(0)
        HomeEvents.trigger(result.getGrammar.getName,new HomeEvent(result.getGrammar.getName,rule.substring(1,rule.length-1),result.getTags,null))
    }

    
    override  def resultRejected(e:ResultEvent) {
    	println("Sorry I dont understand.")
    }
}
