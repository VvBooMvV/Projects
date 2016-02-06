using UnityEngine;
using System.Collections;

public class ChallengeAnimScript : MonoBehaviour {

	public Animator animBack;
	
	public void BackToMain(){
		
		animBack.SetBool("ChallBool", false);
		
	}
	
	public void ChallengeMenu(){
		animBack.SetBool("ChallBool", true);
	}
}
