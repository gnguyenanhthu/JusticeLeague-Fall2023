package com.example.testui.model;
import java.io.*;
import java.util.*;
//Thu

public class Map implements Serializable{
	private static final long serialVersionUID = 1234L;
	private static final String defaultFile = "Map.txt";
    ArrayList<Room> myMap = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Puzzle> puzzleList = new ArrayList<>();
    ArrayList<Monster> monsterList = new ArrayList<>();
    ArrayList<Item> combineItem = new ArrayList<>();
    ArrayList<Equippable> requiredItem = new ArrayList<>();
    ArrayList<Room> spawnRooms = new ArrayList<>();   
    
    public Map() {
        loadRoom();
        System.out.println("Finish loading Room");
        //Uncomment the next line to see all rooms' info
        //System.out.println(myMap);

        loadItem();
         
        //Place items into rooms
        for (Item i : itemList) {
            myMap.get(i.getInitRoomID() - 1).addItem(i);
        }
        System.out.println("Finish adding Item");
        //Uncomment the next line to see all items' info
        //System.out.println(itemList);

        loadPuzzle();
        //Place puzzles into rooms
        for (Puzzle p : puzzleList) {
            myMap.get(p.getInitRoomID() - 1).setPuzzle(p);
        }
        System.out.println("Finish adding Puzzle");
        //Uncomment this line to see all puzzles' info
        //System.out.println(puzzleList);

        loadMonster();
        //Place monsters into rooms
        for (Monster m : monsterList) {
            myMap.get(m.getInitRoomID() - 1).setMonster(m);
        }
        System.out.println("Finish adding Monster");
        //Uncomment the next line to see all monsters' info
        //System.out.println(monsterList);
        
        loadCombineRequired();
        for (Equippable i : requiredItem) {
            myMap.get(i.getInitRoomID() - 1).setRequiredItem(i);;
        }
        //Uncomment the next line to see combine items' info
        //System.out.println(combineItem);
        
        loadSpawnRooms();
    }

    //Load all rooms
    public void loadRoom(){
        try {
            Scanner scan = new Scanner(new File(defaultFile));
            scan.useDelimiter("~");
            while (scan.hasNext()) {
                int roomID = scan.nextInt();
                String roomName = scan.next();
                String line = scan.nextLine();
                String roomDescription = scan.next();
                line = scan.nextLine();
                int northRoom = scan.nextInt();
                int eastRoom = scan.nextInt();
                int southRoom = scan.nextInt();
                int westRoom = scan.nextInt();
                String keyID = scan.next();
                line = scan.nextLine();
                line = scan.nextLine();
                myMap.add(new Room(roomID,roomName,roomDescription,northRoom,eastRoom,southRoom,westRoom,false, keyID));
            }
            scan.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    //Loads SpawnRooms
    private void loadSpawnRooms() {
        try {
            Scanner scan = new Scanner(new File("SpawnRoom.txt"));
            while (scan.hasNext()) {
                int spawnRoomID = scan.nextInt();
                spawnRooms.add(myMap.get(spawnRoomID));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Loading Combine Item information from CombineItem.txt
    public void loadItem(){
        try {
            Scanner scan = new Scanner(new File("Item.txt"));
            while (scan.hasNext()) {
            	int initRoomID = scan.nextInt();
            	String line = scan.nextLine();
                String itemID = scan.nextLine();
                String itemName = scan.nextLine();
                String itemType = scan.nextLine();
                String itemDescription = scan.nextLine();
                
                //Check for consumable/weapon
                if(itemType.equalsIgnoreCase("Consumable")) {
                	int hpValue = Integer.parseInt(scan.nextLine());
                	line = scan.nextLine();
                	itemList.add(new Consumable(initRoomID, itemID, itemName, itemType, itemDescription, hpValue));
                } else if(itemType.equalsIgnoreCase("Weapon")) {
                	int atkValue = Integer.parseInt(scan.nextLine());
                	line = scan.nextLine();
                	itemList.add(new Weapon(initRoomID, itemID, itemName, itemType, itemDescription, atkValue));
                }  else if(itemType.equalsIgnoreCase("Equippable")) {
                	int hpValue = Integer.parseInt(scan.nextLine());
                	line = scan.nextLine();
                	itemList.add(new Equippable(initRoomID, itemID, itemName, itemType, itemDescription, hpValue));
                }  
                else {
                	line = scan.nextLine();
                	itemList.add(new Item(initRoomID, itemID, itemName, itemType, itemDescription));
                }
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    //Loading Combine items and Required Items information from CombineRequired.txt
    public void loadCombineRequired(){
        try {
            Scanner scan = new Scanner(new File("CombineRequired.txt"));
            while (scan.hasNext()) {
            	int initRoomID = scan.nextInt();
            	String line = scan.nextLine();
                String itemID = scan.nextLine();
                String itemName = scan.nextLine();
                String itemType = scan.nextLine();
                String itemDescription = scan.nextLine();         
                if (itemType.equalsIgnoreCase("Required")) {
                	int hpValue = Integer.parseInt(scan.nextLine());
                	line = scan.nextLine();
                	requiredItem.add(new Equippable(initRoomID, itemID, itemName, itemType, itemDescription, hpValue));
                }
                else {
                	line = scan.nextLine();
                	combineItem.add(new Item(initRoomID, itemID, itemName, itemType, itemDescription));
                }
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Puzzle information from Puzzle.txt
    public void loadPuzzle(){
        try {
            Scanner scan = new Scanner(new File("Puzzle.txt"));
            while(scan.hasNext()){
            	int initRoomID = scan.nextInt();
            	String line = scan.nextLine();
                int puzzleID = scan.nextInt();
                line = scan.nextLine();
                String puzzleName = scan.nextLine();
                int numberOfAttempts = scan.nextInt();
                line = scan.nextLine();
                String question = scan.nextLine();
                String answer = scan.nextLine();
                String solvedMessage = scan.nextLine();
                String hint = scan.nextLine();
                int puzzleDmg = scan.nextInt();
                line = scan.nextLine(); line = scan.nextLine();
                puzzleList.add(new Puzzle(initRoomID,puzzleID,puzzleName,numberOfAttempts,question,answer,solvedMessage,hint,puzzleDmg));
            }
            scan.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    //Loading Monster information from Monster.txt
    public void loadMonster(){
        try {
            Scanner scan = new Scanner(new File("Monster.txt"));
            scan.useDelimiter("~");
            while(scan.hasNext()){
            	int initRoomID = scan.nextInt();
                String monsterID = scan.next();
                String monsterName = scan.next();
                int monsterHP = Integer.valueOf(scan.next());
                int monsterDmg = Integer.valueOf(scan.next());
                int threshold = Integer.parseInt(scan.next());
                String line = scan.nextLine();
                monsterList.add(new Monster(initRoomID,monsterID,monsterName,monsterHP,monsterDmg, threshold));
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public Room getRoom(int roomID){return myMap.get(roomID-1);} //roomID starts at 1

    public int getNumberOfRooms(){return myMap.size();} //total rooms

    public ArrayList<Item> getCombineItem() { return combineItem; }
    
    public ArrayList<Room> getSpawnRooms() {return spawnRooms;}
         
    

	@Override
    public String toString() {
        return "Map{" +
                "MyMap=" + myMap +", spawnRooms=" + spawnRooms +
                '}';
    }
}
