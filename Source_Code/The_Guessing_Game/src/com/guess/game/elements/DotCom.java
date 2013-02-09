package com.guess.game.elements;

public class DotCom {
	
	String name;
	//int position;
	boolean selected;
	
	public DotCom(String newName, int currPos)
	{
		name = newName;
		//position = currPos;
		selected = false;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName()
	{
		return name;
	}

	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected()
	{
		selected = true;
	}
}
