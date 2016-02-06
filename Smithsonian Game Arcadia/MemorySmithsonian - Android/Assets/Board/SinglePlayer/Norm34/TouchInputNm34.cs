using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using System.Collections.Generic;

public class TouchInputNm34 : MonoBehaviour {
	
	public LayerMask touchMask;
	
	public SinglePlayerNm34 Logic;
	public CountdownNm Counter;
	
	public Animator anim;
	
	
	public Image victoryImage;
	public Sprite incomplete;
	public Sprite complete;
	
	public float DelayTime = 0.3f;
	public float DelayTime1 = 0.06f;
	
	private List<GameObject> GameList = new List<GameObject>();
	private List<GameObject> MatchList = new List<GameObject>();
	
	
	
	
	
	void Update () {
		
		
		if (Counter.isZero()) {
			
			Counter.textTime();
			
			if(Counter.isPaused()){
				Logic.flipBoardDown();
				//TODO add this to win condition
				//				victoryImage.sprite = complete;
				//				victoryImage.sprite = incomplete;
				//				anim.SetBool ("Vic", true);
				//  Might not be necessary below
				////				Invoke ("animateVic", 0f);
				
			}
			else{
				Logic.flipBoardUp();
				
			}
			//count down calling method in counter texttime
			//if countdown is zero 
			
			//else flip board up
			//flip board in single player
			//
			
			
		} 
		else {
			
			//alternate between methods if developed for web or touch screen devices = will not work at the same time.
			//		WebMouseDevice ();
			TouchScreenDevice ();
			
		}
		
		
	}//End of update method
	
	
	/// <summary>
	/// Use this method for Web Only (mouse input) 
	/// </summary>
	private void WebMouseDevice(){
		
		
		
		if (Input.GetMouseButtonDown (0) || Input.GetMouseButtonUp (0)) {
			
			Ray ray = GetComponent<Camera>().ScreenPointToRay (Input.mousePosition);
			RaycastHit hit; 
			
			if (Physics.Raycast (ray, out hit, touchMask)) {
				
				if (Input.GetMouseButtonDown (0)) { 
					GameObject gameButton = hit.transform.gameObject;
					
					if (!MatchList.Contains (gameButton)) {
						MatchList.Add (gameButton);
					}
					int lastHold = MatchList.Count -1;
					MatchList[lastHold].GetComponent<Collider>().SendMessage ("defaultColor", SendMessageOptions.DontRequireReceiver);
					MatchList[lastHold].GetComponent<Collider>().SendMessage ("CardHoverTexture", SendMessageOptions.DontRequireReceiver);
					foreach(GameObject g in MatchList){
						g.GetComponent<Collider>().SendMessage ("defaultColor", SendMessageOptions.DontRequireReceiver);
						
					}
					
					
				} //End of button down
				
				if (Input.GetMouseButtonUp (0)) {
					
					//if not buttons add buttons to list
					
					int count = MatchList.Count;
					
					switch (count) {
						
					case 1:
						MatchList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList [0].GetComponent<Collider>().SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						
						
						if (MatchList.Count > 1) {
							
							MatchList [0].GetComponent<Collider>().SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
							MatchList [1].GetComponent<Collider>().SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
							
							
							if (Logic.matchCards ()) {
								
								MatchList [0].GetComponent<Collider>().SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
								MatchList [1].GetComponent<Collider>().SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
								
								MatchList [0].GetComponent<Collider>().SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
								MatchList [1].GetComponent<Collider>().SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
								MatchList.RemoveAt (0);
								MatchList.RemoveAt (0);
								
								
								
								if(Logic.getEndGame()%12 == 0){
									//TODO this if statement is for countdown only.
									//									Logic.reDeal();
									
									//final chance only
									//									victoryImage.sprite = complete;
									//									victoryImage.sprite = incomplete;
									anim.SetBool ("Vic", true);
									
								}
								
							} else {
								
								StartCoroutine (DelayRoutine ());
								//TODO change only for final chance
								//								Logic.disableBoardCollision();
								//								victoryImage.sprite = incomplete;
								//								anim.SetBool ("Vic", true);
								
							}
							
						}
						break;
						
						
					case 2:
						
						MatchList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList[1].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						MatchList [0].GetComponent<Collider>().SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						MatchList [1].GetComponent<Collider>().SendMessage ("CardFlipTexture", SendMessageOptions.DontRequireReceiver);
						
						
						MatchList [0].GetComponent<Collider>().SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
						MatchList [1].GetComponent<Collider>().SendMessage ("addButtonNum", SendMessageOptions.DontRequireReceiver);
						
						if (Logic.matchCards ()) {
							MatchList [0].GetComponent<Collider>().SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
							MatchList [1].GetComponent<Collider>().SendMessage ("changeColor", SendMessageOptions.DontRequireReceiver);
							
							MatchList [0].GetComponent<Collider>().SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
							MatchList [1].GetComponent<Collider>().SendMessage ("disableButtonCollider", SendMessageOptions.DontRequireReceiver);
							MatchList.RemoveAt (0);
							MatchList.RemoveAt (0);
							
							//TODO this if statement is for countdown only.
							if(Logic.getEndGame()%12 == 0){
								//								Logic.reDeal();
								//								victoryImage.sprite = complete;
								//								victoryImage.sprite = incomplete;
								anim.SetBool ("Vic", true);
							}
							
						} else {
							StartCoroutine (DelayRoutine ());
							//TODO change only for final chance
							//							Logic.disableBoardCollision();
							//							victoryImage.sprite = incomplete;
							//							anim.SetBool ("Vic", true);
						}
						
						break;
						
					default: 
						
						while (MatchList.Count > 1) {
							MatchList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
							MatchList [1].GetComponent<Collider>().SendMessage ("CardBackTexture", SendMessageOptions.DontRequireReceiver);
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
			Ray ray = GetComponent<Camera>().ScreenPointToRay(touch.position);
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
							GameList[i].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
							GameList[i].GetComponent<Collider>().SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
							GameList.RemoveAt(i);
						}
						
						
					}
					
					
					if(GameList.Count == Input.touchCount){
						//hover texture if it equals
						
						for(int i = 0; i < GameList.Count; i++){
							GameList[i].GetComponent<Collider>().SendMessage("CardHoverTexture",SendMessageOptions.DontRequireReceiver);
						}
						
					}
					
					
					
				}//End Touch Stationary/Moved
				
				
				//if finger is lifted
				if(touch.phase == TouchPhase.Ended){
					
					
					//if not buttons add buttons to list
					while(GameList.Count > 0){
						
						
						if(!MatchList.Contains(GameList[0])){
							MatchList.Add (GameList[0]);
							MatchList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						}
						GameList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
						GameList.RemoveAt(0);
					}
					
					//insures that buttons are default color and not wrong color
					int count = MatchList.Count;
					
					for(int i = 0; i < count ; i++){
						MatchList[i].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
					}
					
					//based on matching or game object count.
					switch(count){
						
					case 1:
						MatchList[0].GetComponent<Collider>().SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);
						
						
						if(MatchList.Count > 1){
							
							MatchList[0].GetComponent<Collider>().SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
							MatchList[1].GetComponent<Collider>().SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
							
							
							if(Logic.matchCards()){
								
								MatchList[0].GetComponent<Collider>().SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
								MatchList[1].GetComponent<Collider>().SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
								
								MatchList[0].GetComponent<Collider>().SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
								MatchList[1].GetComponent<Collider>().SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
								MatchList.RemoveAt(0);
								MatchList.RemoveAt(0);
								
								//TODO this if redeal statement is for countdown only.
								if(Logic.getEndGame()%12 == 0){
									//									Logic.reDeal();
									//									victoryImage.sprite = complete;
									//									victoryImage.sprite = incomplete;
									anim.SetBool ("Vic", true);
								}
								
							}
							else{
								
								
								StartCoroutine(DelayRoutine());
								//TODO change only for final chance
								//								Logic.disableBoardCollision();
								//								victoryImage.sprite = incomplete;
								//								anim.SetBool ("Vic", true);
								
							}
							
						}
						break;
						
						
					case 2:
						MatchList[0].GetComponent<Collider>().SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);
						MatchList[1].GetComponent<Collider>().SendMessage("CardFlipTexture",SendMessageOptions.DontRequireReceiver);
						
						
						MatchList[0].GetComponent<Collider>().SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
						MatchList[1].GetComponent<Collider>().SendMessage("addButtonNum",SendMessageOptions.DontRequireReceiver);
						
						if(Logic.matchCards()){
							MatchList[0].GetComponent<Collider>().SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
							MatchList[1].GetComponent<Collider>().SendMessage("changeColor",SendMessageOptions.DontRequireReceiver);
							
							MatchList[0].GetComponent<Collider>().SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
							MatchList[1].GetComponent<Collider>().SendMessage("disableButtonCollider",SendMessageOptions.DontRequireReceiver);
							MatchList.RemoveAt(0);
							MatchList.RemoveAt(0);
							
							//TODO this if statement is for countdown only.
							if(Logic.getEndGame()%12 == 0){
								//								Logic.reDeal();
								//								victoryImage.sprite = complete;
								//								victoryImage.sprite = incomplete;
								anim.SetBool ("Vic", true);
							}
						}
						else{
							
							StartCoroutine(DelayRoutine());
							//TODO change only for final chance
							//							Logic.disableBoardCollision();
							//							victoryImage.sprite = incomplete;
							//							anim.SetBool ("Vic", true);
							
						}
						
						break;
						
					default: 
						
						while(MatchList.Count > 1){
							MatchList[1].GetComponent<Collider>().SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
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
			MatchList[0].GetComponent<Collider>().SendMessage("wrongColor",SendMessageOptions.DontRequireReceiver);
			MatchList[1].GetComponent<Collider>().SendMessage("wrongColor",SendMessageOptions.DontRequireReceiver);
		}
		catch{
			foreach(GameObject g in MatchList){
				g.GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
			}
			
			
			Debug.Log("Too fast, slow down with pressing the buttons!");
			
			
		}
		
		yield return new WaitForSeconds (DelayTime);
		
		
		for (int i = 0; i < MatchList.Count; i++) {
			MatchList[i].GetComponent<Collider>().SendMessage("CardBackTexture",SendMessageOptions.DontRequireReceiver);
			MatchList[i].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
		}
		
		while (MatchList.Count > 0) {
			MatchList[0].GetComponent<Collider>().SendMessage("defaultColor",SendMessageOptions.DontRequireReceiver);
			MatchList.RemoveAt(0);
		}
		
	}
	
	IEnumerator finalChance(){
		
		yield return new WaitForSeconds(3);
	}
	
	
	
	
}