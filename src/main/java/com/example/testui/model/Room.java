package com.example.testui.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
	private static final long serialVersionUID = 1234L;
	private int roomID;
	private String roomName;
	private String roomDescription;
	private int northRoom;
	private int eastRoom;
	private int southRoom;
	private int westRoom;
	private boolean isVisited;
	private String keyID;
	
	private ArrayList<Item> roomItems = new ArrayList<>();
	private Equippable requiredItem;
	
	private Puzzle puzzle;
	private Monster monster;
	
	//Constructor
	public Room(int roomID, String roomName, String roomDescription, int northRoom, int eastRoom, int southRoom,
			int westRoom, boolean isVisited, String keyID) {
		super();
		this.roomID = roomID;
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.northRoom = northRoom;
		this.eastRoom = eastRoom;
		this.southRoom = southRoom;
		this.westRoom = westRoom;
		this.isVisited = isVisited;
		this.requiredItem = null;
		this.puzzle = null;
		this.monster = null;
		this.keyID = keyID;
	}
	
	//Getter
	public int getRoomID() { return roomID; }

	public String getRoomName() { return roomName; }

	public String getRoomDescription() { return roomDescription; }

	public int getNorthRoom() { return northRoom; }

	public int getEastRoom() { return eastRoom; }

	public int getSouthRoom() { return southRoom; }

	public int getWestRoom() { return westRoom; }

	public boolean isVisited() { return isVisited; }
	
	public ArrayList<Item> getRoomItems() { return roomItems; }

	public Equippable getRequiredItem() { return requiredItem;}

	public Puzzle getPuzzle() { return puzzle; }

	public Monster getMonster() { return monster; }
	
	public String getKeyID() { return keyID; }

	//Setter
	public void setVisited(boolean isVisited) { this.isVisited = isVisited; }

	public void setRoomItems(ArrayList<Item> roomItems) { this.roomItems = roomItems; }

	public void setRequiredItem(Equippable requiredItem) { this.requiredItem = requiredItem; }

	public void setPuzzle(Puzzle puzzle) { this.puzzle = puzzle; }

	public void setMonster(Monster monster) { this.monster = monster; }
	
	public void addItem(Item i) { this.roomItems.add(i); }
	
	public void setKeyID(String keyID) { this.keyID = keyID; }
	
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomName=" + roomName + ", roomDescription=" + roomDescription
				+ ", northRoom=" + northRoom + ", eastRoom=" + eastRoom + ", southRoom=" + southRoom + ", westRoom="
				+ westRoom + ", isVisited=" + isVisited + ", keyID=" + keyID + ", roomItems=" + roomItems
				+ ", requiredItem=" + requiredItem + ", puzzle=" + puzzle + ", monster=" + monster + "]\n";
	}
	
}
