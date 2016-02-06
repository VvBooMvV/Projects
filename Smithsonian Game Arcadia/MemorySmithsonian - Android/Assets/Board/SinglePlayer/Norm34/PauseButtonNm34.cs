using UnityEngine;
using System.Collections;

public class PauseButtonNm34 : MonoBehaviour {
	
	public Animator anim;
	//	public theTime timer;
	public CountdownNm count;
	public SinglePlayerNm34 Score;
	
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
