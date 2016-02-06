using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;
//if board size changes be sure to change denom in ShuffleCards and DealCards methods accordiningly


public class SinglePlayer : MonoBehaviour {
	
	public int score = 0;
	public Text theText;

	//these number should be shuffled for random set of cards that is hall the board size.
	int[] TextureListSize = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21}; //texture size board

	int[] MatchList = {0,1,2,3,4,5,6,7,8,9};  //for test deal.
	public Texture[] cards = new Texture[22];  //texture images added in the score object in hierarchy


	List<int> board;  //deals double list from ShuffledList   both are used in the DealShuffledCards Method below
	List<int> CardList; //a list to match values.  cleared when list is greater than 1;
	
	public AudioClip WrongSound;
	public AudioClip RightSound;
	public AudioSource BackgroundMusic;

	private int endgame = 0;  //counts cards matches.  when board is filled it will end.  can delete in match card method for timer method in theTimer class.


	public bool theEndTest = false;  //if the game has ended.  it may change with timer or values.
	public bool paused = false;


	//buttons classes to call specific methods in each button class to: show board / flip board, disable/enable buttons collision, etc.
	//these methods can be called from touchinput for update.  must drag button object in inspector.
	//NOTE: if any rows are deleted for different mode,  be sure to get the last 4 methods in this class also.

	//row 1
	public Button01 button1;
	public Button02 button2;
	public Button03 button3;
	public Button04 button4;

	//row 2
	public Button05 button5;
	public Button06 button6;
	public Button07 button7;
	public Button08 button8;

	//row 3
	public Button09 button9;
	public Button10 button10;
	public Button11 button11;
	public Button12 button12;

	//row 4
	public Button13 button13;
	public Button14 button14;
	public Button15 button15;
	public Button16 button16;

	//row 5
	public Button17 button17;
	public Button18 button18;
	public Button19 button19;
	public Button20 button20;




	void Awake () {

		DealShuffledCards ();
//		TestDeal();

		CardList = new List<int>();
	}
	


	//TODO add an update method to show current score/ times


	public void textScore(){
//		guiText.text = "Matches: " + score;
		theText.text = "Matches " + score;
	}


	/// <summary>
	/// Adds the card to cardList when object is collidied with touches.  
	/// grabs card from button## and places correct item for testing values are matching with the same cooresponding textures.
	/// </summary>
	/// <param name="cardNum">Card number.</param>
	public void addCard(int cardNum){


		int Board = board [cardNum];
		CardList.Add (Board);

	}
	/// <summary>
	/// Cards the list count.
	/// </summary>
	/// <returns>The list count.</returns>
	public int cardListCount(){
		//test how many cards are added to match all other classes.
		return CardList.Count;
	}

	/// <summary>
	/// theEndTest changes below in the matchCards method.  
	/// this is to tell touchinput when the game has ended to show final score, and victory screen.	/// </summary>
	/// <returns><c>true</c>, if end was turned true from matchcard method, <c>false</c> otherwise.</returns>
	public bool isEnd(){


		return theEndTest;
	}
	/// <summary>
	/// Matchs the cards.
	/// tests values if the card is matching
	/// </summary>
	/// <returns><c>true</c>, if cards was matched, <c>false</c> otherwise.</returns>
	public bool matchCards(){

		bool test = false;
	
		//if cards do not match return false else true;  it should use the index number to the other items.
		if (CardList.Count > 1) {


			if (CardList [0] == CardList [1]) {

				//if the cards matches
					test = true;	
					AudioSource.PlayClipAtPoint(RightSound, transform.position);
					CardList.RemoveAt (0);
					CardList.RemoveAt (0);

				endgame++;
				endgame++;

				score+= 1;


			} else {
				//cards didn't match
					test = false;	
					AudioSource.PlayClipAtPoint(WrongSound, transform.position);
					CardList.RemoveAt (0);
					CardList.RemoveAt (0);

			}

		}


		return test;
	}

	public int getEndGame(){
		return endgame;
	}

	public void reDeal(){

		enableBoardCollision();
		changeDefaultColor();
		flipBoardDown();
		DealShuffledCards();
//		TestDeal ();

	}
	/// <summary>
	/// Gets the card number.  for grabbing specific texture when shuffled.  also assign the shuffled value to the button class.
	/// </summary>
	/// <returns>The card number for testing values.</returns>
	/// <param name="sub">Subscript of the BNum.</param> 
	public int getCardNumber(int sub){
		
		return (int)board [sub];
		
	}
	/// <summary>
	/// Gets the card.
	/// </summary>
	/// <returns>The card.</returns>
	/// <param name="sub">Sub.  the button number.  defined as BNum in each button class.</param>  
	public Texture getCard(int sub){
		
		return cards [sub];
	}
	
	
	/// <summary>
	/// Deals out a test board which is ordered/unshuffled
	/// </summary>
	public void TestDeal(){
		//delete entire method when finished, deals unshuffled, delete testdeal() above;
		board = new List<int>();
		ArrayList temp = new ArrayList ();
		board.Clear ();
		
		for (int i = 0; i < MatchList.Length; i++) {
			temp.Add(MatchList[i]);
			temp.Add(MatchList[i]);
		}
		while (temp.Count !=0) {
			board.Add((int)temp[0]);
			temp.RemoveAt(0);
		}
		
	}

	/// <summary>
	/// Deals the shuffled cards into the game board for each card object.
	/// </summary>
	public void DealShuffledCards(){
		
		board = new List<int>();


		List<int> ShuffledList = new List<int> ();  //this will hold shuffled number positions
		List<int> doubleDeck = new List<int> ();  //this will double each position number and added to a deck the size of board
		List<int> shuffleTexture = new List<int> ();  //hold the temporary texture's position number then shuffled 

		
		
		float testnum = Random.value * 1000; //generates a random
		int random = Time.renderedFrameCount + (int)testnum; //provides a true random
		int denom = 20;  //size of the array from temp
		int sub;  //subscript of which we will temporarily place a random subscript; defined below
		
		
		float tempNum = Random.value * 1000; //generates a random of 1000
		int trueRandom = Time.renderedFrameCount + (int)tempNum; //provides a true random by adding to the framecount when game started with random number
		int denomShuff = 22;  //this should equal size of the array from temp
		int subscript;  //subscript of which we will temporarily place a random subscript; defined below
		
		
		board.Clear ();
		
//		for (int j = 0; j < MatchList.Length; j++) {
//			temp.Add (MatchList [j]);
//		}

		for (int j = 0; j < TextureListSize.Length; j++) {
			shuffleTexture.Add (TextureListSize [j]);
		}

		/*
				 * grab random position
				 * place in new array
				 * delete temp p		osition
				 * subtract denom by 1
				 * repeat until temp is empty
			*/
		while (shuffleTexture.Count > 0){
			subscript = trueRandom % denomShuff;  //sub is defined here with random number between 0 and denom size
			ShuffledList.Add (shuffleTexture[subscript]); //takes random value from from sub and add it in
			shuffleTexture.RemoveAt(subscript);  //removes the number from the temp list so that it does not repeat
			denomShuff--; //decrement to add new card for next step until list is filled
		}
		
		
		
		//this will add two copies of the texture position for the list of ten

		int halfboardSize = 10;  //TODO this value should be equal to half the total board size; only thing that needs to change in this method
		for(int a = 0; a < halfboardSize; a++){
			doubleDeck.Add (ShuffledList[0]);
			doubleDeck.Add (ShuffledList[0]);
			ShuffledList.RemoveAt(0);
		}

		//this will shuffle the temp list into the array list of board
		while (doubleDeck.Count != 0){
			sub = random % denom;  //sub is defined here
			board.Add ((int)doubleDeck[sub]);
			doubleDeck.RemoveAt(sub);
			denom--;
		}

		shuffleTexture.Clear ();
		doubleDeck.Clear ();
		ShuffledList.Clear ();
		

	}

	/// <summary>
	/// Enables the board collision.
	/// </summary>
	public void enableBoardCollision(){


		//row 1
		button1.enableButtonCollider();
		button2.enableButtonCollider();
		button3.enableButtonCollider();
		button4.enableButtonCollider();

		//row 2
		button5.enableButtonCollider();
		button6.enableButtonCollider();
		button7.enableButtonCollider();
		button8.enableButtonCollider();

		//row 3
		button9.enableButtonCollider();
		button10.enableButtonCollider();
		button11.enableButtonCollider();
		button12.enableButtonCollider();

		//row 4
		button13.enableButtonCollider();
		button14.enableButtonCollider();
		button15.enableButtonCollider();
		button16.enableButtonCollider();
		//row 5
		button17.enableButtonCollider();
		button18.enableButtonCollider();
		button19.enableButtonCollider();
		button20.enableButtonCollider();
		
	}

	/// <summary>
	/// Disables the board collision.
	/// </summary>
	
	public void disableBoardCollision(){
		
		//row 1
		button1.disableButtonCollider ();
		button2.disableButtonCollider ();
		button3.disableButtonCollider ();
		button4.disableButtonCollider ();

		//row 2
		button5.disableButtonCollider ();
		button6.disableButtonCollider ();
		button7.disableButtonCollider ();
		button8.disableButtonCollider ();

		//row 3
		button9.disableButtonCollider ();
		button10.disableButtonCollider ();
		button11.disableButtonCollider ();
		button12.disableButtonCollider ();

		//row 4
		button13.disableButtonCollider ();
		button14.disableButtonCollider ();
		button15.disableButtonCollider ();
		button16.disableButtonCollider ();

		//row 5
		button17.disableButtonCollider ();
		button18.disableButtonCollider ();
		button19.disableButtonCollider ();
		button20.disableButtonCollider ();
		
	}

	/// <summary>
	/// Flips the board up.
	/// </summary>
	public void flipBoardUp(){
		
		//row 1
		button1.CardFlipTexture ();
		button2.CardFlipTexture ();
		button3.CardFlipTexture ();
		button4.CardFlipTexture ();

		//row 2
		button5.CardFlipTexture ();
		button6.CardFlipTexture ();
		button7.CardFlipTexture ();
		button8.CardFlipTexture ();

		//row 3
		button9.CardFlipTexture ();
		button10.CardFlipTexture ();
		button11.CardFlipTexture ();
		button12.CardFlipTexture ();

		//row 4
		button13.CardFlipTexture ();
		button14.CardFlipTexture ();
		button15.CardFlipTexture ();
		button16.CardFlipTexture ();

		//row 5
		button17.CardFlipTexture ();
		button18.CardFlipTexture ();
		button19.CardFlipTexture ();
		button20.CardFlipTexture ();
		
	}

	/// <summary>
	/// Flips the board down.
	/// </summary>
	public void flipBoardDown(){
		
		//row 1
		button1.CardBackTexture();
		button2.CardBackTexture();
		button3.CardBackTexture();
		button4.CardBackTexture();

		//row 2
		button5.CardBackTexture();
		button6.CardBackTexture();
		button7.CardBackTexture();
		button8.CardBackTexture();

		//row 3
		button9.CardBackTexture();
		button10.CardBackTexture();
		button11.CardBackTexture();
		button12.CardBackTexture();

		//row 4
		button13.CardBackTexture();
		button14.CardBackTexture();
		button15.CardBackTexture();
		button16.CardBackTexture();

		//row 5
		button17.CardBackTexture();
		button18.CardBackTexture();
		button19.CardBackTexture();
		button20.CardBackTexture();
		
	}
	public void changeDefaultColor(){
		
		//row 1
		button1.defaultColor ();
		button2.defaultColor ();
		button3.defaultColor ();
		button4.defaultColor ();
		
		//row 2
		button5.defaultColor ();
		button6.defaultColor ();
		button7.defaultColor ();
		button8.defaultColor ();
		
		//row 3
		button9.defaultColor ();
		button10.defaultColor ();
		button11.defaultColor ();
		button12.defaultColor ();
		
		//row 4
		button13.defaultColor ();
		button14.defaultColor ();
		button15.defaultColor ();
		button16.defaultColor ();
		
		//row 5
		button17.defaultColor ();
		button18.defaultColor ();
		button19.defaultColor ();
		button20.defaultColor ();
		
	}

}
