package com.example.testui.model;

public class Equippable extends Item{
	private static final long serialVersionUID = 1234L;
	private int hpValue;

	public Equippable(int initRoomID, String itemID, String itemName, String itemType, String itemDescription,
			int hpValue) {
		super(initRoomID, itemID, itemName, itemType, itemDescription);
		this.hpValue = hpValue;
	}

	public int getHpValue() { return hpValue; }
	
}
