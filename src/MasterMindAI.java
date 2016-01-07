/*
 * MasterMind.java
 * 
 * Version 7
 * 
 * Copyright Jordan Brown
 * 
 * Course: CSC 172 FALL 2015
 * 
 * Assignment: PROJECT 1
 * 
 * Last Revised: October 7, 2015
 */

import java.util.ArrayList;

/*
 * MasterMindAI class provides and creates an artificial intelligence for the mastermind game.
 * This class provides method to calculate the total possible combinations given color values and positions, generates
 * those colors and positions, and contains decision methods for the computer to determine what elements to eliminate
 * in the total combinations list to make the best next move. The computer's elimination and guess choices are based
 * on the user's feedback of black and white pegs. 
 */
public class MasterMindAI {
	
	private int positions;	//number of positions for user's secret combination 
	
	private String[] tokenColors;	//token colors user chooses
	
	private int numberOfColors;		//number of token colors that user specifies

	
	private int totalNumOfCombinations;		//total number of combinations of colors and positions
	
	private ArrayList<String> comboList;	//list for all the possible combinations
	
	private ArrayList<String> tokenList;	//list for all the token colors
	
	private String guess;	//represents the computer's current guess
	
	
	/*
	 * Constructor to initialize instance variables 
	 */
	public MasterMindAI( String[] tokenColors, int positions, int numOfColors ){
		
		this.positions = positions;		//set the positions, token colors, and number of colors
		
		this.tokenColors = tokenColors;
		
		numberOfColors = numOfColors;
		
		totalNumOfCombinations = calculateCombinations( numberOfColors, positions ); //calculate the total number of combinations possible
		
		
		tokenList = new ArrayList<String>();	//initialize a token colors array list and fill it with the token colors
		
		for(int i = 0; i < tokenColors.length; i++){
			
			tokenList.add(tokenColors[i]);
			
		}
		
		comboList = combinationsGenerator(tokenList, tokenColors, positions);	//initialize the combinations list
		
		guess = "";	//initialize computer guess to being blank
		
	}//end of constructor
	
	/*
	 * Converts a string to an array, returns that array
	 */
	public String[] toStringArray( String word ){
		
		String[] wordArray = word.split("");//separate the string into an array at each new letter space
		
		//In this case, the split operation leaves an empty space at the beginning of the array, the following steps delete this
		
		String[] letterArray = new String[wordArray.length-1]; //Initialize a string array with one less size of our word array (to disregard empty space)
		
		for(int i = 0; i < letterArray.length; i++){ //for each space in the letter array
			
			letterArray[i] = wordArray[ i + 1 ];//Fill the letter array with every element in the word array except for the first one (since this is an empty blank space)
			
		}
		
		return letterArray; //return an array only containing letters (no empty spaces)
	}//end of method toStringArray
	
	
	
	/*
	 * Determines which elements to eliminate in a String array that corresponds to the size of the combinations 
	 * ArrayList. Marks each index that must be eliminated in the eliminations string array with "elim". 
	 * If the index does not need to be eliminated, the element is left as null.
	 * returns the elimination array
	 */
	public String[] eliminateElements( String typeOfElimination ){
		
		String[] eliminationElems = new String[comboList.size()];	//initialize eliminations array
		
		String[] letters;											//declare an array that will hold the letters of each combination group in the combinations list
		
		int count = 0;												//number counts how many times the computer's guess contains a letter
		
		
		for(int i = 0; i < comboList.size(); i++){		//For each element in the combinations list
			
			for(String elem: comboList){
				
				letters = toStringArray(elem);			//Convert that element to an array, so now it will be each single, color letter will have its own index 
				
				for(int j = 0; j < letters.length; j++){	//For each element in the letters array
					
				
					
					if( guess.contains(letters[j]) ){		//Checks if the computers guess contains the letter
									
						
						count++;
						
					}
				}
				
				
				switch( typeOfElimination ){			//Based on the type of elimination needed, determine when to mark an index as "elim"
				
					case "elimIfNotContainAll":							//Eliminate if guess does not contain all the letters of an element, count will not reach position number
						
						if( count != positions ){
							eliminationElems[comboList.indexOf(elem)] = elem;
						}
						break;
						
					case "elimIfEqual":									//Eliminate if guess is exactly equal to the letters of the element, count will be equal to the number of positions
						
						if(count == positions ){
							eliminationElems[comboList.indexOf(elem)] = elem;
						}
						break;
						
					case "elimIfContainsNone":						//Eliminate if guess contains none of the letters of the element, count will be 0
						
						if(count == 0 ){
							eliminationElems[comboList.indexOf(elem)] = elem;
							
						}
						break;
						
					case "elimIfNotContainsTwo":					//Eliminate if guess does not contain exactly 2 letters of the element, count will be less than and not equal to 2
						
						if( count < 2 ){
							eliminationElems[comboList.indexOf(elem)] = elem;
						}
						break;
						
					case "elimIfNotContainsThree":					//Eliminate if guess does not contain exactly 3 letters of the element, count will be less than and not equal to 3
						
						if( count < 3 ){
							eliminationElems[comboList.indexOf(elem)] = elem;
							
						}
						break;
						
					case "elimIfContainsAny":						//Eliminate if guess contains any of the letters pf the element, count will be at least 1 or greater
						if(count >= 1 ){
							eliminationElems[comboList.indexOf(elem)] = elem;
						}
				}
				

				
				count = 0; 		//restart the count to 0
				
			
			}
			
		}
		
		return eliminationElems;			//elimination array now contains all the "elim" elements at the corresponding index of combinations list to be deleted
	}//end of eliminateElements method
	
		
	/*
	 * Removes all the combinations from the combinations list deemed by the computer as unneeded for winning
	 * Takes in a string array that contains null or a string that needs to be deleted.
	 * 
	 */
	public void eliminationPossibility(String[] eliminationElems){
		
		for(String elem: eliminationElems){ //for each element in the eliminations array
			
			if( elem != null ){	//if the element is not null, remove it from the combinations list

				comboList.remove(elem);
				
			}
			
			else{	
					
				//null, do nothing
			}
		}
		
			
	}//end of eliminationPossibility method
	
	
	
	/*
	 * Driver method that takes in the user's number of black and white pegs computer earned and the computer's previous guess.
	 * Depending on the amount of positions and pegs, determines what type of elimination needs to occur and calls the 
	 * necessary method to eliminate the unneeded combinations 
	 */
	public void response( int whitePegs, int blackPegs, String[] guessArr ){
		
		guess = "";
		
		for(int i = 1; i < guessArr.length; i++ ){				//convert the guess into a string and store in guess instance variable
			
			guess = guess + guessArr[i];
		}

		
		comboList.remove(guess); 	//remove guess from combinations list
		
		
		String[] eliminationElems = new String[comboList.size()];		//Initialize an array that will hold at each index either "elim" for the element to be deleted or null for the element to stay
		
		if( blackPegs == 0 && whitePegs == 0 ){	//If computer scores both a 0 in white and black pegs
			
			eliminationElems = eliminateElements("elimIfContainsAny");	//Eliminate all combinations that contain any of the letters in guess
			
		}
		
		else{	//The computer earned at least one black or white peg
			
			if(positions == 2 || (positions == 4 && numberOfColors == 2) || (positions == 3 && numberOfColors == 2) || (positions == 4 && numberOfColors == 3)){			//If user chose 2 positions
				
				
				if(numberOfColors == 3 && positions == 4){
					
					if(whitePegs == 4){
						
						eliminationElems = eliminateElements("elimIfEqual");
					}
					
				
				}
				
				else{
					if( blackPegs == 1 || whitePegs == 1 ){			//If computer scores at least 1 white or black peg
						
						
						eliminationElems = eliminateElements("elimIfContainsNone");	//Eliminate all combinations that don't contain at least one letter in guess
					}
					
					else if( whitePegs == 2 ){		//If computer earned 2 white pegs, eliminate all combinations that don't contain all the letters in guess
						
						eliminationElems = eliminateElements("elimIfNotContainAll");
					}
				}
				
			}//end of 2-positions decision statements
			
			else if (positions == 3  ){	//User chose 3-positions
				
				
				//If computer scores exactly 3 (white pegs, black and white or white and black combined, eliminate all combinations that do not contain all of the letters in guess
				if( whitePegs == 3 || ( blackPegs == 1 && whitePegs == 2) || (blackPegs == 2 && whitePegs == 1) ){
					
					eliminationElems = eliminateElements("elimIfNotContainAll");
				}
				
				//If computer scores exactly 2 (white pegs, black pegs, or black an white pegs combined), eliminate all combinations that do not contain at least two letters in guess
				else if( whitePegs == 2 || blackPegs == 2 || (whitePegs == 1 && blackPegs == 1) ){
					
					eliminationElems = eliminateElements("elimIfNotContainsTwo");
				}
				
				//If computer scores exactly 1, either white or black pegs, eliminate all combinations that don't contain any of the guess letters
				else if( whitePegs == 1 || blackPegs == 1){
					
					eliminationElems = eliminateElements("elimIfContainsNone");
				}
				
			}//end of 3-position decision statements
			
			else{	//user chose 4-positions
				
				//If computer scores exactly 4 white pegs, eliminate all combinations that do not contain all of the letters in guess
				if(whitePegs == 4 ){
					
					eliminationElems = eliminateElements("elimIfNotContainAll");
				}
				
				//If computer scores exactly 3 (black, white, or white and black, or black and white combined), eliminate all combinations that do not contain at least 3 of the letters in guess
				else if( blackPegs == 3 || whitePegs == 3 || (whitePegs == 2 && blackPegs == 1) || (blackPegs == 2 && whitePegs == 1)){
					
					eliminationElems = eliminateElements("elimIfNotContainsThree");
				
				}
				
				//If computer scores exactly 2 (black, white, or black and white combined), eliminate all combinations that do not contain at least two letters in guess
				else if( blackPegs == 2 || whitePegs == 2 || (whitePegs == 1 && blackPegs == 1) ){
					
					eliminationElems = eliminateElements("elimIfNotContainsTwo");
				}
				
				//If computer scores exactly 1 (white or black pegs), eliminate all combinations that do not contain at least 1 letter in guess
				else if( blackPegs == 1 || whitePegs == 1 ){
					
					eliminationElems = eliminateElements("elimIfContainsNone");
				}
				
			}//end of 4-position decision statements
		}
		
		
		eliminationPossibility(eliminationElems);	//Eliminate all the unnecessary combinations from combinations list

		
	}//end of response method
	
	
	/*
	 * Driver method that resets the game; re-initializes the core aspects of the game, number of positions,
	 * number of colors, token colors, recalculates combinations list, and makes computer's guess blank
	 */
	public void newGame(int positions, int numOfColors, String[] tokenColors){
		
		//Reset the game
		comboList.clear();	//clear the combinations list
		
		//Reset the positions, token colors, and number of colors
		
		this.positions = positions;
		
		this.tokenColors = tokenColors;
		
		numberOfColors = numOfColors; 
		
		totalNumOfCombinations = calculateCombinations( numberOfColors, positions );	//recalculate the total number of combinations possible
		
		tokenList = new ArrayList<String>();	//fill the token colors list
		
		for(int i = 0; i < tokenColors.length; i++){
			
			tokenList.add(tokenColors[i]);
			
		}
		
		comboList = combinationsGenerator(tokenList, tokenColors, positions); //recalculate combinations list
		
		guess = "";	//guess is now blank
		
	}//end of method newGame
	
	/*
	 * Method that determines the first move for the computer to make and returns that guess as a string array
	 * 
	 * According to Donald Knuth's 5 Guess method, the best first guess to make is '1122'.
	 * So, for each position I chose what was close to that for ex:
	 * 
	 * 2-positions: PP
	 * 3-positions: RRG
	 * 4-positions: RRGG
	 */
	public String[] firstMove(){
		
		String guess = "";	//set guess to empty string
		
		if( positions == 3){
			guess = comboList.get(2);
			
		}
		
		else if( positions == 4 ){
			guess = comboList.get(5);
		}
		
		else{	
			guess = comboList.get(0);	
		}
		
		
		String[] array = guess.split("");	//convert guess into string array
		return array;
	}//end of method firstMove
	
	/*
	 * Driver method that returns a string array representing the next guess of the computer. If there are no more
	 * combinations left, it returns null
	 */
	public String[] newMove(){
		//return the next guess
		
		if( comboList.isEmpty() == false ){	//if combinations list is not empty
			
			guess = comboList.get(0);	//return the next guess (first element in combinations list) of the combinations array
			
			String[] newGuess = guess.split("");

			return newGuess;
		}
		
		else{
			
			return null;
		}
	}//end of method newMove
		
	/*
	 * Calculates the total number of combinations possible, k^n, with k being the color values and n the positions
	 */
	public int calculateCombinations( int k , int n ){
		
		if( n == 0 ){			//Base Case
			
			return 1;
		}
		
		else if( n == 1 ){
			
			return k;
		}
		
		else{
			
			return k * calculateCombinations( k , n - 1 );		//Recursive step
		}
		
	}//end of calculateCombinations method
	
	/*
	 * Generates all the combinations of colors given a number of positions. Concatenates element in array to the user's
	 * token colors until reaching the desired position. Returns all the combinations inside an ArrayList
	 */
	public ArrayList<String> combinationsGenerator( ArrayList<String> list, String[] tokenColors, int position ){
		
		if( position == 1 ){	//Base Case: If positions possible is only 1, return the list
			
			return list;
		}
		
		else{		
			
			ArrayList<String> holder = new ArrayList<String>();	//initialize a new arraylist that holds the concatenated elements
			
			for( String elem: list ){	//for each element our list that holds our combinations
				
				for( String letter: tokenColors ){	//for each letter in the user's token colors
					
					holder.add( elem + letter );	//add the letter and element in the list to the holder 
						
				}
				
			}
			
			list = holder;	//Let the list reference all the elements in holder
			
			return combinationsGenerator( list, tokenColors, position - 1 );	//Recursive Step
			
		}
			
	}//end of combinationsGenerator
	
	/* Accessor and Getter methods for the instance variables */
	public void printCombo(){
		
		for(String elem: comboList){
			
			System.out.print(elem + " , ");
		}
	}

	public int getPositions() {
		return positions;
	}

	public void setPositions(int positions) {
		this.positions = positions;
	}

	public String[] getTokenColors() {
		return tokenColors;
	}

	public void setTokenColors(String[] tokenColors) {
		this.tokenColors = tokenColors;
	}

	public int getNumberOfColors() {
		return numberOfColors;
	}

	public void setNumberOfColors(int numberOfColors) {
		this.numberOfColors = numberOfColors;
	}


	public int getTotalNumOfCombinations() {
		return totalNumOfCombinations;
	}

	public void setTotalNumOfCombinations(int totalNumOfCombinations) {
		this.totalNumOfCombinations = totalNumOfCombinations;
	}

}//end of class MasterMindAI

