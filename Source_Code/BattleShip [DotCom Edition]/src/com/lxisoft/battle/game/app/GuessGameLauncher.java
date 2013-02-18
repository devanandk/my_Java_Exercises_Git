package com.lxisoft.battle.game.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GuessGameLauncher {

	static GameEngine game;
	static BufferedReader input;
	static int boardSize;
	static int playerCount;
	static int currPlayer;
	static int startWith;

	public static void main(String[] args) throws IOException {

		input = new BufferedReader(new InputStreamReader(System.in));
		runGame();
		

	}
	
	public static void runGame() throws IOException{
		
		initializeGame();
		
		while(isGameNotOver()){
			try {
			game.printBoard(boardSize);
			System.out.println("\nEnter your guesses \n" +
					"------------------------------------------");
			if(startWith >= playerCount)
				startWith = 0;
			for(currPlayer = startWith; currPlayer < playerCount; currPlayer++)
			{
//				System.out.println(currPlayer + " " + skipFlag);
				if(game.isAllCellSelected()) break;
				System.out.print(String.format(" %-25s : ",game.Players[currPlayer].getName()));
				
					String guess = input.readLine();
					game.readPlayerInput(currPlayer, guess);
			}
			System.out.println();
			startWith = 0;
			game.updateScore();
		}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("\nError occurred... \nInvalid selection... All players must select an unselected value from the board\n" +
						"You have lost this chance...!!!\n");
				
			}
			catch(NumberFormatException e)
			{
				System.out.println("\nError occurred... All players must make a selection to play the game....\n" +
						"You have lost this chance...!!!\n");
				
			}
			catch(StringIndexOutOfBoundsException e){
				System.out.println("\nError occurred... All players must make a selection to play the game....\n" +
						"You have lost this chance...!!!\n");
			}
			catch (Exception e) {
			System.out.println("An error has occurred... Please restart the game to try again...!!!");
			//e.printStackTrace();
			GameEngine.waitAndExit();
		}
		}
		
		game.showScores();
		GameEngine.waitAndExit();
	}

	private static boolean isGameNotOver() {

		if(game.isAllGuessesOver())
		{
			return false;
		}
		else if(game.isAllCellSelected())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private static void initializeGame() {
		
		System.out.println("\n\nTHE Dot.Com GUESSING GAME");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("This is a simple guessing game. There are a few web addresses hidden randomly inside " +
				"the board. The players will each take turns to guess where they are hidden. The player " +
				"who guesses the most options correctly will win the game.\n");
		
		try {
			System.out.print("\nEnter the board size [S / M / L] : ");
			String sBoardSize = input.readLine();
			
			if(sBoardSize.toUpperCase().equals("S")) boardSize = 5;
			
			else if(sBoardSize.toUpperCase().equals("M")) boardSize = 10;
			
			else if(sBoardSize.toUpperCase().equals("L")) boardSize = 15;
			
			else throw new Exception("Invalid Board Size...!!!");
			
			System.out.print("\nEnter the number of players : ");
			playerCount = Integer.parseInt(input.readLine());
			
			//if(boardSize <= 6) throw new ArrayIndexOutOfBoundsException();
			
			if(playerCount <= 0) throw new ArrayIndexOutOfBoundsException();
			
			if(playerCount > (boardSize * boardSize)) throw new Exception("To many players for the specified board size...!!!");
			
			game = new GameEngine(boardSize, playerCount);
		
			game.fillBoard(boardSize);
			System.out.print("\nDo you wish to set names for the players?.. [Y/N]  : ");
			if(input.readLine().toUpperCase().equals("Y")){
				game.setCustomPlayerNames();
			}
		} catch (NumberFormatException e) {
			System.out.println("\n\nAn error has occurred...\n" +
					"Enter a valid number for the board size and number of players.\n" +
					"Please restart the game to try again...!!!");
			GameEngine.waitAndExit();
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("\n\nAn error has occurred... \n" +
					"There was an error in the input.\n" +
					"Please restart the game to try again...!!!");
			GameEngine.waitAndExit();
			//e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("\n\nAn error has occurred...\n" +
					"The number of Players cannot be less than one.\n" +
					"Please restart the game to try again...!!!");
			GameEngine.waitAndExit();
		}
		catch(Exception e){
			System.out.println("\n\n"+ e.getMessage());
			System.out.println("\nAn error has occurred... Please restart the game to try again...!!!");
			GameEngine.waitAndExit();
		}
		
	}
	
}
