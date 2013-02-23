package com.lxisoft.battleship.game.elements;

/**
 * The DotCom class constitutes the data members and methods required to 
 * create and manipulate each individual cell on the game board. It contains 
 * variables to store the DotCom name, DotCom ID, identify the player who selected the cell, flag 
 * to check if the cell has been selected and the direction of the ship.
 * 
 * @author Devanand
 * @version 1.0
 */
public class DotCom {
	
	private String name;
	private boolean selected;
	private String dotComId;
	private int direction;
	private String userName;
	
	/**
	 * Constructor for DotCom Class
	 * @param newName
	 */
	public DotCom(String newName)
	{
		name = newName;
		selected = false;
		dotComId = "";
		setDirection(0);
	}
	
	/**
	 * Sets name to the DotCom object
	 * @param newName	String : Name to be set
	 */
	public void setName(String newName){
		name = newName;
	}
	
	/**
	 * returns the name of the DotCom instance
	 * @return	String : name of the DotCom instance
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * check if selection flag is set for the DotCom object
	 * @return	boolean : true if selection flag is set, else false
	 */
	public boolean isSelected()
	{
		return selected;
	}
	
	/**
	 * Sets the selection flag to true
	 */
	public void setSelected()
	{
		selected = true;
	}
	
	/**
	 * Sets the ID for the current DotCom object
	 * 
	 * @param id String : ID to be set for the current DotCom object
	 */
	public void setDotComId(String id){
		dotComId = id;
	}
	
	/**
	 * returns the ID of the current DotCom instance
	 * @return	String : ID of the current DotCom instance
	 */
	public String getDotComId(){
		return dotComId;
	}

	/**
	 * returns the direction value for the current DotCom instance
	 * 
	 * @return int : direction value denoting the orientation of the ship, default 0
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * sets the direction value for the current DotCom instance
	 * 
	 * @param direction	int : direction value denoting the orientation of the ship
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * returns the name of the player who has selected the current DotCom instance
	 * @return	String : name of the player who selected the current DotCom instance
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * sets the name of the player who has selected the current DotCom instance
	 * 
	 * @param userName	String : name of the player
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
