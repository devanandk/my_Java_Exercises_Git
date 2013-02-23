package com.lxisoft.battleship.game.elements;

/**
 * The player class contains the data elements and methods to manage the state and behavior of
 * a single player in the DotCom game. It stores the name, current score and current guess of 
 * the player and offers methods to access and manipulate them.
 * 
 * @author Devanand
 * @version 1.0
 */
public class Player {
	
	private String name;
	private int score;
	private String currentGuess;
	
	/**
	 * Constructor for the Player class
	 * 
	 * @param playerName	String : name of the player
	 */
	public Player(String playerName){
		name = playerName;
		score = 0;
		currentGuess = "";
	}
	
	/**
	 * stores the guess made by the player to the current Player instance
	 * 
	 * @param guess	String : guess made by the player (A1, B2, D5 etc)
	 */
	public void setGuess(String guess)
	{
		currentGuess = guess;
	}
	
	/**
	 * returns the guess stored by the current Player instance
	 * 
	 * @return String : guess stored in current Player instance
	 */
	public String getGuess()
	{
		return currentGuess;
	}
	
	/**
	 * returns the name of the current Player instance
	 * 
	 * @return String : name of current Player instance
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Adds the incrementValue to the current score of the Player instance
	 * 
	 * @param incrementValue int : Value to be added to the current player score
	 */
	public void addScore(int incrementValue)
	{
		score = score + incrementValue;
	}

	/**
	 * returns the current score of the Player instance
	 * 
	 * @return int : current score of the Player instance
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * sets a name to the current Player instance
	 * 
	 * @param playerName String : Name to be set for the current instance
	 */
	public void setName(String playerName)
	{
		name = playerName;
	}
}
