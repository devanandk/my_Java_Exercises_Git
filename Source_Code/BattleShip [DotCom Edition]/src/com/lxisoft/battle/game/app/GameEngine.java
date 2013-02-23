package com.lxisoft.battle.game.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lxisoft.battleship.game.elements.DotCom;
import com.lxisoft.battleship.game.elements.Player;

public class GameEngine {
	
	private static final int KILL_SCORE = 6;
	private static final int HIT_SCORE = 1;
	DotCom[][] Board;
	Player[] Players;
	int sizeOfBoard;
	int noOfPlayers;
	BufferedReader input;
	int currentPlayer;
	
	String[] boardElements = {"Gmail.com", "Hotmail.com", "Yahoo.com", "Rediffmail.com", 
							"Engadget.com", "in.com", "flipkart.com", "Alagesia.org", "piratebay.org",
							"Wikipedia.org"};
	
	String[] boardPositions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
							   "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	public GameEngine(int boardSize, int playerCount)
	{
		sizeOfBoard = boardSize;
		noOfPlayers = playerCount;
		currentPlayer = 0;
		Board = new DotCom[sizeOfBoard][sizeOfBoard];
		Players = new Player[playerCount];
		input = new BufferedReader(new InputStreamReader(System.in));
		int thisPlayer;		
		for(thisPlayer = 0; thisPlayer < playerCount; thisPlayer++)
		{
			Players[thisPlayer] = new Player("Player"+ (thisPlayer + 1));
		}
		
		int row, col;
		for(row = 0; row < sizeOfBoard; row++){
			for(col = 0; col < sizeOfBoard; col++)
		{
			Board[row][col] = new DotCom("");
		}
		}
	}
	
	public void fillBoard(int numOfHiddenObjects){
		
		int currentRow;
		int currentCol;
		String currentObject;
		int currentDirection;
		
		for(int i = 0; i < numOfHiddenObjects; i++){
			
			currentRow = (int) (Math.random() * sizeOfBoard-1);
			currentCol = (int) (Math.random() * sizeOfBoard-1);
			if(currentRow < 1) currentRow = 1;
			if(currentCol < 1) currentCol = 1;
			
			currentObject = boardElements[(int) (Math.random() * (boardElements.length))];
			currentDirection = (int) (Math.random() * 10);
			
//			System.out.println(currentRow + "  " + currentCol + "  " + currentObject + "  " + currentDirection);
			
			if(placeShip(currentRow, currentCol, currentObject, currentDirection)){
//				System.out.println("new ship placed at " + currentRow + "  " + currentCol + 
//						" Direction : " + currentDirection);
			}
//			else
//			{
//				System.out.println("ship at " + currentRow + " " + currentCol + " overlaps existing ship.");
//			}
		}
		
	}

	private boolean placeShip(int currentRow, int currentCol,
			String currentObject, int currentDirection) {
		
		String currentId;
		
		currentId = String.format("%s.%s.%s.%s", currentRow, currentCol, currentObject, currentDirection);
		
		if((currentDirection % 2) == 0){
			for(int thisRow = currentRow -1; thisRow <= currentRow + 1; thisRow++){
				if(Board[thisRow][currentCol].getDotComId().length() > 0){
					return false;
				}
			}
			for(int thisRow = currentRow -1; thisRow <= currentRow + 1; thisRow++){
				Board[thisRow][currentCol].setName(currentObject);
				Board[thisRow][currentCol].setDotComId(currentId);
				Board[thisRow][currentCol].setDirection(currentDirection);
			}
			
		}
		else{
			
			for(int thisCol = currentCol -1; thisCol <= currentCol + 1; thisCol++){
				if(Board[currentRow][thisCol].getDotComId().length() > 0){
					return false;
				}
			}
			for(int thisCol = currentCol -1; thisCol <= currentCol + 1; thisCol++){
				Board[currentRow][thisCol].setName(currentObject);
				Board[currentRow][thisCol].setDotComId(currentId);
				Board[currentRow][thisCol].setDirection(currentDirection);
			}
			
		}
		System.out.println(" current ship id is " + currentId);
		return true;
	}

	public boolean isAllCellSelected()
	{
		for(int i = 0; i < sizeOfBoard - 1; i++){
			for(int j = 0; j < sizeOfBoard - 1; j++)
			if(Board[i][j].isSelected() == false){
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
			for(int j = 0; j < sizeOfBoard - 1; j++)
			if(Board[i][j].getName().length() > 0)
				if(Board[i][j].isSelected() == false)
					{
					return false;
					}
		}
		return true;
	}
	
	public void printBoard(int boardSize)
	{
		System.out.println("\n\n\n");
		
			for(int row = 0; row < sizeOfBoard; row++){
				System.out.print(" " + boardPositions[row] + " |  ");
				for(int col = 0; col < sizeOfBoard; col++)
				{
								
				if(Board[row][col].isSelected())
					if(Board[row][col].getDotComId().length() > 0){
						System.out.print(String.format("%-5s", "H"));
					}
					else
					{
						System.out.print(String.format("%-5s", " "));
					}
				else
				{
					System.out.print(String.format("%-5s", "~"));
				}
			}
			System.out.println("\n   | ");
			}
			System.out.print("    ");
			for(int j = 0; j < sizeOfBoard; j++){
			System.out.print(String.format("%5s", "-----"));
			}
			System.out.println();
			System.out.print("      ");

			for(int i = 0; i < sizeOfBoard; i++){
				System.out.print(String.format("%-5s", i+1));
			}
		System.out.println("\n");
	}
	
	public boolean readPlayerInput(int playerNo, String guess)
	{
		Players[playerNo].setGuess(guess);
		currentPlayer = playerNo;
		return this.updateScore();
	}
	
	public boolean updateScore()
	{
		String guess;
		boolean hitFlag = false;
		
		guess = Players[currentPlayer].getGuess();
			
		if(this.getGuessRow(guess) < 0 || this.getGuessRow(guess) >= sizeOfBoard
					|| this.getGuessCol(guess) < 0 || this.getGuessRow(guess) >= sizeOfBoard){
				throw new ArrayIndexOutOfBoundsException();
		}

		int row, col;

		row = this.getGuessRow(guess);
		col = this.getGuessCol(guess);
			
	
		if(!(Board[row][col].getName().length()==0)){
				if(!Board[row][col].isSelected())
				{
					Board[row][col].setUserName(Players[currentPlayer].getName());
					Board[row][col].setSelected();
					
					if(isKillStreak(row, col)){
						Players[currentPlayer].addScore(KILL_SCORE);
						System.out.println("\n " + Players[currentPlayer].getName() + " has sunk "+ Board[row][col].getName());	
					}
					else{
					Players[currentPlayer].addScore(HIT_SCORE);
					System.out.println("\n " + Players[currentPlayer].getName() + " has scored.");
					}
					hitFlag = true;
				}
		}
		Board[row][col].setSelected();
		return hitFlag;
	}
			
	private boolean isKillStreak(int row, int col) {
		
		int killCount = 0;
		
		String shipId = Board[row][col].getDotComId();
		
		if((Board[row][col].getDirection() % 2) == 0)
		{																						//-- for the current row
			for(int thisRow = 0; thisRow < sizeOfBoard; thisRow++)
			{																					//-- check each cell such that
				if(Board[thisRow][col].getDotComId().length() > 0 && Board[thisRow][col].isSelected())
				{																				//-- if the dotComId has been set then,
					if(Board[thisRow][col].getDotComId().equals(shipId))
					{																			//-- check if the dotComId is equal to the current shipId for each cell and that all those cells have been hit
						if(Board[thisRow][col].getUserName().equals(Players[currentPlayer].getName()))
																								//-- if all hits were not made by the same user then
							killCount++;
					}
				}
			}
		}
		else{																					//-- for the current column
			for(int thisCol = 0; thisCol < sizeOfBoard; thisCol++)
			{																					//-- check each cell such that
				if(Board[row][thisCol].getDotComId().length() > 0 && Board[row][thisCol].isSelected())
				{																				//-- if the dotComId has been set then,
					if(Board[row][thisCol].getDotComId().equals(shipId))
					{																			//-- check if the dotComId is equal to the current shipId for each cell and that all those cells have been hit
						if(Board[row][thisCol].getUserName().equals(Players[currentPlayer].getName()))
																								//-- if all hits were not made by the same user then
							killCount++;
					}
				}
			}
			
			}
		if(killCount == 3) return true;
		else return false;
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
	
	public int getGuessRow(String currentGuess){
		int position = 0;
		
		String letter = currentGuess.substring(0, 1);
		
		for(String option: boardPositions){
			if(letter.toUpperCase().equals(option)){
				return position;
			}
			position++;
		}
		
		return -1;
	}
	
	public int getGuessCol(String currentGuess){
		int position = -1;
		String letter = currentGuess.substring(1, currentGuess.length());
		position = Integer.parseInt(letter);
		
		return position-1;
}

	@SuppressWarnings("unused")
	public static void waitAndExit(){
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("\nPRESS ENTER TO EXIT.");
			String i = ip.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.exit(0);
	}

}
