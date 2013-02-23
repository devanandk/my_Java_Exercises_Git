package com.lxisoft.battleship.game.elements;

public class Player {
	
	private String name;
	private int score;
	private String currentGuess;
	
	public Player(String playerName){
		name = playerName;
		score = 0;
		currentGuess = "";
	}
	
	public void setGuess(String guess)
	{
		currentGuess = guess;
	}
	
	public String getGuess()
	{
		return currentGuess;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addScore(int incrementValue)
	{
		score = score + incrementValue;
	}

	public int getScore()
	{
		return score;
	}
	
	public void setName(String playerName)
	{
		name = playerName;
	}
}
