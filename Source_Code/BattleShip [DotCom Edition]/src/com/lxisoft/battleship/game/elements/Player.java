package com.lxisoft.battleship.game.elements;

public class Player {
	
	private String name;
	private int score;
	private String currentGuess;
	private boolean killStreak;
	
	public Player(String playerName){
		name = playerName;
		score = 0;
		currentGuess = "";
		killStreak = false;
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
	
	public boolean isKillStreak(){
		return killStreak;
	}
	
	public void setKillStreak(){
		killStreak = true;
	}
	
	public void resetKillStreak(){
		killStreak = false;
	}
	
}
