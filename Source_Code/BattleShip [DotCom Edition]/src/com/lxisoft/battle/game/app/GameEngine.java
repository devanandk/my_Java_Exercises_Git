package com.lxisoft.battle.game.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lxisoft.battleship.game.elements.DotCom;
import com.lxisoft.battleship.game.elements.Player;

/**
 * The Game Engine class provides all the services for running the Dot.Com guessing game. It generates the game board, displays it, tracks
 * user input, maintains user scores, provides methods to check the current game status and maintains important
 * information for execution of the game. 
 * 
 * @author Devanand
 * @version 1.1
 */

public class GameEngine {
	
	public static final int KILL_SCORE = 6;
	public static final int HIT_SCORE = 1;
	
	
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
	
	/**
	 * Constructor for GameEngine Class<br><br>
	 * 
	 * The constructor initializes the game engine object using the board size and player count
	 * provided. It performs the following functions:
	 * <br>
	 * <br>1. set sizeOfBoard
	 * <br>2. set noOfPlayers
	 * <br>3. creates new DotCom matrix of size specified by boardSize
	 * <br>4. creates new Player array of size specified by playerCount
	 * <br>5. initializes the BufferedReader input
	 * <br>6. Sets a default name for each player in the Player[] array
	 * <br>7. Initializes each cell of the Board[][] to a new DotCom object
	 * 
	 * @param boardSize 	int : size of the board for the current instance of the game
	 * @param playerCount	int : number of players for the current instance of the game
	 */
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
	
	/**
	 * Fills the Board[][] matrix with the hidden objects ("Dot.Com Ships")<br>
	 * <br>
	 * The fillBoard() method generates a random set of numbers to determine the row, column, element and direction
	 * for the Dot.Com ships that will be stored on the Board[][] matrix. It then calls the {@link #placeShip(int, int, String, int)} method
	 * to place ship at the generated co-ordinates
	 * 
	 * @param numOfHiddenObjects	int : specifies the maximum number of hidden "ships" that can be stored on the Board[][]
	 */
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

	
	/**
	 * Places a ship on the specified co-ordinates if it satisfied a fixed set of conditions<br>
	 * <br>
	 * The input row and column values are used evaluated to ensure that no other ship already exists within the
	 * bounds of the new ship's position. If a ship already exists in the given range, then the method exits returning false
	 * else it places the ship at the given co-ordinates and places a Dot.Com name from the {@link #boardElements} array.
	 * <br><br>
	 * The method also generates a unique ID for each ship it places it in the ship.
	 * 
	 * @param currentRow		int : row where the ship could be placed.
	 * @param currentCol		int : column where the ship could be placed.
	 * @param currentObject		String : object that should be placed in the Dot.Com ship.
	 * @param currentDirection	int : value to identify whether the ship must be placed vertically or horizontally.
	 * @return					boolean : returns true if a ship is successfully placed at the input co-ordinates, else returns false.
	 */
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
//		System.out.println(" current ship id is " + currentId);
		return true;
	}

	
	/**
	 * Checks if all cells on the board have already been selected by the players<br>
	 * <br>
	 * The method iterates each cell in the {@link #Board} matrix and checks if the cell is selected using the {@link DotCom#isSelected()} method.
	 *   
	 * @return	boolean : if any cell is not yet selected returns false, else returns true
	 */
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
	
	/**
	 * Checks if all the hidden guesses on the board has already been selected<br>
	 * <br>
	 * This method iterates through each of the cells on the {@link #Board} matrix identifying cells with a Dot.Com name set in them. If any such cell in not yet selected
	 * then the method returns false, else it returns true denoting that all available guesses on the board are over.
	 * 
	 * @return	boolean : true if all guesses have already been selected by the players, else false.
	 */
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
	
	/**
	 * Used to display the Game Board.<br>
	 * <br>
	 * The print board method displays a square matrix of the specified board size. Various elements displayed by the printBoard method are:
	 * <ul>
	 * <li> Index positions at the boundary using numbers in the X axis and Letters from {@link #boardPositions} array on the Y axis
	 * <li> Unselected board elements identified by the "~" symbol
	 * <li> Hits by the players are identified by "H" symbol
	 * <li> Miss by the players are identfied by an empty space at the guess location.
	 * 
	 * @param boardSize	int : size of the board to be displayed screen
	 */
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
	
	/**
	 * Sets the current player guess and calls the {@link #updateScore()} method<br>
	 * <br>
	 * This method sets the current guess for the player by calling the {@link Player#setGuess(String)} method
	 * sets the {@link GameEngine#currentPlayer} to the playerNo parameter and calls the {@link #updateScore()} method
	 * to check the player's guess.
	 * <p>
	 * The boolean return value identifies whether the current player guess is a Hit or a Miss. This is used by the {@link GuessGameLauncher#runGame()} 
	 * method.
	 * 
	 * @see Player#setGuess(String)
	 * @see #updateScore()
	 * @see GuessGameLauncher#runGame()
	 * 
	 * @param playerNo	int : index position of the current player in the {@link #Players} array
	 * @param guess		String : guess made by the current player 
	 * @return			boolean : true if the current player guess is a hit, else false
	 */
	public boolean readPlayerInput(int playerNo, String guess)
	{
		Players[playerNo].setGuess(guess);
		currentPlayer = playerNo;
		return this.updateScore();
	}
	
	/**
	 * Checks the guess by the current player and updates the game state<br>
	 * <br>
	 * <p>
	 * This method checks the guess made by the current player (identified by {@link #currentPlayer} index position of the {@link #Players} array)
	 * and changes the following elements to update the game state:
	 * <ul>
	 * <li>Adds the {@link #HIT_SCORE} to the player score in case of a hit
	 * <li>Adds the {@link #KILL_SCORE} to the player score in case of a kill
	 * <li>Uses the {@link #isKillStreak(int, int)} method to identify a kill
	 * <li>Sets the cell guessed by the user as selected using the {@link DotCom#setSelected()} method
	 * </ul>
	 * <p>The method returns true if the guess is a Hit, else returns false
	 *  
	 * @see #isKillStreak(int, int)
	 * @return	boolean : true if player guess is a hit, else false
	 * @throws ArrayIndexOutOfBoundsException if the player guess is not a valid position on the board
	 */
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
	
	/**
	 * Checks if the current hit is a Kill Streak<br>
	 * <br>
	 * <p>
	 * The method checks the current board state to identify if the the player guess resulted in a kill streak
	 * (All 3 cells of the ship has been hit by the same player). If the current hit constitutes a kill streak, the method
	 * returns true, else false. This is identified by checking if all cells of the ship is selected and that they were all selected by
	 * the same user.
	 * 
	 * @param row	int : row position of current guess
	 * @param col	int : column position of current guess
	 * @return		boolean : true if guess resulted in kill streak, else false
	 */
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

	/**
	 * Used to display the final scores of all players and display a leader board<br>
	 * <br>
	 * The method is called once the game has ended. The method identifies the game winner and displays a congratulations to them.
	 * It also displays a formatted leader board showing the scores of all players.
	 */
	public void showScores() {
		
		int winner = 0;
		int winningScore = 0;
		int thisPlayer = 0;
		
		System.out.println("\n\nTHE Dot.Com GUESSING GAME [BATTLESHIP EDITION]");
		
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

	/**
	 * Sets custom name for the players<br>
	 * <br>
	 * The method calls {@link Player#setName(String)} method to set custom names for the players to replace the default names
	 * set by the {@link GameEngine#GameEngine(int, int)} constructor.
	 * 
	 * @throws IOException in case of unforeseen errors while reading user names.
	 */
	public void setCustomPlayerNames() throws IOException {
		
		System.out.println("Enter your names:");
		for(int thisPlayer = 0; thisPlayer < noOfPlayers; thisPlayer++){
			System.out.print(Players[thisPlayer].getName() + " : ");
			Players[thisPlayer].setName(input.readLine());
		}
		
	}
	
	/**
	 * Identifies the current row index position from player guess<br>
	 * <br>
	 * The method selects the substring of player guess denoting the row index and identifies the corresponding 
	 * integer position value using the {@link #boardPositions} array as reference. It then returns this position.
	 * 
	 * @param currentGuess	String : contains the guess made by the current player (such as A1, B3, D5 etc)
	 * @return				int : denoting the corresponding integer index value for the row position on the board
	 */
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
	
	/**
	 * Identifies the current column index position from player guess<br>
	 * <br>
	 * The method selects the substring of player guess denoting the column index and parses the value
	 * into an integer using {@link Integer#parseInt(String)} method. It then returns this position.
	 * 
	 * @param currentGuess	String : contains the guess made by the current player (such as A1, B3, D5 etc)
	 * @return				int : denoting the corresponding integer index value for the column position on the board
	 */
	public int getGuessCol(String currentGuess){
		int position = -1;
		String letter = currentGuess.substring(1, currentGuess.length());
		position = Integer.parseInt(letter);
		
		return position-1;
}

	/**
	 * Used to make the console wait for an Enter Key press from the user before exiting the game.
	 */
	public static void waitAndExit(){
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("\nPRESS ENTER TO EXIT.");
			
			@SuppressWarnings("unused")
			String i = ip.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.exit(0);
	}

}
