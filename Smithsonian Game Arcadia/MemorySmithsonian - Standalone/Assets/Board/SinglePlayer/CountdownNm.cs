using UnityEngine;
using System;
using UnityEngine.UI;
using System.Collections;

public class CountdownNm : MonoBehaviour {
	
	
	public Text TheText;
	public bool isEnded = false;
	//	public int maxMinutes = 0;
	public int maxSeconds = 0;
	
	
	private float theClock = 0;
	private string theTimer;
	
	public bool pause = false;
	private float temptime;
	private float startTime = 6f;  //time in seconds +1; change accordingly
	
	bool done = true;
	
	public void textTime(){
		
		if (!pause) {
			
			theClock += Time.deltaTime;
			
		}
		temptime = startTime - theClock;
		
		int seconds = (int) (temptime % 60);
		
		theTimer = seconds.ToString();
		
		if (temptime <= 0) {
			pause = true;
			done = false;
			TheText.text = "";
		}
		else {			   
			TheText.text  = "Get Ready " + theTimer;
		}
		
		//		guiText.text = "Time: " + theTimer;
		
		
		
	}
	
	public bool isPaused(){
		return pause;
	}
	
	public bool isZero(){
		
		return done;
		
	}

	public void paused(){
		pause = true;
	}
	
	public void unpaused(){
		pause = false;
	}
	
}
