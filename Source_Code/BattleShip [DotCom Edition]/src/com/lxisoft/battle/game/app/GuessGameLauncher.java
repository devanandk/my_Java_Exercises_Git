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
			
			if(startWith >= playerCount)
				startWith = 0;
			for(currPlayer = startWith; currPlayer < playerCount; currPlayer++)
			{
				if(game.isAllCellSelected()) break;
				
				if(game.isAllGuessesOver()) break;
				
				game.printBoard(boardSize);
				
				System.out.println("\n Enter your guesses \n" +
						"------------------------------------------");
				System.out.print(String.format(" %-25s : ",game.Players[currPlayer].getName()));
				
				String guess = input.readLine();
				
				if(game.readPlayerInput(currPlayer, guess)){
					currPlayer--;
				}
			}
			System.out.println();
			startWith = 0;
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
		game.printBoard(boardSize);
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
		
		System.out.println("\n\nTHE Dot.Com GUESSING GAME [Battleship Edition]");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Hello Agent X.... \nOur selection of targets for you today are " +
				"\"Dot.Com ships\" hidden randomly on the board. Your mission, should you wish to choose it, is to " +
				"locate the hidden ships that pose a threat to the very existance of the free web as we know it " +
				"and in the process put an end to their evil reign of terror. This game will begin now.... " +
				"\n\nGood luck Agents....!!!\n");
		
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
