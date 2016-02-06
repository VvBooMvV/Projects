﻿using UnityEngine;
using System.Collections;

public class PauseButtonNm22 : MonoBehaviour {
	
	public Animator anim;
	//	public theTime timer;
	public Countdown33 count;
	public SinglePlayerNm22 Score;
	
	public void PauseTheGame()
	{
		
		
		anim.SetBool("Pause", !anim.GetBool("Pause") );
		
		if (anim.GetBool ("Pause")) {
			
			count.paused();
			Score.disableBoardCollision();
			
		}
		else {
			
			count.unpaused();
			Score.enableBoardCollision();
		}
		
	}
}
