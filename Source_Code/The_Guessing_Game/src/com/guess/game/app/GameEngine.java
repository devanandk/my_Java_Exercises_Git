package com.guess.game.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.guess.game.elements.Dice;
import com.guess.game.elements.DotCom;
import com.guess.game.elements.Player;

public class GameEngine {
	
	DotCom[] Board;
	Player[] Players;
	Dice dice;
	int sizeOfBoard;
	int noOfPlayers;
	BufferedReader input;
	
	String[] boardElements = {"Gmail.com", "Hotmail.com", "Yahoo.com", "Rediffmail.com", 
							"Engadget.com", "in.com", "flipkart.com", "Alagesia.org", "piratebay.org",
							"Wikipedia.org"};
	
	public GameEngine(int boardSize, int playerCount)
	{
		sizeOfBoard = boardSize * boardSize;
		noOfPlayers = playerCount;
		Board = new DotCom[sizeOfBoard];
		Players = new Player[playerCount];
		input = new BufferedReader(new InputStreamReader(System.in));
		
		int thisPlayer;		
		for(thisPlayer = 0; thisPlayer < playerCount; thisPlayer++)
		{
			Players[thisPlayer] = new Player("Player"+ (thisPlayer + 1));
		}
		
		int thisCell;
		for(thisCell = 0; thisCell < sizeOfBoard; thisCell++)
		{
			Board[thisCell] = new DotCom("", thisCell);
		}
	}
	
	public void fillBoard(int numOfHiddenObjects){
		
		int currentPosition;
		String currentObject;
		
		for(int i = 0; i < numOfHiddenObjects; i++){
			
			currentPosition = (int) (Math.random() * sizeOfBoard);
			currentObject = boardElements[(int) (Math.random() * (boardElements.length))];
			
		//System.out.println(currentPosition + " " + currentObject);

			Board[currentPosition].setName(currentObject);
		}
		
	}

	public boolean isAllCellSelected()
	{
		for(int i = 0; i < sizeOfBoard - 1; i++){
			if(Board[i].isSelected() == false){
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isAllGuessesOver(){
		for(int i = 0; i < sizeOfBoard - 1; i++)
		{
			if(Board[i].getName().length() > 0)
				if(Board[i].isSelected() == false)
					{
					return false;
					}
		}
		return true;
	}
	
	public void printBoard(int boardSize)
	{
		int count = 1;
				System.out.println("\n\n\n");
		
			for(int thisCell = 0; thisCell < sizeOfBoard; thisCell++){
			
				if(Board[thisCell].isSelected())
					System.out.print("-\t");
				else
				System.out.print(count + "\t");
				
				if((count % boardSize) == 0)
					System.out.println();
			
				count++;
			}
			
			
		
	}
	
	public void readPlayerInput(int playerNo, int guess)
	{
		Players[playerNo].setGuess(guess);
//		System.out.println(Players[playerNo].getName() + " guessed " + guess);
	}
	
	public void updateScore()
	{
		int guess;
		
		for(int thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			guess = Players[thisPlayer].getGuess();
			
			if(guess < 0 || guess >= sizeOfBoard){
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		
		for(int thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			
			guess = Players[thisPlayer].getGuess();
			
//			System.out.println(guess + " " +Board[guess].getName());
			
			if(!(Board[guess].getName().length()==0)){
				if(!Board[guess].isSelected())
				{
					Players[thisPlayer].addScore();
					System.out.println(Players[thisPlayer].getName() + " has scored.");
				}
			}
			Board[guess].setSelected();
		}
	}

	public void showScores() {
		
		int winner = 0;
		int winningScore = 0;
		int thisPlayer = 0;
		
		System.out.println("\n\nTHE Dot.Com GUESSING GAME");
		
		winningScore = Players[0].getScore();
		//-- this for loop checks for the winning score
		for(thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++)
		{
			if(Players[thisPlayer].getScore() > winningScore)
			{
				winningScore = Players[thisPlayer].getScore();
				winner = thisPlayer;
			}
			
		}
		
		System.out.print("\nCONGRATULATIONS " + Players[winner].getName().toUpperCase());
		
		//-- this for loop checks for more than one players with the winning score
		for(thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			if(Players[thisPlayer].getScore() == winningScore)
			{
				if(thisPlayer != winner){
					System.out.print(", " + Players[thisPlayer].getName().toUpperCase());
				}
			}
		}
		System.out.println("...!!!");
		
		System.out.println("\n\t\tLEADERBOARD\n" +
								 "----------------------------------------------");
		System.out.println(String.format(" %-35s %-4s","Name","Score"));
		System.out.println("----------------------------------------------");
		for(thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			
			System.out.println(String.format(" %-35s   %-4s",Players[thisPlayer].getName(),Players[thisPlayer].getScore()));
			
		}
		System.out.println("----------------------------------------------\n\n");
	}

	public void setCustomPlayerNames() throws IOException {
		
		System.out.println("Enter your names:");
		for(int thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			System.out.print(Players[thisPlayer].getName() + " : ");
			Players[thisPlayer].setName(input.readLine());
		}
		
	}
}
