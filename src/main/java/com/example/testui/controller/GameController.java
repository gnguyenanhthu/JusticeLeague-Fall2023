package com.example.testui.controller;

import com.example.testui.model.*;
import com.example.testui.view.*;
import javax.sound.sampled.*;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameModel getGameModel() { return gameModel; }

    public GameView getGameView() { return gameView; }

    public GameController(GameModel gameModel, GameView gameView) {
        File file = new File("tiptaptwo.wav");
        this.gameModel = gameModel;
        this.gameView = gameView;
//        music(file);
        gameStart();
        gamePlay();
    }

    public void gameStart(){
        gameView.mainPage();
        gameView.getNameBox().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                String name = gameView.getNameBox().getText();
                gameModel.getPlayer().setPlayerName(name);
                gameView.loadGame();
                gameView.testView();
                gameView.updateView("Hello " + gameModel.getPlayer().getPlayerName() + ", welcome to SCP DANGER!"
                        + "\n" + gameModel.getPlayer().displayLocation());
            }
        });

        gameView.getLoadButton().setOnMouseClicked(e -> {
            File file = new File("Save1.bin");
            try {
                gameModel.setPlayer(gameModel.loadGame(file));
                gameModel.getPlayer().setCurrentRoom(gameModel.getPlayer().getCurrentRoom());
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            gameView.loadGame();
            gameView.testView();
            gameView.updateView("You have successfully loaded the game.\n" + gameModel.getPlayer().enterRoom(true));
        });
    }

    private void gamePlay() {
        gameView.getCommandBox().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String command = gameView.getCommandBox().getText();
                gameView.getCommandBox().clear();
                if (command.equalsIgnoreCase("exit")) {
                    System.exit(0);
                } else if (gameModel.getPlayer().getPlayerHP() <= 0) {
                    gameView.updateView(gameModel.getPlayer().revivePlayer());
                } else {
                    if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n")) {
                        gameView.updateView(gameModel.getPlayer().enterRoom(gameModel.getPlayer().moveNorth()));
                    } else if (command.equalsIgnoreCase("east") || command.equalsIgnoreCase("e")) {
                        gameView.updateView(gameModel.getPlayer().enterRoom(gameModel.getPlayer().moveEast()));
                    } else if (command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s")) {
                        gameView.updateView(gameModel.getPlayer().enterRoom(gameModel.getPlayer().moveSouth()));
                    } else if (command.equalsIgnoreCase("west") || command.equalsIgnoreCase("w")) {
                        gameView.updateView(gameModel.getPlayer().enterRoom(gameModel.getPlayer().moveWest()));
                    } else if (command.equalsIgnoreCase("location") || command.equalsIgnoreCase("l") || command.equalsIgnoreCase("")) {
                        gameView.updateView(gameModel.getPlayer().displayLocation());
                    } else if (command.equalsIgnoreCase("info")) {
                        gameView.updateView(gameModel.getPlayer().showInfo());
                    } else if (command.equalsIgnoreCase("stats")) {
                        gameView.updateView(gameModel.getPlayer().showEquipped());
                    } else if (command.equalsIgnoreCase("explore")) {
                        gameView.updateView(gameModel.getPlayer().explore());
                    } else if (command.equalsIgnoreCase("solve")) {
                        gameModel.getPlayer().playPuzzle(gameView);
                    } else if (command.equalsIgnoreCase("combine")) {
                        gameView.updateView(gameModel.getPlayer().combineItem());
                    } else if (command.equalsIgnoreCase("weapon")) {
                        gameView.updateView(gameModel.getPlayer().weaponList());
                    } else if (command.equalsIgnoreCase("ex monster")) {
                        gameModel.getPlayer().inspectMonster(gameView);
                    } else if (command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")) {
                        if (gameModel.getPlayer().getInventory().size() <= 8)
                            gameView.updateView(gameModel.getPlayer().showInventory());
                        else
                            gameView.updateInventory(gameModel.getPlayer().showInventory());
                    } else if (command.contains("pickup")) {
                        if (command.length() > 7) {
                            String itemId = command.substring(7, command.length());
                            gameView.updateView(gameModel.getPlayer().pickUp(itemId));
                        } else
                            gameView.updateView("You did not specify what to pick up.");
                    } else if (command.contains("drop")) {
                        if (command.length() > 5) {
                            String itemId = command.substring(5, command.length());
                            gameView.updateView(gameModel.getPlayer().dropItem(itemId));
                        } else
                            gameView.updateView("You did not specify what to drop.");
                    } else if (command.contains("inspect")) {
                        if (command.length() > 8) {
                            String itemId = command.substring(8, command.length());
                            gameView.updateView(gameModel.getPlayer().inspectItem(itemId));
                        } else
                            gameView.updateView("You did not specify what to inspect.");
                    } else if (command.contains("equip") && !command.contains("unequip")) {
                        if (command.length() > 6) {
                            String itemId = command.substring(6, command.length());
                            gameView.updateView(gameModel.getPlayer().equipItem(itemId));
                        } else
                            gameView.updateView("You did not specify what to equip.");
                    } else if (command.contains("unequip")) {

                        if (command.length() > 8) {
                            String itemId = command.substring(8, command.length());
                            gameView.updateView(gameModel.getPlayer().unequipItem(itemId));
                        } else {
                            gameView.updateView("You did not specify what to unequip.");
                        }
                    } else if (command.contains("use")) {
                        if (command.length() > 4) {
                            String itemId = command.substring(4, command.length());
                            gameView.updateView(gameModel.getPlayer().useItem(itemId));
                        } else {
                            gameView.updateView("You did not specify what to use.");
                        }
                    } else if (command.equalsIgnoreCase("save")){
                        File file = new File("Save1.bin");
                        try {
                            gameModel.saveGame(gameModel.getPlayer(),file);
                            gameView.updateView("You have successfully saved the game.");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else if (command.equalsIgnoreCase("load")){
                        File file = new File("Save1.bin");
                        try {
                            gameModel.setPlayer(gameModel.loadGame(file));
                            gameModel.getPlayer().setCurrentRoom(gameModel.getPlayer().getCurrentRoom());
                            gameView.updateView("You have successfully loaded the game.\n" + gameModel.getPlayer().enterRoom(true));
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        gameView.updateView("Please input right command.");
                    }
                }
            }
        });
    }

    public void music(File file)
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
            // If you want to stop the sound, then use clip.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
