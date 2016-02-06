using UnityEngine;
using System.Collections;

public class Victory : MonoBehaviour {

	public Animator anim;

	void Start(){

		anim = GetComponent<Animator> ();

	}
	public void VictoryScreen()
	{
		anim.SetBool ("Vic", true);
	}
}
