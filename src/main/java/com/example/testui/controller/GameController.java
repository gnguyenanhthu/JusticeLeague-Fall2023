package com.example.testui.controller;

import com.example.testui.model.*;
import com.example.testui.view.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameModel getGameModel() { return gameModel; }

    public GameView getGameView() { return gameView; }

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        TextField commandBox = new TextField();
        gameView.updateView(gameModel.getPlayer().location());
        gameView.testView(commandBox);
        commandBox.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String command = commandBox.getText();
                commandBox.clear();
                if (command.equalsIgnoreCase("Hello")) {
                    gameView.updateView(gameModel.getPlayer().location());
                }
                else if (command.equalsIgnoreCase("north")) {
                    gameModel.getPlayer().moveNorth();
                    gameView.updateView(gameModel.getPlayer().location());
                }
                else if (command.equalsIgnoreCase("east")) {
                    gameModel.getPlayer().moveEast();
                    gameView.updateView(gameModel.getPlayer().location());
                }
                else if (command.equalsIgnoreCase("south")) {
                    gameModel.getPlayer().moveSouth();
                    gameView.updateView(gameModel.getPlayer().location());
                }
                else if (command.equalsIgnoreCase("west")) {
                    gameModel.getPlayer().moveWest();
                    gameView.updateView(gameModel.getPlayer().location());
                }
                else {
                    gameView.updateView("Please input right command.");
                }
            }
        });

    }
}
