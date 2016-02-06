using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class MuteScript : MonoBehaviour {

	public Toggle MuteToggle;

	public void Awake(){

		LoadData.LoadTheData();
		startMute (Data.MUTE);

	}

	public void mute(){

		if (MuteToggle.isOn) {

			Data.MUTE = 1;	
			PlayerPrefs.SetInt("MUTE",1);
			AudioListener.volume = PlayerPrefs.GetInt("MUTE");

		} 
		else {

			Data.MUTE = 0;	
			PlayerPrefs.SetInt("MUTE",0);
			AudioListener.volume = PlayerPrefs.GetInt("MUTE");

		}
	}
	public void startMute(int volume){
		
		if (volume == 1) {

			AudioListener.volume = 1;
			MuteToggle.isOn = true;

		} 
		else {

			AudioListener.volume = 0;
			MuteToggle.isOn = false;

		}
	}

}
