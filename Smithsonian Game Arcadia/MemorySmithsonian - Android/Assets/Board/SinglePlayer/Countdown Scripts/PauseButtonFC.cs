using UnityEngine;
using System.Collections;

public class PauseButtonFC : MonoBehaviour {
	
	public Animator anim;
//	public theTime timer;
	public Countdown count;
	public SinglePlayerFC Score;
	
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
