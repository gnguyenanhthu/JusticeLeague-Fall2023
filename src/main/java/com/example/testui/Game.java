package com.example.testui;

import com.example.testui.controller.*;
import com.example.testui.model.*;
import com.example.testui.view.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application{
    public void start(Stage stage) throws IOException{
        // Create the model
        GameModel gameModel = new GameModel();

        // Create the view
        GameView gameView = new GameView();

        // Create the controller
        GameController gameControl = new GameController(gameModel, gameView);

        // Create the root pane and add the view
        Pane pane = new Pane();
        pane.getChildren().add(gameControl.getGameView());
        pane.setBackground(Background.fill(Color.BLACK));

        // Create the scene and set it on the stage
        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("SCP Danger");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}