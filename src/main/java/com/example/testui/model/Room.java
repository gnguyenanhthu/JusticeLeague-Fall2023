package com.example.testui.model;

public class Room {
    private int roomID;
    private String roomName;
    private int northRoom;
    private int eastRoom;
    private int southRoom;
    private int westRoom;
    private boolean isVisited;

    public Room(int roomID, String roomName, int northRoom, int eastRoom, int southRoom, int westRoom, boolean isVisited) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.northRoom = northRoom;
        this.eastRoom = eastRoom;
        this.southRoom = southRoom;
        this.westRoom = westRoom;
        this.isVisited = isVisited;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(int northRoom) {
        this.northRoom = northRoom;
    }

    public int getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(int eastRoom) {
        this.eastRoom = eastRoom;
    }

    public int getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(int southRoom) {
        this.southRoom = southRoom;
    }

    public int getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(int westRoom) {
        this.westRoom = westRoom;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", roomName='" + roomName + '\'' +
                ", northRoom=" + northRoom +
                ", eastRoom=" + eastRoom +
                ", southRoom=" + southRoom +
                ", westRoom=" + westRoom +
                ", isVisited=" + isVisited +
                "}\n";
    }
}
