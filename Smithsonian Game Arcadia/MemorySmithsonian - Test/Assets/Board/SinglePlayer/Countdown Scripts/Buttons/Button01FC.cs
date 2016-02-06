using UnityEngine;
using System.Collections;

public class Button01FC : MonoBehaviour {
	
	
	
	public Texture BackTexture;   //neutral position
	public Texture HoverTexture;  //held down
	//	public Material HoverMat;
	
	
	int BNum = 0;  //Button Number
	int cardNumber; //Card (Random)
	
	public SinglePlayerFC Logic;
	//need SinglePlayer Object (or class) for shuffled values and textures (drag/drop in Unity Editor from the object with a SinglePlayer Script attached);
	public Color CorrectColor;
	public Color WrongColor;
	public Color DefaultColor;
	
	public void defaultColor(){
		renderer.material.color = DefaultColor;
	}
	
	public void changeColor (){
		renderer.material.color = CorrectColor;
	}
	public void wrongColor (){
		
		renderer.material.color = WrongColor;
	}
	
	
	//testing if button is hit
	public void test(){
		Debug.Log ("Test " + BNum);
	}
	//adds to a list of object id (BNum) for testing match
	public void addButtonNum(){
		Logic.addCard (BNum);
	}
	
	//setting values for card number & .getCardNumber returns random card value as int;
	public void SetCardNumber(){
		cardNumber = Logic.getCardNumber (BNum);
	}
	
	//changes button textures when called:
	public void CardBackTexture(){
		renderer.material.mainTexture = BackTexture;
	}
	
	
	public void CardHoverTexture(){
		//this method will send use single player to see status
		renderer.material.mainTexture = HoverTexture;
		//		renderer.material = HoverMat;
	}
	
	public void CardFlipTexture(){
		SetCardNumber ();
		renderer.material.mainTexture = Logic.getCard (cardNumber);
	}
	
	
	//disables object or button from being clicked again or not;
	public void enableButtonCollider(){
		collider.enabled = true;
	}
	
	public void disableButtonCollider(){
		collider.enabled = false;
	}
	
	
	//return values if needed
	public int getButtonNumber(){
		return BNum;
	}
	
	public int getCardNumber(){
		return cardNumber;
	}
}
