﻿using UnityEngine;

using System.Collections;
using System.Collections.Generic;

public class TouchInput : MonoBehaviour {
	
	public LayerMask touchMask;

	public SinglePlayer Logic;
	public theTime Counter;

	public Animator anim;



	public float DelayTime = 0.3f;
	public float DelayTime1 = 0.06f;

	private List<GameObject> GameList = new List<GameObject>();
	private List<GameObject> MatchList = new List<GameObject>();




	void Start(){
		Invoke ("animateVic", 120f);  //second paramenter must -1 to the value in the theTime class startTime float variable.

	}

	void animateVic(){
		anim.SetBool ("Vic", true);
	}

	void Update () {
	


		Logic.textScore ();
		Counter.textTime ();


		//alternate between methods if developed for web or touch screen devices = will not work at the same time.
		WebMouseDevice ();
//		TouchScreenDevice ();

		
		if (Counter.timedEnding ()) {
				Counter.paused ();
				Logic.disableBoardCollision ();
				

		}


	}//End of update method


	/// <summary>
	/// Use this method for Web Only (mouse input) 
	/// </summary>
	private void WebMouseDevice(){


		
		if (Input.GetMouseButtonDown (0) || Input.GetMouseButtonUp (0)) {
			
			Ray ray = camera.ScreenPointToRay (Input.mousePosition);
			RaycastHit hit; 

			if (Physics.Raycast (ray, out hit, touchMask)) {
				
				if (Input.GetMouseButtonDown (0)) { 
					GameObject gameButton = hit.transform.gameObject;

					if (!MatchList.Contains (gameButton)) {
						MatchList.Add (gameButton);
					}
					int lastHold = MatchList.Count -1;
					MatchList[lastHold].collider.SendMessage ("defaultColor", SendMessageOptions.DontRequireReceiver);
					MatchList[lastHold].collider.SendMessage ("CardHoverTexture", SendMessageOptions.DontRequireReceiver);
					foreach(GameObject g in MatchList){
						g.collider.SendMessage ("defaultColor", SendMessageOptions.DontRequireReceiver);

					}


				} //End of button down

				if (Input.GetMouseButtonUp (0)) {
					
					//if not buttons add buttons to list

					int count = MatchList.Count;
					
					switch (count) {
						
					case 1:
						MatchList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList [0].collider.SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						
						
						if (MatchList.Count > 1) {
							
							MatchList [0].collider.SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
							MatchList [1].collider.SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
							
							
							if (Logic.matchCards ()) {
								
								MatchList [0].collider.SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
								MatchList [1].collider.SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
								
								MatchList [0].collider.SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
								MatchList [1].collider.SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
								MatchList.RemoveAt (0);
								MatchList.RemoveAt (0);

								//TODO this if statement is for countdown only.

								if(Logic.getEndGame()%20 == 0){
									Logic.reDeal();
								}
								
							} else {
								
								StartCoroutine (DelayRoutine ());
								
								
							}
							
						}
						break;
						
						
					case 2:
						
						MatchList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList[1].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList [0].collider.SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						MatchList [1].collider.SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						
						
						MatchList [0].collider.SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
						MatchList [1].collider.SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
						
						if (Logic.matchCards ()) {
							MatchList [0].collider.SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
							MatchList [1].collider.SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
							
							MatchList [0].collider.SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
							MatchList [1].collider.SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
							MatchList.RemoveAt (0);
							MatchList.RemoveAt (0);

							//TODO this if statement is for countdown only.
							if(Logic.getEndGame()%20 == 0){
								Logic.reDeal();
							}
							
						} else {
							StartCoroutine (DelayRoutine ());
							
						}
						
						break;
						
					default: 
						
						while (MatchList.Count > 1) {
							MatchList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
							MatchList [1].collider.SendMessage ("CardBackTexture", SendMessageOptions.DontRequireReceiver);
							MatchList.RemoveAt (1);
						}
						
						break;
						
					}  //End of switch
					
					

					
					
				}//End of Up
				
			}//End of Physics TouchMask


		}//End of Down/Up Button


	} //End of WebDevice Method


	/// <summary>
	/// 
	/// use this method when finalizing with a multi-touch screen device (android or ipad: Multitouch input)
	/// 
	/// </summary>
	private void TouchScreenDevice(){

		
		
		foreach (Touch touch in Input.touches){

			//test if gameobject is being hit with screen hold.
			Ray ray = camera.ScreenPointToRay(touch.position);
			RaycastHit hit;

			//if object and screen toucch is colliding.
			if(Physics.Raycast(ray,out hit, touchMask)){




				//if finger held down or moving on screen
				if(touch.phase == TouchPhase.Moved || touch.phase == TouchPhase.Stationary ){


					GameObject game = hit.transform.gameObject;
					if(!GameList.Contains(game)){
						GameList.Add (game);
					}


					if(GameList.Count > Input.touchCount){


					
						int size = (GameList.Count - Input.touchCount);

						//TODO got an array out of bounds error here.
						for(int i = 0; i < size; i++){
							GameList[i].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
							GameList[i].collider.SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
							GameList.RemoveAt(i);
						}


					}


					if(GameList.Count == Input.touchCount){
						//hover texture if it equals

						for(int i = 0; i < GameList.Count; i++){
							GameList[i].collider.SendMessage("CardHoverTexture",SendMessageOptions.DontRequireReceiver);
						}

					}



				}//End Touch Stationary/Moved


				//if finger is lifted
				if(touch.phase == TouchPhase.Ended){

				
					//if not buttons add buttons to list
					while(GameList.Count > 0){


						if(!MatchList.Contains(GameList[0])){
							MatchList.Add (GameList[0]);
							MatchList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						}
						GameList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						GameList.RemoveAt(0);
					}

					//insures that buttons are default color and not wrong color
					int count = MatchList.Count;

					for(int i = 0; i < count ; i++){
						MatchList[i].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
					}

					//based on matching or game object count.
					switch(count){

					case 1:
						MatchList[0].collider.SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);


						if(MatchList.Count > 1){

							MatchList[0].collider.SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
							MatchList[1].collider.SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);


							if(Logic.matchCards()){

								MatchList[0].collider.SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
								MatchList[1].collider.SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
								
								MatchList[0].collider.SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
								MatchList[1].collider.SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
								MatchList.RemoveAt(0);
								MatchList.RemoveAt(0);

								//TODO this if statement is for countdown only.
								if(Logic.getEndGame()%20 == 0){
									Logic.reDeal();
								}

							}
							else{


								StartCoroutine(DelayRoutine());

								
							}

						}
						break;


					case 2:
						MatchList[0].collider.SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);
						MatchList[1].collider.SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);


						MatchList[0].collider.SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
						MatchList[1].collider.SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);

						if(Logic.matchCards()){
							MatchList[0].collider.SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
							MatchList[1].collider.SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);

							MatchList[0].collider.SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
							MatchList[1].collider.SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
							MatchList.RemoveAt(0);
							MatchList.RemoveAt(0);

							//TODO this if statement is for countdown only.
							if(Logic.getEndGame()%20 == 0){
								Logic.reDeal();
							}
						}
						else{
					
							StartCoroutine(DelayRoutine());

						}

						break;

					default: 
					
							while(MatchList.Count > 1){
								MatchList[1].collider.SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
								MatchList.RemoveAt (1);
							}

						break;
					
					}  //End of switch

				}//End of Touch Ended

			}//End if raycast Physic/Hit

		}//End of For Each Touch

	} //End of TouchScreenDevice Method




	IEnumerator DelayRoutine(){

		//should work regardless of player button smashing abilities.

		yield return new WaitForSeconds (DelayTime1);

		try{
			MatchList[0].collider.SendMessage("wrongColor",SendMessageOptions.DontRequireReceiver);
			MatchList[1].collider.SendMessage("wrongColor",SendMessageOptions.DontRequireReceiver);
		}
		catch{
			foreach(GameObject g in MatchList){
				g.collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
			}


				Debug.Log("Too fast, slow down with pressing the buttons!");


		}

		yield return new WaitForSeconds (DelayTime);
	
			
		for (int i = 0; i < MatchList.Count; i++) {
			MatchList[i].collider.SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
			MatchList[i].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
		}

		while (MatchList.Count > 0) {
			MatchList[0].collider.SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
			MatchList.RemoveAt(0);
		}

	}

	IEnumerator finalChance(){

		yield return new WaitForSeconds(3);
	}
	
	
	
	
}