package com.example.testui.model;
import java.io.Serializable;

public class Monster implements Serializable {
	private static final long serialVersionUID = 1234L;
	private int initRoomID;
	private String monsterID;
	private String monsterName;
	private int monsterHP;
	private int monsterDmg;
	private int damageThreshold; // Threshold for double damage
	
	public Monster(int initRoomID, String monsterID, String monsterName, int monsterHP, int monsterDmg, int damageThreshold) {
		super();
		this.initRoomID = initRoomID;
		this.monsterID = monsterID;
		this.monsterName = monsterName;
		this.monsterHP = monsterHP;
		this.monsterDmg = monsterDmg;
		this.damageThreshold = damageThreshold;
	}
	
	//Getter    
	public int getInitRoomID() { return initRoomID; }
	
	public String getMonsterID() { return monsterID; }
	
	public String getMonsterName() { return monsterName; }
	
	public int getMonsterHP() { return monsterHP; }

	public int getMonsterDmg() { return monsterDmg; }
	
	public int getDamageThreshold() { return damageThreshold; }

	//Setter
	public void setInitRoomID(int initRoomID) { this.initRoomID = initRoomID; }

	public void setMonsterID(String monsterID) { this.monsterID = monsterID; }

	public void setMonsterName(String monsterName) { this.monsterName = monsterName; }

	public void setMonsterHP(int monsterHP) { if(monsterHP > 0) { this.monsterHP = monsterHP; } else { this.monsterHP = 0; } }

	public void setMonsterDmg(int monsterDmg) { this.monsterDmg = monsterDmg; }

	@Override
	public String toString() {
		return "Monster [initRoomID=" + initRoomID + ", monsterID=" + monsterID + ", monsterName=" + monsterName
				+ ", monsterHP=" + monsterHP + ", monsterDmg=" + monsterDmg + "]\n";
	}
	
}
