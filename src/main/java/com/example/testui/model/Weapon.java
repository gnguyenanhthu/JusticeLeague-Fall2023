package com.example.testui.model;

public class Weapon extends Item{
	private static final long serialVersionUID = 1234L;
	private int atkValue;
	
	//Constructor from superclass
	public Weapon(int initRoomID, String itemID, String itemName, String itemType, String itemDescription,
			int atkValue) {
		super(initRoomID, itemID, itemName, itemType, itemDescription);
		this.atkValue = atkValue;
	}

	//Getters
	public int getAtkValue() { return atkValue; }

}
