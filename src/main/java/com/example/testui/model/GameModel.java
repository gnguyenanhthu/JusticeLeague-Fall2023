package com.example.testui.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Player player;
    private Map myMap = new Map();

    public GameModel() {
        player = new Player("Player",myMap);
    }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public static void saveGame(Player obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Player loadGame(File file) throws IOException, ClassNotFoundException {
        Player result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Player) ois.readObject();
        }
        return result;
    }

}
