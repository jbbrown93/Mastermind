/*
 * ConsuleGUI.java
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

import java.util.Scanner;			


/*
 * ConsuleGUI class provides methods that display messages to user and returns the response of the user to various prompts.
 * Handles the interaction between the user.
 */
public class ConsuleGUI {
	
	/* Declare instance variables */
	
	private Scanner input;			//object to handle input from user
	
	private String userResponse;	//user's response to various prompts
	
	private int number;				//user's numerical input from various prompts
	
	//constructor, initialize instance variables
	public ConsuleGUI(){
		
		input = new Scanner(System.in);
		
		userResponse = "";					
		
		number = 0;
	}//end of constructor
	
	/*
	 * Method prompts user to start thinking of and writing down their secret color combination
	 */
	public void pickSecretCodeMessage(){
		System.out.println("Let's play! First, you'll need to pick a secret color combination with a number of positions. After you do, remember to write it down!");
		
	}
	
	/*
	 * Method prints out a greeting message
	 */
	public void greetMessage(){
		
		System.out.println("Welcome to MasterMind!");
		
	}
	
	/*
	 * Method prints out message informing the user a new game has started
	 */
	public void startNewGameMessage(){
		System.out.println("Now starting a new game!");
	}
	
	
	
	/*
	 * Prints the introduction message, rules, and purpose of the game 
	 */
	public void printIntroRules(){
		
		System.out.println("Mastermind is a code-breaking guessing game, where you will be the codemaker and the computer is the codebreaker. \nFirst, select a combination of colors that has 1 to 4 positions from 4 possible colors.");
		System.out.println("Your combination is kept secret. \nYour opposing player, the computer, will guess a combination. \nYou will then enter in how many colors are the correct colors "
				+ " and in the correct location (black tokens) \nand also how many colors are of the correct color but in the wrong location (white token)\n\n");
		
	}
	

	
	/*
	 * prompts to enter the color values they would like to have the computer to choose from
	 */
	public String promptForColorValues(){
		
		System.out.println("Please enter the color values (single letters) you would like the computer to choose from. Separate by commas. \nExample: If you wanted pink, green and blue you need to type in: P, G, B\nUse single letters only to represent colors.");
		userResponse = input.next();
		
		return userResponse;
	}
	
	/*
	 * prompts user to enter the amount of colors they have for their secret combination
	 */
	public String promptForColorNumber(){
		System.out.print("Enter how many colors you will have: ");
		userResponse = input.next();
		
		return userResponse;
	}
	
	/*
	 * informs user of maximum amount of colors possible to pick from 
	 */
	public void printMaxColors(){
		System.out.println("The maximum amount of colors you may choose are 4.");
	}
	
	/*
	 * informs user of maximum amount of possible positions to choose from 
	 */
	public void printMaxPositions(){
		
		System.out.println("The possible amount of positions you can choose are from 2 to 4. With 4 being the maximum amount of positions.");
	}
	/*
	 * prompts user to enter a number of desired positions
	 */
	public String promptForPositions(){
		
		System.out.print("Please enter the number of positions you would like: ");
		userResponse = input.next();
		
		return userResponse;
	}
	
	/*
	 * Prints out the user's chosen colors, number of colors, and positions for the game
	 */
	public void printChosenColorsAndPositions( String[] tokencolors, int positions, String secretCode ){
		
		System.out.println("The token colors you have chosen are: " + tokencolors + "\nThe positions you have chosen are: " + positions 
				+ "\nYou're secret code is: " + secretCode);
				
	}
	
	/*
	 * Prompts user to enter yes or no if they'd like to play again 
	 */
	public String playAgainMessage(){
		
		System.out.println("\nWould you like to play again? Enter yes or no: ");
		userResponse = input.next();
		
		return userResponse;
	}
	
	/*
	 * Prints out a message that the computer has made a guess
	 */
	public void computerGuessMessage(){
		System.out.println("The computer has guessed the following colors:");
	}
	
	/*
	 * Prompts user to enter how many black pegs computer has earned
	 */
	public int blackPegsMessage(){
		System.out.println("Enter how many black pegs (correct color and location) the computer earned: ");
		userResponse = input.next();
		
		number = Integer.parseInt(userResponse);
		return number;
	}
	
	/*
	 * Prompts user to enter how many white pegs computer has earned
	 */
	public int whitePegsMessage(){
		
		System.out.println("Enter how many white pegs (correct color but wrong location) the computer earned: ");
		userResponse = input.next();
		
		number = Integer.parseInt(userResponse);
		return number;
	}
	
	/*
	 * Prints out the computer has won the game
	 */
	public void computerWinMessage(){
		System.out.println("The computer wins the game! Your secret combination was: ");
	}
	
	/*
	 * Prints out end of game message
	 */
	public void goodByeMessage(){
		
		System.out.println("Thanks for playing! Goodbye!");
	}

}//end of class ConsuleGUI

