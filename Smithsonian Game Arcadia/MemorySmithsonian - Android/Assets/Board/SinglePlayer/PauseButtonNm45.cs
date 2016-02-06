using UnityEngine;
using System.Collections;

public class PauseButtonNm45 : MonoBehaviour {
	
	public Animator anim;
	//	public theTime timer;
	public CountdownNm count;
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
