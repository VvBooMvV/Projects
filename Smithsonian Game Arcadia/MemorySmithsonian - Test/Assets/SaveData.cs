using UnityEngine;
using System.Collections;

public class SaveData  {

	public static void SaveTheData(){
		PlayerPrefs.SetInt ("MUTE", Data.MUTE);
	}

}
