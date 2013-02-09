package com.guess.game.elements;

public class Dice {
	
	int number;
	private static int MAXDICE = 6;
	
	public int rollDice()
	{
		number = ((int) Math.random() * MAXDICE);
		return number;
	}

}
