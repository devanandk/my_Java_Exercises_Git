package com.guess.game.elements;

public class Player {
	
	String name;
	int score;
	int currentGuess;
	
	public Player(String playerName){
		name = playerName;
		score = 0;
		currentGuess = -1;
	}
	
	public void setGuess(int guess)
	{
		currentGuess = guess;
	}
	
	public int getGuess()
	{
		return currentGuess;
	}
	
	public String getName()
	{
		return name;
	}
	public void addScore()
	{
		score++;
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
