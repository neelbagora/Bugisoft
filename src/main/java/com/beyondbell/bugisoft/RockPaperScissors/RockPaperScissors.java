package com.beyondbell.bugisoft.RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {


	private static enum Signs {
		ROCK,
		PAPER,
		STANDBY,
		SCISSORS
	}

	private static enum gameState {
		PLAYING,
		GAMEOVER
	}

	private static Scanner takeInput = new Scanner(System.in);
	private static Integer games;


	public static void main(String... args) {

		//enum for representing rps state
		gameState rps = gameState.PLAYING;

		//computer wins
		int computerWins = 0;

		//player wins
		int playerWins = 0;


		//variable for user name

		String check;

		//Start Prompt
		System.out.println("Rock Paper Scissors, by Neel Bagora");
		System.out.println("\nEnter gamename: ");
		String userName = takeInput.nextLine();

		int number = 1;


		for(int thing = 0; thing < number; thing++) {
			System.out.println("Is " + userName + " correct? (yes / no)" );
			check = takeInput.nextLine();
			char yesNo = check.toLowerCase().charAt(0);
			if(yesNo == 'y') {
				System.out.println("Welcome " + userName + "!");
				break;
			} else if(yesNo == 'n') {
				System.out.println("Please re-enter name: ");
				userName = takeInput.nextLine();
				number += 1;
			} else {
				System.out.println("Invalid answer, please re-enter answer:");
				number += 1;
			}
		}

		number = 1;


		System.out.print("\nBest out of how many: ");


		//Set amount of games
		String numberOfGames = takeInput.next();
		try {
			games = Integer.parseInt(numberOfGames);
		} catch(NumberFormatException x) {
			System.out.println("That is not a number!");
			games = 1;
		} finally {
			if(games != 1) {
				System.out.println("Game size set to " + games + " games");
			} else if(games > 0){
				System.out.println("Game size set to " + games + " game");
			} else {
				games = 1;
				System.out.println("Game size set to " + games + " game");
			}
		}



		//wins needed
		int winsNeeded = (games / 2) + 1;

		int count = 1;


		for(int i = 0; i < count; i++) {
			if(games == null) {
				count += 1;
				System.out.print(" Input invalid, please re-enter a valid number of games. Type quit to quit: ");
				numberOfGames = takeInput.next();
				if(numberOfGames.toLowerCase().substring(0,1).equals("q")) {
					System.out.println("Game Terminated");
					System.exit(0);
				}
				games = Integer.parseInt(numberOfGames);
			} else if(games == 0 || games < 0){
				count += 1;
				System.out.print("Input invalid, please re-enter a valid number of games. Type quit to quit: ");
				numberOfGames = takeInput.next();
				if(numberOfGames.toLowerCase().substring(0,1).equals("q")) {
					System.out.println("Game Terminated");
					System.exit(0);
				}
				games = Integer.parseInt(numberOfGames);
			} else {
				//System.out.println("\n" + userName + " versus Computer");
				rps = gameState.PLAYING;
			}
		}




		//Represent round number
		int round = 1;

		//enums for signs
		Signs user = Signs.STANDBY;
		Signs computer = Signs.STANDBY;

		Scanner gameInput = new Scanner(System.in);

		boolean repeat = true;
		while(rps != gameState.GAMEOVER) {
			System.out.println("\nRound " + round + ":");
			round += 1;
			System.out.println(" Your turn: Rock, Paper, Scissors, TO QUIT: quit");

			boolean validInput;
			System.out.print(" ");
			String sign = gameInput.next();
			char value = sign.toLowerCase().charAt(0);
			number = 1;

			for(int values = 0; values < number; values++) {
				if (value == 'r') {
					System.out.println("You picked Rock");
					validInput = true;
					user = Signs.ROCK;
				} else if (value == 'p') {
					System.out.println("You picked Paper");
					validInput = true;
					user = Signs.PAPER;
				} else if (value == 's') {
					System.out.println("You picked Scissors");
					validInput = true;
					user = Signs.SCISSORS;

				} else if (value == 'q') {
					System.out.println();
					System.out.println("Game cancelled");
					rps = gameState.GAMEOVER;
					break;
				} else {
					System.out.println("That's not a sign, please re-enter a correct sign: (Rock, Paper, Scissors)");
					sign = gameInput.next();
					value = sign.toLowerCase().charAt(0);
					number += 1;
				}
			}

			//resetting universal for-loop limit
			number = 0;


			//Computer sign generator
			Random computerSign = new Random();
			int randomInt = computerSign.nextInt(3);


			if (randomInt == 0) {
				computer = Signs.ROCK;
			} else if (randomInt == 1) {
				computer = Signs.PAPER;
			} else if (randomInt == 2) {
				computer = Signs.SCISSORS;
			}

			System.out.println();
			System.out.println("I picked: " + computer.toString().substring(0,1) + computer.toString().substring(1).toLowerCase());

			if (user == Signs.SCISSORS && computer == Signs.PAPER) { //user win conditions
				playerWins += 1;
				System.out.println();
				System.out.println("You won!");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				user = Signs.STANDBY;
			} else if (user == Signs.PAPER && computer == Signs.ROCK) {
				playerWins += 1;
				System.out.println();
				System.out.println("You won!");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				user = Signs.STANDBY;
			} else if (user == Signs.ROCK && computer == Signs.SCISSORS) {
				playerWins += 1;
				System.out.println();
				System.out.println("You won!");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				user = Signs.STANDBY;
			} else if (computer == Signs.SCISSORS && user == Signs.PAPER) { //computer win conditions
				computerWins += 1;
				System.out.println();
				System.out.println("Computer won");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				computer = Signs.STANDBY;
			} else if (computer == Signs.PAPER && user == Signs.ROCK) {
				computerWins += 1;
				System.out.println();
				System.out.println("Computer won");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				computer = Signs.STANDBY;
			} else if (computer == Signs.ROCK && user == Signs.SCISSORS) {
				computerWins += 1;
				System.out.println("Computer won");
				System.out.println(" You: " + playerWins + "\n Computer: " + computerWins);
				computer = Signs.STANDBY;
			} else if (computer == user) {
				System.out.println();
				System.out.println("Tie!");
				System.out.println("\n You: " + playerWins + "\n Computer: " + computerWins);
				computer = Signs.STANDBY;
				user = Signs.STANDBY;
			}


			if (playerWins == winsNeeded) {
				System.out.println();
				int winCount = Math.abs(computerWins - playerWins);
				String words;
				if(winCount != 1) {
					words = " more wins!";
				} else {
					words = " more win!";
				}
				System.out.println("\nYou won the game with " + Math.abs(computerWins - playerWins) + words);
				System.out.println("\nFinal Score: \n You: " + playerWins + "\n Computer: " + computerWins);
				rps = gameState.GAMEOVER;

			} else if (computerWins == winsNeeded) {
				int winCount = Math.abs(computerWins - playerWins);
				String words;
				if(winCount != 1) {
					words = " more wins.";
				} else {
					words = " more win.";
				}
				System.out.println();
				System.out.println("\nComputer won the game with " + Math.abs(computerWins - playerWins) + words + ".");
				rps = gameState.GAMEOVER;

			} else if(round == games + 1){
				int winCount = Math.abs(computerWins - playerWins);
				String words;
				if(winCount != 1) {
					words = " more wins";
				} else {
					words = " more win";
				}
				if(playerWins > computerWins) {
					System.out.println("\nYou won the game with " + Math.abs(computerWins - playerWins) + words + "!");
					System.out.println("\n You: " + playerWins + "\n Computer: " + computerWins);
					rps = gameState.GAMEOVER;
					System.exit(0);
				} else if(computerWins > playerWins) {
					System.out.println("\nComputer won the game with " + Math.abs(computerWins - playerWins) + words + ".");
					System.out.println("\nFinal Score: \n You: " + playerWins + "\n Computer: " + computerWins);
					rps = gameState.GAMEOVER;
					System.exit(0);
				} else if(playerWins == computerWins && repeat) {
					System.out.print("\nTie game, would you like to keep playing till someone wins? (yes/no)");
					check = takeInput.next();
					repeat = false;

					for(int ask = 0; ask < number; ask++) {
						char yesNo = check.toLowerCase().charAt(0);
						if(yesNo == 'y') {
							System.out.println("You picked yes!");
							winsNeeded = playerWins + 1;
							games += 1;
						} else if(yesNo == 'n') {
							System.out.println("You picked no");
							System.exit(0);
						} else if(check == null) {
							System.out.println("Invalid answer, please re-enter answer: (yes/no): ");
							number += 1;
						} else {
							System.out.println("Invalid answer, please re-enter answer: (yes/no): ");
							number += 1;
						}

					}

					rps = gameState.PLAYING;
				}
			}

		}
	}
}
