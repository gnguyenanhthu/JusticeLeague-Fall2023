package com.example.testui.model;

import java.io.*;
import java.util.*;

public class Map {
    private static final String defaultFile = "Map.txt";
    ArrayList<Room> myMap = new ArrayList<>();
    public Map(){
        try {
            Scanner scan = new Scanner(new File(defaultFile));
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                int roomID = scan.nextInt();
                int northRoom = scan.nextInt();
                int eastRoom = scan.nextInt();
                int southRoom = scan.nextInt();
                int westRoom = scan.nextInt();
                String roomName = scan.next();
                boolean isVisited = Boolean.parseBoolean(scan.nextLine());
                myMap.add(new Room(roomID,roomName,northRoom,eastRoom,southRoom,westRoom,isVisited));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Room getRoom(int roomID){return myMap.get(roomID-1);} //roomID starts at 1

    public int getNumberOfRooms(){return myMap.size();} //total rooms

    @Override
    public String toString() {
        return "Map{" +
                "MyMap=" + myMap +
                '}';
    }
}
