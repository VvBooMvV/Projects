using UnityEngine;
using System.Collections;

public class LoadData {

	public static void LoadTheData(){
		Data.MUTE = PlayerPrefs.GetInt ("MUTE");

		if (Data.MUTE == 1) {
			AudioListener.volume = 1;			
		} 
		else {
			AudioListener.volume = 0;
		}
	}
}
