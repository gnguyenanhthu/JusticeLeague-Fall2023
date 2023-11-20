package com.example.testui.model;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private Map myMap;
    private Room currentRoom;

    public Player(String playerName,Map map) {
        this.playerName = playerName;
        this.myMap = map;
        this.currentRoom = myMap.getRoom(1);
        currentRoom.setVisited(true);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /*Check if current region is visited
    If already visited -> say familiar
    If not visited -> set to visited
    */
    public void checkVisited(){
        if (currentRoom.isVisited())
            System.out.println("This Region looks familiar.");
        else currentRoom.setVisited(true);
    }

    public void moveNorth(){
        if (currentRoom.getNorthRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getNorthRoom()));
            checkVisited();
        }
    }

    public void moveEast(){
        if (currentRoom.getEastRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getEastRoom()));
            checkVisited();
        }
    }

    public void moveSouth(){
        if (currentRoom.getSouthRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getSouthRoom()));
            checkVisited();
        }
    }

    public void moveWest(){
        if (currentRoom.getWestRoom() == 0)
            System.out.println("You cannot go this way. Please choose another direction!");
        else {
            setCurrentRoom(myMap.getRoom(currentRoom.getWestRoom()));
            checkVisited();
        }
    }

    public void checkAllVisited(){
        ArrayList<Room> notVisited = new ArrayList<>();
        for (int i = 1; i <= myMap.getNumberOfRooms(); i++){
            if(!myMap.getRoom(i).isVisited())
                notVisited.add(myMap.getRoom(i));
        }
        if (notVisited.isEmpty())
            System.out.println("Congratulations! You have visted all regions.");
        else{
            System.out.println("These are regions you have not visited: ");
            notVisited.forEach((n) -> System.out.println(n.getRoomID() + " " + n.getRoomName() ));
        }
    }

    //Print player's location
    public String location(){
        return "You are at Region " + currentRoom.getRoomID() + ", " + currentRoom.getRoomName();
    }

}
