package com.lxisoft.battleship.game.elements;

public class DotCom {
	
	private String name;
	private boolean selected;
	private String dotComId;
	private int direction;
	private String userName;
	
	public DotCom(String newName)
	{
		name = newName;
		selected = false;
		dotComId = "";
		setDirection(0);
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	public String getUserName() {
		return userName;
	}

	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
