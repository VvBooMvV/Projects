using UnityEngine;
using System.Collections;

public class PauseButton : MonoBehaviour {

	public Animator anim;
	public theTime timer;
	public SinglePlayer Score;

	public void PauseTheGame()
	{


		anim.SetBool("Pause", !anim.GetBool("Pause") );

		if (anim.GetBool ("Pause")) {
				
			timer.paused();
			Score.disableBoardCollision();
		
		}
		else {
		
			timer.unpaused();
			Score.enableBoardCollision();
		}
	
	}
}
