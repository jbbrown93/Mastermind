/* MastermindGame.java
 * 
 * Version 7
 * 
 * Copyright Jordan Brown
 * 
 * Course: CSC 172 FALL 2015
 * 
 * Assignment: PROJECT 1
 * 
 * 
 * Last Revised: October 7, 2015
 */

/*
 * MastermindGame class provides helper methods and a main method to run a game of Mastermind.
 * 
 */
public class MastermindGame {

	/*
	 * Method determines whether another game will occur.
	 * Returns true if the string passed equals (ignoring case) yes or starts with a 'y'. Otherwise returns false.
	 */
	public static boolean playAgain( String answer ){
		
		if( answer.equalsIgnoreCase("YES") || answer.startsWith("y") || answer.startsWith("Y")){
			
			return true;
		}
		
		return false;
	}//end of method playAgain
	

	
	//start of main method 
	public static void main(String[] args) {
		
		/* Declare and initialize variables */
		
		
		ConsuleGUI consule = new ConsuleGUI();		//initialize ConsuleGUI classes for displaying messages to user
		
		MasterMindAI computer;						//declare opponent player, the computer
		
		String[] guess;								//computer's color guess 
		
		String answer = "";							//user's answer from consule prompts
		
		String userColors;							//user's chosen colors
		
		int positions;								//user's chosen positions
		
		String[] tokenColors;						//array of user's chosen colors
		
		int numOfColors;							//number of colors user specifies
		
		int whitePegs;								//number of white pegs the user grants the computer
		
		int blackPegs;								//number of black pegs the user grants the computer
		
		
		
		/* Begin playing one game of Mastermind */
		
		
		
		consule.greetMessage();						//greeting message for beginning game play
		
		consule.printIntroRules();					//inform user of game rules
		
		boolean playGame = true;					//determines whether to play again
		
		boolean gameContinues = true;				//determines whether to continue game until a win
		
		consule.pickSecretCodeMessage();			//Inform user to that they must pick and write down a secret combination
		
		consule.printMaxColors();					//inform user of maximum amount of color values possible
		
		answer = consule.promptForColorNumber();	//store user's chosen amount of colors
		
		numOfColors = Integer.parseInt(answer);
		
		userColors = consule.promptForColorValues();	//store user's chosen color values
		
		tokenColors = userColors.split(",");
		
		consule.printMaxPositions();				//inform user of maximum positions possible
		
		answer = consule.promptForPositions();
		
		positions = Integer.parseInt(answer);
		
		computer = new MasterMindAI(tokenColors, positions, numOfColors);	//Initialize the computer given the user's choices of colors, number of positions and colors
		
		
		
		/* Begin game play with user and computer */
		
		
		
		do {
			
			
			
			
			guess = computer.firstMove();						//computer makes their first guess and print to user
			
			consule.computerGuessMessage();
			
			for(int i = 0; i < guess.length; i++){
				
				System.out.print(guess[i] + " ");
			}
		
			whitePegs = consule.whitePegsMessage(); 			//user gives feedback on how many white or black pegs the computer earned
			
			blackPegs = consule.blackPegsMessage();
			
			
			while( gameContinues == true ){					//continue until a winner,
				
				if( blackPegs == positions ){				//if all black pegs in each position
					
					consule.computerWinMessage();			//computer wins and end loop
					
					for(int i = 0; i < guess.length; i++){
						
						System.out.print(" " + guess[i] + " ");
					}
					gameContinues = false;
				}
				
				else{														//computer's guess was incorrect
					
					computer.response(whitePegs, blackPegs, guess);			//send the user's input for white and black pegs to the computer, so the computer can determine what move to make next
					
					guess = computer.newMove();								//computer makes a new guess
					
					consule.computerGuessMessage();							//inform user of computer's new guess
					
					System.out.println();
					for(int i = 0; i < guess.length; i++){
						
						System.out.print(guess[i] + " ");
					}
					System.out.println();
					whitePegs = consule.whitePegsMessage();					//user is again prompted to give feedback on white or black pegs the computer has earned
					
					blackPegs = consule.blackPegsMessage();
					
				}
				
			}//end of game continues while-loop
				
			
			
			/* Round over, determine whether to play again */
			
			
			
			answer = consule.playAgainMessage();							//prompt user to enter whether to play again or quit
			
			playGame = playAgain( answer );
			
			
			if( playGame == true ){											//user has chosen to play again
				
				consule.startNewGameMessage();								//message to user of starting a new game
				
				consule.pickSecretCodeMessage();							//Inform user to that they must pick and write down a secret combination
				
				consule.printMaxColors();									//inform user of maximum color values
				
				answer = consule.promptForColorNumber();					//store user's chosen amount of colors
				
				numOfColors = Integer.parseInt(answer);
				
				userColors = consule.promptForColorValues();				//store user's inputed token colors
				
				tokenColors = userColors.split(",");
				
				consule.printMaxPositions();								//inform user of max positions possible
				
				answer = consule.promptForPositions();						//store user's inputed positions
				
				positions = Integer.parseInt(answer);				
				
				computer.newGame(positions, numOfColors, tokenColors);		//restart the game over
				
				gameContinues = true;										//keep playing game
			}
			
			
			
			
		} while( playGame == true ); 			//repeat the game play until user specifies not to play again
		
		consule.goodByeMessage(); 				//end of game message
		

	}//end of main method

}//end of class MastermindGame

