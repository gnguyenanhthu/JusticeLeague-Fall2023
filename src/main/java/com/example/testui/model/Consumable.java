package com.example.testui.model;

public class Consumable extends Item {
	private static final long serialVersionUID = 1234L;
	
	//HP recovery value for consumable items
	private int hpValue;

	//Constructor from superclass
	public Consumable(int initRoomID, String itemID, String itemName, String itemType, String itemDescription, int hpValue ) {
		super(initRoomID, itemID, itemName, itemType, itemDescription);
		this.hpValue = hpValue;
	}
	
	
	//Getters
	public int getHpValue() { return hpValue; }

}
