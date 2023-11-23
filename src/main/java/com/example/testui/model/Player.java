package com.example.testui.model;
import com.example.testui.view.*;
import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
	private static final long serialVersionUID = 1234L;
	private String playerName;
	private Map gameMap;
	private Room currentRoom;
	private Room spawnRoom;
	private int playerHP;
	private int playerMaxHP;
	
	private ArrayList<Item> inventory = new ArrayList<>();
	private ArrayList<Equippable> equipped = new ArrayList<>();
	private ArrayList<Weapon> weapons = new ArrayList<>();
	
	//Constructor
	public Player(String playerName, Map gameMap) {
		super();
		 this.playerName = playerName;
	     this.gameMap = gameMap;
	     this.currentRoom = gameMap.getRoom(1);
	     this.spawnRoom = currentRoom;
	     currentRoom.setVisited(true);
	     this.playerHP = 100;
	     this.playerMaxHP = 100;
	}
	
	// Getter
    public String getPlayerName() { return playerName; }

	public Room getCurrentRoom() { return currentRoom; }

	public Room getSpawnRoom() { return spawnRoom; }
	
	public int getPlayerHP() { return playerHP; }
	
	public int getPlayerMaxHP() { return playerMaxHP; }
	
	public ArrayList<Item> getInventory() { return inventory; }
	
	public ArrayList<Equippable> getEquipped() { return equipped; }

	// Setter
	public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

	public void setSpawnRoom(Room spawnRoom) { this.spawnRoom = spawnRoom; }

	public void setPlayerHP(int hpValue) {
		if (hpValue >= playerMaxHP) {
			this.playerHP = playerMaxHP;
			return;
		}
		if (hpValue <= 0) {
			this.playerHP = 0;
			return;
		}
		this.playerHP = hpValue;
	}
	
	public void setPlayerMaxHP(int playerMaxHP) { this.playerMaxHP = playerMaxHP; }
	
	public boolean checkKey() {
		return (currentRoom.getKeyID().equals("0"));
	}
	
	// Check required item when enter a room
	// Thu
	public boolean checkRequiredItem() {
		if (currentRoom.getRequiredItem() == null)
			return true;
		else {
			for (Equippable e : equipped) {
				if (e.getItemID().equalsIgnoreCase(currentRoom.getRequiredItem().getItemID()))
					return true;
			}
			return false;
		}
	}

	// Move player to North Room
	// Thu
	public String checkVisited(){
		if (currentRoom.isVisited())
			return "\n---------------\nThis Region looks familiar.";
		else {
			currentRoom.setVisited(true);
			return "";
		}
	}

	public boolean moveNorth(){
		if (currentRoom.getNorthRoom() == 0)
			return false;
		else {
			setCurrentRoom(gameMap.getRoom(currentRoom.getNorthRoom()));
			return true;
		}
	}

	public boolean moveEast(){
		if (currentRoom.getEastRoom() == 0)
			return false;
		else {
			setCurrentRoom(gameMap.getRoom(currentRoom.getEastRoom()));
			return true;
		}
	}

	public boolean moveSouth(){
		if (currentRoom.getSouthRoom() == 0)
			return false;
		else {
			setCurrentRoom(gameMap.getRoom(currentRoom.getSouthRoom()));
			return true;
		}
	}

	public boolean moveWest(){
		if (currentRoom.getWestRoom() == 0)
			return false;
		else {
			setCurrentRoom(gameMap.getRoom(currentRoom.getWestRoom()));
			return true;
		}
	}
    
    // Move player to Spawn Room
    // Thu
    public void checkSpawnRoom() {
        for (Room room : gameMap.spawnRooms) {
        	if (currentRoom.getRoomID() == room.getRoomID())
        		setSpawnRoom(room);
        }
    }
    
    // Revive player to Spawn Room
    // Andrew
    public String revivePlayer() {
            setPlayerHP(100);  // Reset player's HP to the maximum value
            setCurrentRoom(this.spawnRoom);  // Move the player to the spawn room
			return "You have been revived at the spawn room.\n" + displayLocation();
    }
    
    // Action happens after player enter a room
    // Thu
    public String enterRoom(boolean roomFound){
		if (!roomFound)
			return "You cannot go this way.\nPlease choose another direction!";
    	if (!checkKey()) {
    		Item key = null;
    		for(Item item : gameMap.itemList) {
    			if(item.getItemID().equals(currentRoom.getKeyID())) {
    				key = item;
    			}
        	}
    		if (key == null) {
    			for(Item item : gameMap.combineItem) {
        			if(item.getItemID().equals(currentRoom.getKeyID())) {
        				key = item;
        			}
            	}
    		}
    		setCurrentRoom(gameMap.getRoom(currentRoom.getRoomID()-1));
    		return "The next room is locked, you need to use " + key.getItemName() + " to unlock.";
    	}
    	else if(!checkRequiredItem()) {
			setPlayerHP(0);
    		return "You didn't equip the right item, you're dead!"
					+ "Press Enter to revive at the spawn room or \"exit\" to quit the game.";
    	}
    	else {
    		checkSpawnRoom();
			return displayLocation() + checkVisited();
    	}
    }
    
    // Inspect a monster a room
    // Thu
    public void inspectMonster() {
        if (currentRoom.getMonster() != null) {
            Monster monster = currentRoom.getMonster();
            System.out.println("You inspect the monster in the room:");
            System.out.println("--------------------------");
            System.out.println("Monster ID: " + monster.getMonsterID());
            System.out.println("Monster Name: " + monster.getMonsterName());
            System.out.println("Monster HP: " + monster.getMonsterHP());
            System.out.println("Monster Attack: " + monster.getMonsterDmg());
            System.out.println("--------------------------");
            System.out.println("What do you want to do with it?");
            System.out.println("1. Fight it (fight)");
            System.out.println("2. Leave it alone (ignore)");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            while(!answer.equalsIgnoreCase("fight") && !answer.equalsIgnoreCase("ignore")){
                System.out.println("Please type \"fight\" or \"ignore\".");
                answer = input.nextLine();
            }
            if (answer.equalsIgnoreCase("fight")) {
                fightMonster();
            }
            else {
                System.out.println("\nSCP is still here if you come back!\n");
            }
        } else {
            System.out.println("There's no monster to inspect in this room.");
        }
    }

    // Action when Player fights Monster
    // Thu, Andrew
    public void fightMonster() {
        if (currentRoom.getMonster() != null) {
            Monster monster = currentRoom.getMonster();
            System.out.println("\nGAME ON");
            System.out.println("Here are commands you can use: ");
            System.out.println("- Select weapon (1,2,3) \n- Inventory, equip, unequip, use");
            if (weapons.isEmpty()) {
            	System.out.println("You don't have any weapons");
            } else {
                System.out.println("\nYour list of weapon:");
                for (int i=0; i < weapons.size(); i++) {
                	System.out.println(i+1 + "/ " + weapons.get(i).getItemName() + " (" + weapons.get(i).getAtkValue() + ")");
                }
            }
            System.out.println("Please input your command: ");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            while (monster.getMonsterHP() > 0 && playerHP > 0){
                if (answer.equalsIgnoreCase("inventory")) {
                    showInventory();
                }
                else if (answer.toLowerCase().contains("equip") && !answer.toLowerCase().contains("unequip")){
                    String itemName = answer.substring(6,answer.length()); //split item name
                    equipItem(itemName);
                }
                else if (answer.toLowerCase().contains("unequip")){
                    String itemName = answer.substring(8,answer.length()); //split item name
                    unequipItem(itemName);
                }
                else if (answer.toLowerCase().contains("use")){
                    String itemName = answer.substring(4,answer.length()); //split item name
                    useItem(itemName);
                }
                else if (answer.equalsIgnoreCase("1") || answer.equalsIgnoreCase("2") || answer.equalsIgnoreCase("3")){
                	int playerAttack = 0;
                	if (answer.equalsIgnoreCase("1") && !weapons.isEmpty())
                		playerAttack = weapons.get(0).getAtkValue();
                	else if (answer.equalsIgnoreCase("2") && weapons.size() >= 2)
                		playerAttack = weapons.get(1).getAtkValue();
                	else if (answer.equalsIgnoreCase("3") && weapons.size() >= 3)
                		playerAttack = weapons.get(2).getAtkValue();
                	else {
                		System.out.println("You don't have this weapon!");
                		playerAttack = 0;
                	}
                	monster.setMonsterHP(monster.getMonsterHP() - playerAttack);
                	System.out.println("\n~~~~~~~~~~~~~");
                    System.out.println("You deal " + playerAttack + " damage to " + monster.getMonsterName());
                    System.out.println(monster.getMonsterName() + "'s HP: " + monster.getMonsterHP());
                    System.out.println("~~~~~~~~~~~~~");
                    if (monster.getMonsterHP() <= 0) {
                        currentRoom.setMonster(null);
                        System.out.println("Monster is dead. You won!");
                        break;
                    }
                    int dice = (int) ((Math.random() * (monster.getDamageThreshold()*3 - 1)) + 1);
                    if (dice < monster.getDamageThreshold()) {
                        setPlayerHP(playerHP - monster.getMonsterDmg() * 2);
                        System.out.println("Critical hit!");
                        System.out.println("Monster deals " + monster.getMonsterDmg()*2 + " damage to you.");
                    }
                    else {
                        setPlayerHP(playerHP - monster.getMonsterDmg());
                        System.out.println("Monster deals " + monster.getMonsterDmg() + " damage to you.");
                    }
                    System.out.println(playerName + "'s HP: " + playerHP);
                    System.out.println("~~~~~~~~~~~~~\n");
                    if (playerHP <= 0){
                        System.out.println("You're dead! GAME OVER!");
                        System.out.println("Press enter to revive at the spawn room or \"exit\" to quit the game.");
                		String decision = input.nextLine();	
                		if (decision.equalsIgnoreCase("exit")) {
                			return;
                		}
                		else {
                			revivePlayer();
                		}
                        break;
                    }
                } else {
                    System.out.println("Please enter correct command.");
                }
                if (weapons.isEmpty()) {
                	System.out.println("You don't have any weapons");
                } else {
	                System.out.println("Your list of weapon:");
	                for (int i=0; i < weapons.size(); i++) {
	                	System.out.println(i+1 + "/ " + weapons.get(i).getItemName() + " (" + weapons.get(i).getAtkValue() + ")");
	                }
                }
                System.out.println("Please input your command: ");
                answer = input.nextLine();
            }       
        } else {
            System.out.println("\nThere's no monster to fight in this room.\n");
        }
    }
    
    // Display all weapons in inventory
    // Thu, Andrew
    public void weaponList() {
    	if (weapons.isEmpty()) {
    		System.out.println("\nYou don't have any weapons in your inventory.\n");
    		return;
    	}
	    System.out.println("\nYour list of weapons:");
	    System.out.println("--------------------------");
	    Collections.sort(weapons);
	    for (Weapon weapon : weapons) {
	            System.out.println("Weapon ID: " + weapon.getItemID());
	            System.out.println("Weapon Name: " + weapon.getItemName());
	            System.out.println("Attack Value: " + weapon.getAtkValue());
	            System.out.println("--------------------------\n");
	    }
	}
    
    // Print player's current room information including RoomID, RoomName and RoomDescription
    // Thu
    public String displayLocation(){
		return "You are at Room (" + currentRoom.getRoomID() + ") "
				+ currentRoom.getRoomName() + "\n" + currentRoom.getRoomDescription();
    }
    
    // Display all commands and their functions
    // Thu
    public void displayHelpMenu(){
    	   System.out.println("\n--------------HELP MENU--------------");
           System.out.printf("| %2s %5s %-10s %10s \n", "n/north", "", "Move North","|");
           System.out.printf("| %2s %6s %-10s %10s \n", "e/east","","Move East","|");
           System.out.printf("| %2s %5s %-13s %7s \n", "s/south","", "Move South","|");
           System.out.printf("| %2s %6s %-13s %7s \n", "w/west","","Move West","|");
           System.out.printf("| %2s %5s %-13s %6s \n", "explore","","Explore a room","|");
           System.out.printf("| %2s %3s %-10s %5s \n", "inventory","", "Check inventory","|");
           System.out.printf("| %2s %6s %-10s %8s \n", "pickup","","Pick up item","|");
           System.out.printf("| %2s %8s %-10s %10s \n", "drop","","Drop item","|");
           System.out.printf("| %2s %5s %-13s %7s \n", "inspect","","Inspect item","|");
           System.out.printf("| %2s %7s %-13s %7s \n", "equip","","Equip item","|");
           System.out.printf("| %2s %5s %-13s %7s \n", "unequip","","Unequip item","|");
           System.out.printf("| %2s %9s %-10s %10s \n", "use","","Use item","|");
           System.out.printf("| %2s %2s %-10s %5s \n", "ex monster","","Examine monster","|");
           System.out.printf("| %2s %7s %-10s %5s \n", "fight","","Fight a monster","|");
           System.out.printf("| %2s %6s %-10s %1s \n", "ignore","","Skip monster/puzzle","|");
           System.out.printf("| %2s %4s %-10s %6s \n", "location","","Check location","|");
           System.out.printf("| %2s %7s %-10s %5s \n", "stats","","Check equipment","|");
           System.out.printf("| %2s %8s %-10s %5s \n", "info","","Check player HP","|");
           System.out.printf("| %2s %8s %-10s %10s \n", "help","","Help menu","|");
           System.out.println("-------------------------------------\n");
    }
    
    // Print items found in the current room
    // ET
    public String explore() {
		String message = "";
    	if (currentRoom.getRoomItems().isEmpty()) {
    		message += "Nothing but this weird room here.";
    	}
    	else {
    		Collections.sort(currentRoom.getRoomItems());
    		for(Item item : currentRoom.getRoomItems()) {
    			message = message + "\n-------------------------------\n" + item.getItemID() + ": " + item.getItemName();
    		}
    		message += "\n-------------------------------\n";
    	}
		if (currentRoom.getPuzzle()!=null)
			message += "Hey you have a puzzle to solve!\n";
    	if (currentRoom.getMonster() != null) {
    		message += "There's an SCP in this room!\n";
    	}
		return message;
    }

    // Show non-equipped items currently in inventory
    // ET
    public String showInventory() {
    	if(inventory.isEmpty()) {
    		return "You have nothing in your inventory right now.";
    	}
    	else {
    		Collections.sort(inventory);
			String message = "Your Inventory:\n";
    		for(Item item : inventory) {
    			message = message + "\n" + item.getItemID() + ": " + item.getItemName();
    		}
			return message;
    	}
    }

    // Pickup an item in a room
    // ET
    public String pickUp(String itemID) {
		if (currentRoom.getRoomItems().isEmpty()) {
			return "There's nothing here to pick up.";
		} else if (currentRoom.getPuzzle() != null) {
			return "You have to solve the puzzle before picking up items.";
		} else {
			for (Item item : currentRoom.getRoomItems()) {
				if (item.getItemID().equals(itemID)) {
					currentRoom.getRoomItems().remove(item);
					inventory.add(item);
					if (item instanceof Weapon)
						weapons.add((Weapon) item);
					return "You've pickup up " + item.getItemName() + " and placed it in your inventory.";
				}
			}
			return "This item does not exist here.";
		}
	}
    
    // Drop an item from an inventory, put item into the room
    // ET
    public String dropItem(String itemID) {
    	if(inventory.isEmpty()) {
    		return "There's nothing to drop.";
    	}
    	else {
    		Item item = findItem(itemID);
    		if(item!=null) {
    				inventory.remove(item);
    				currentRoom.getRoomItems().add(item);
    				if (item instanceof Weapon)
    					weapons.remove((Weapon) item);
					return "You've dropped " + item.getItemName() + " on the floor.";
    		}
    		else {
    			return "You don't have this item.";
    		}
    	}
    }

    // Display the puzzle in a room
    // Augustine, ET, Thu
	public void playPuzzle (GameView view) {
		if (currentRoom.getPuzzle()!= null) {
			int numAttempts = currentRoom.getPuzzle().getAttempts();
			view.updateView(currentRoom.getPuzzle().getQuestion());
			view.enterEvent();
			view.getAnswerBox().setOnKeyPressed(e -> {
				if(e.getCode() == KeyCode.ENTER){
						String answer = view.getAnswerBox().getText();
						view.getAnswerBox().clear();
						view.getCommandBox().clear();
						if (answer.equalsIgnoreCase("hint")) {
							view.updateView("Hint:" + currentRoom.getPuzzle().getHint());
						}
						else if (answer.equalsIgnoreCase(currentRoom.getPuzzle().getAnswer())) {
							String message = currentRoom.getPuzzle().getSolvedMessage() + "\n" + autoPickup();
							view.updateView(message);
							currentRoom.setPuzzle(null);
							view.exitEvent();
						}
						else {
							currentRoom.getPuzzle().setAttempts(currentRoom.getPuzzle().getAttempts()-1);
							view.updateView("You answered incorrectly! You have " + currentRoom.getPuzzle().getAttempts() + " attempt(s) left.\n"
									+ currentRoom.getPuzzle().getQuestion());
						}
					}
					if (currentRoom.getPuzzle()!= null && currentRoom.getPuzzle().getAttempts() == 0) {
						String message = "You failed the puzzle.\n";

						currentRoom.getPuzzle().setAttempts(numAttempts);
						if (currentRoom.getPuzzle().getPuzzleDmg()!=0) {
							this.setPlayerHP(this.getPlayerHP() - currentRoom.getPuzzle().getPuzzleDmg());
							message += "You took " + currentRoom.getPuzzle().getPuzzleDmg() + " damage.";
							if (playerHP <= 0)
								revivePlayer();
						}
						view.updateView(message);
						view.exitEvent();
					}
			});
		} else {
			view.updateView("There's no puzzle to solve!");
			return;
		}
	}

    // Inspect an item to see description
    // Thu
    public String inspectItem(String itemID) {
    	if(inventory.isEmpty()) {
    		return "There's nothing to inspect.";
    	}
    	else {
    		Item item = findItem(itemID);
    		if(item!=null) {
    				return item.getItemID() + ": " + item.getItemName() + "\n" + item.getItemDescription();
    		}
    		else {
    			return "\nYou don't have this item.";
    		}
    	}
    }
    
    // Find an item in inventory based on itemID
    // Thu
    public Item findItem(String itemID) {
    	for(Item item : inventory) {
			if(item.getItemID().equals(itemID)) {
				return item;
			}
    	}
    	return null;
    }
    
    // Find an item in equipped based on itemID
    // ET
    public Item findEquip(String itemID) {
    	for(Item item : equipped) {
			if(item.getItemID().equals(itemID)) {
				return item;
			}
    	}
    	return null;
    }
    
    // Remove all key cards in inventory while doing combine
    // Thu
    public void removeAllKeys(String itemID) {
    	for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getItemID().equals(itemID)) {
				inventory.remove(i);
				--i;
			}
    	}
    }
    
    // Combine 2 keys to get the higher level key
    // Thu
    public String combineItem() {
    	if (currentRoom.getRoomID() != 5) {
    		return "\nYou can only combine items in Room LC-05.\n";
    	}
    	int key0 = 0, key1 = 0, key2 = 0;
    	for (Item item : inventory) {
    		if (item.getItemID().equalsIgnoreCase("A15"))
    			++key0;
    		if (item.getItemID().equalsIgnoreCase("A16"))
    			++key1;
    		if (item.getItemID().equalsIgnoreCase("A17"))
    			++key2;
    	}
    	if (key0 == 2) {
    		Item item = findItem("A15");
    		removeAllKeys("A15");
    		Item combineItem = gameMap.getCombineItem().get(0);
    		inventory.add(combineItem);
    		return "\nYou have successfully combined 2 " + item.getItemName() + ".\n"
					+ combineItem.getItemID() + ": " + combineItem.getItemName() + " is added to your inventory.\n";
    	}
    	else if (key1 == 2) {
    		Item item = findItem("A16");
    		removeAllKeys("A16");
    		Item combineItem = gameMap.getCombineItem().get(1);
    		inventory.add(combineItem);
			return "\nYou have successfully combined 2 " + item.getItemName() + ".\n"
					+ combineItem.getItemID() + ": " + combineItem.getItemName() + " is added to your inventory.\n";
    	}
    	else if (key2 == 2) {
    		Item item = findItem("A17");
    		removeAllKeys("A17");
    		Item combineItem = gameMap.getCombineItem().get(2);
    		inventory.add(combineItem);
			return "\nYou have successfully combined 2 " + item.getItemName() + ".\n"
					+ combineItem.getItemID() + ": " + combineItem.getItemName() + " is added to your inventory.\n";
    	}
    	else {
    		return "\nYou don't have any items to combine.\n";
    	}
    }
    
    // Equip an item, moving them from the inventory to the equipment array
    // ET
    public String equipItem(String itemID) {
    	// search inventory
    	Item item = findItem(itemID);
    	
    	// if an item is found in the inventory, place it in the equipment array
    	if (inventory.isEmpty()) {
    		return "You literally have nothing. Pick something up.";
    	} else if(item != null && item instanceof Equippable) {
    		Equippable equip = (Equippable)item;
    		removeFromInventory(itemID);
    		equipped.add((Equippable) item);
    		if(equip.getHpValue() != 0){
    			setPlayerMaxHP(getPlayerMaxHP() + equip.getHpValue());
    		}
    		return "You've successfully equipped " + item.getItemName();
    	} else if( !(item instanceof Equippable) ) {
    		return "This is not an equippable item.";
    	} else {
    		return "This item was not found in your inventory.";
    	}
    }
    // Use a consumable item
    // Augustine, Thu
    public String useConsumable (String itemID) {
        Item item = findItem(itemID);
        if (item != null && item instanceof Consumable) {
            removeFromInventory(itemID);
			String message = "You've successfully use " + item.getItemName() + ".\n";
            if (playerHP == playerMaxHP) {
            	message = message + "You are at full HP.\nYou just waste 1 " + item.getItemName() + ".";
            }
            else if (getPlayerHP() + ((Consumable) item).getHpValue() > playerMaxHP) {
                message = message + "You healed " + (playerMaxHP - playerHP);
            }
            else {
                message = message + "You healed " + ((Consumable) item).getHpValue();
            }
            setPlayerHP(getPlayerHP() + ((Consumable) item).getHpValue());           	
            message = message + "\nCurrent HP: " + playerHP + "/" + playerMaxHP;
            return message;
        } else if (!(item instanceof Consumable)) {
            return "This is not a consumable item.";
        } else {
            return "This item was not found in your inventory.\n";
        }
    }
    
    // Unequip an item, moving them from the equipment array back to the inventory.
    // ET
    public String unequipItem(String itemID) {
    	// search inventory
    	Item item = findEquip(itemID);
    	Equippable equip = (Equippable)item;
    	// if an item is found in the inventory, place it in the equipment array
    	if (equipped.isEmpty()) {
    		return "There's nothing on you to remove.";
    		
    	} else if(item != null) {
    		removeFromEquips(itemID);
    		inventory.add((Equippable) item);
    		if(equip.getHpValue() != 0){
    			setPlayerMaxHP(getPlayerMaxHP() - equip.getHpValue());
    			if(getPlayerHP() >= getPlayerMaxHP()) {
    				setPlayerHP(getPlayerMaxHP());
    			}
    		}
    		return "You've successfully unequipped " + item.getItemName();
    	} else {
    		return "You don't have this item on you.";
    	}
    }
    
    // Display a list of items currently equipped.
    // ET
    public String showEquipped(){
    	if(equipped.isEmpty()) {
    		return "You have nothing equipped right now.";
    	}
    	else {
    		Collections.sort(equipped);
    		String message = "Here's what's on you:\n";
    		for(Equippable equip : equipped) {
    			if(equip.getHpValue() > 0) {
    				message = message + equip.getItemID() + ": " + equip.getItemName() + " +" + equip.getHpValue() + " HP.\n";
    			} else {
    				message = message + equip.getItemID() + ": " + equip.getItemName() + "\n";
    			}
    		}
    		return message;
    	}
    }
    
    // Remove item from inventory array based on item ID
    // ET
    public void removeFromInventory(String itemID) {
    	for(Item item : inventory) {
    		if(item.getItemID().equalsIgnoreCase(itemID)) {
    			inventory.remove(inventory.indexOf(item));
    			break;
    		}
    	}
    }
    
    // Remove item from equipped array based on item ID
    // ET
    public void removeFromEquips(String itemID) {
    	for(Item item : equipped) {
    		if(item.getItemID().equalsIgnoreCase(itemID)) {
    			equipped.remove(equipped.indexOf(item));
    			break;
    		}
    	}
    }

    // Use a consumable item or key
    // Thu
    public String useItem(String itemID) {
    	if (inventory.isEmpty()) {
    		return "You literally have nothing. Pick something up.";
    	}
    	Item item = findItem(itemID);
    	if (item == null) {
    		return "\nYou don't have this item in your inventory.\n";
    	}
    	if (!(item instanceof Consumable) && item.getItemType().equalsIgnoreCase("Key Item")) {
    		return useKey(item);
    	} else
    		return useConsumable(itemID);
    }
    
    //Auto-Pickup items
    public String autoPickup() {
    	if (currentRoom.getRoomItems().isEmpty())
    		return "";

		String message = "\nYou automatically picked up: \n";
        // Iterate through the items in the room
        for (Item item : currentRoom.getRoomItems()) {
            // Add the item to the player's inventory
        	inventory.add(item);
        	message = message + item.getItemID() + ": " + item.getItemName() +"\n";
        }
        currentRoom.getRoomItems().clear();
		return message;
    }
    
    // Use a key to unlock a room
    // Thu
    public String useKey(Item key) {
    	Room nextRoom = gameMap.getRoom(currentRoom.getRoomID()+1);
    	if (nextRoom.getKeyID().equalsIgnoreCase("0"))
    		return "There's no room to unlock.";
    	else {
    		nextRoom.setKeyID("0");
    		return "You have successfully unlock the next room!";
    	}
    }
    
    // Show player currentHP and maxHP
    // ET
    public String showInfo() {
    	return "\nHere's how we're lookin:\n" +
				"---------------------\n" +
				"Name: " + playerName + "\n" +
				"HP: " + playerHP + "/" + playerMaxHP + "\n" +
				"---------------------\n";
    }
}
    


