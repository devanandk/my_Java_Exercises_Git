package com.lxisoft.battleship.game.elements;

public class DotCom {
	
	private String name;
	private boolean selected;
	private String dotComId;
	
	
	public DotCom(String newName)
	{
		name = newName;
		selected = false;
		dotComId = "";
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
	
	public void setDotComId(String id){
		dotComId = id;
	}
	
	public String getDotComId(){
		return dotComId;
	}
}
