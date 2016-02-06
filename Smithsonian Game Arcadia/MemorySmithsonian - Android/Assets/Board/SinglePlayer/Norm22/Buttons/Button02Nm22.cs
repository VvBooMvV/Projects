using UnityEngine;
using System.Collections;

public class Button02Nm22 : MonoBehaviour {
	
	
	
	public Texture BackTexture;   //neutral position
	public Texture HoverTexture;  //held down
	//	public Material HoverMat;
	
	
	int BNum = 1;  //Button Number
	int cardNumber; //Card (Random)
	
	public SinglePlayerNm22 Logic;
	//need SinglePlayer Object (or class) for shuffled values and textures (drag/drop in Unity Editor from the object with a SinglePlayer Script attached);
	public Color CorrectColor;
	public Color WrongColor;
	public Color DefaultColor;
	
	public void defaultColor(){
		GetComponent<Renderer>().material.color = DefaultColor;
	}
	
	public void changeColor (){
		GetComponent<Renderer>().material.color = CorrectColor;
	}
	public void wrongColor (){
		
		GetComponent<Renderer>().material.color = WrongColor;
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
		GetComponent<Renderer>().material.mainTexture = BackTexture;
	}
	
	
	public void CardHoverTexture(){
		//this method will send use single player to see status
		GetComponent<Renderer>().material.mainTexture = HoverTexture;
		//		renderer.material = HoverMat;
	}
	
	public void CardFlipTexture(){
		SetCardNumber ();
		GetComponent<Renderer>().material.mainTexture = Logic.getCard (cardNumber);
	}
	
	
	//disables object or button from being clicked again or not;
	public void enableButtonCollider(){
		GetComponent<Collider>().enabled = true;
	}
	
	public void disableButtonCollider(){
		GetComponent<Collider>().enabled = false;
	}
	
	
	//return values if needed
	public int getButtonNumber(){
		return BNum;
	}
	
	public int getCardNumber(){
		return cardNumber;
	}
}
