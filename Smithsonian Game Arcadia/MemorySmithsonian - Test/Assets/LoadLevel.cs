using UnityEngine;
using System.Collections;

public class LoadLevel : MonoBehaviour {

	public void LoadTheLevel(int levelSub){
		Application.LoadLevel (levelSub);
	}
}
