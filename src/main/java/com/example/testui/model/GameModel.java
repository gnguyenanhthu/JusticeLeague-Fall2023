package com.example.testui.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Player player;
    private Map myMap = new Map();

    public GameModel() {
        player = new Player("Thu",myMap);
    }

    public Player getPlayer() { return player; }
}
