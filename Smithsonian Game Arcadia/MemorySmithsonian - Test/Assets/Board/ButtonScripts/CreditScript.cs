using UnityEngine;
using System.Collections;

public class CreditScript : MonoBehaviour {

	public Animator animBack;
	
	public void BackToMain(){
		
		animBack.SetBool("CredBool", false);
		
	}
	
	public void CreditMenu(){
		animBack.SetBool("CredBool", true);
	}
}
