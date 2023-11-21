package com.example.testui.model;

import java.io.Serializable;

public class Item implements Serializable, Comparable <Item>{
	private static final long serialVersionUID = 1234L;
	private int initRoomID;
	private String itemID;
	private String itemName;
	private String itemType;
	private String itemDescription;
	//Constructor
	public Item(int initRoomID, String itemID, String itemName, String itemType, String itemDescription) {
		super();
		this.initRoomID = initRoomID;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemDescription = itemDescription;
	}

	//Getter
	public int getInitRoomID() { return initRoomID; }

	public String getItemID() { return itemID; }

	public String getItemName() { return itemName; }

	public String getItemType() { return itemType; }

	public String getItemDescription() { return itemDescription; }
	
	 @Override
	    public int compareTo(Item o) {
	        if (Integer.parseInt(itemID.substring(1)) < Integer.parseInt(o.itemID.substring(1)))
	            return -1;
	        else if (Integer.parseInt(itemID.substring(1)) > Integer.parseInt(o.itemID.substring(1)))
	            return 1;
	        else return 0;
	    }

	@Override
	public String toString() {
		return "Item [initRoomID=" + initRoomID + ", itemID=" + itemID + ", itemName=" + itemName + ", itemType="
				+ itemType + ", itemDescription=" + itemDescription + "]\n";
	}
	
	
	
}
