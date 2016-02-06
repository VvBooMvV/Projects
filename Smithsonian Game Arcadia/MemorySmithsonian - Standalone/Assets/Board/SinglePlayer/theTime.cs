using UnityEngine;
using System;
using UnityEngine.UI;
using System.Collections;

public class theTime : MonoBehaviour {


	public Text theText;
	public bool isEnded = false;
	public int maxMinutes = 0;
	public int maxSeconds = 0;


	private float theClock = 0;
	private string theTimer;

	public bool pause = false;
	private float temptime;
	private float startTime = 121f;  //time in seconds +1; change accordingly


	public void textTime(){

		if (!pause) {
				
			theClock += Time.deltaTime;

		}
		temptime = startTime - theClock;
		
		int minutes = (int) (temptime / 60);
		int seconds = (int) (temptime % 60);

		theTimer = string.Format("{0:00}:{1:00}", minutes, seconds);

		if (temptime <= 0) {
			pause = true;		
		}

//		guiText.text = "Time: " + theTimer;

		theText.text  = "Time " + theTimer;

	}

	public bool timedEnding(){

		if (temptime <=1){
			pause = true;
			isEnded = true;
//			Data.MUTE=1;
//			SaveData.SaveTheData();

		} 
		else {
			isEnded = false;	
		}

		return isEnded;
	}

	public void paused(){
		pause = true;
	}

	public void unpaused(){
		pause = false;
	}
}
