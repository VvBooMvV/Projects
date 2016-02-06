using UnityEngine;
using System.Collections;

public class NormalBac : MonoBehaviour {

	public Animator animBack;

	public void BackToMain(){

		animBack.SetBool("NormBool", false);

	}

	public void NormalMenu(){
		animBack.SetBool("NormBool", true);
	}
}
