package com.example.testui.view;

import com.example.testui.model.*;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.io.IOException;

public class GameView extends Pane {

    private Text display1 = new Text("");

    public GameView() {
    }

    public void testView(TextField textField) {

        Text text1 = new Text("Please enter your command");
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("null", 20));

        Text text2 = display1;
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("null", 20));

        //Creating Text Filed for Name
        TextField textField1 = textField;
        textField1.setBackground(Background.fill(Color.GAINSBORO));
        textField1.setFont(Font.font("null",15));
        textField1.setPrefHeight(20);

        //Creating Buttons
        Button button1 = new Button("Enter");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(800, 600);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(20);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(reflectionTitle(), 1, 0);
        gridPane.add(text1, 1, 1);
        gridPane.add(textField1, 1, 2);
        gridPane.add(button1, 2, 2);
        gridPane.add(text2,1,5);
        getChildren().add(gridPane);
    }
    public void mainPage() {

        Text text1 = new Text("Please enter your name");
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("null", 20));

        Text text2 = display1;
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("null", 20));

        //Creating Text Filed for Name
        TextField textField1 = new TextField();
        textField1.setBackground(Background.fill(Color.GAINSBORO));
        textField1.setFont(Font.font("null",15));
        textField1.setPrefHeight(20);

        //Creating Buttons
        Button button1 = new Button("Enter");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(800, 600);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(20);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(reflectionTitle(), 1, 0);
        gridPane.add(text1, 1, 1);
        gridPane.add(textField1, 1, 2);
        gridPane.add(button1, 2, 2);
        gridPane.add(text2,1,5);
        getChildren().add(gridPane);
    }

    public Node reflectionTitle() {
        Text t = new Text("SCP DANGER");
        t.setX(10.0f);
        t.setY(50.0f);
        t.setCache(true);
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 50));

        Reflection r = new Reflection();
        r.setFraction(0.8f);
        t.setEffect(r);
        t.setTranslateY(-150);
        return t;
    }

    public void updateView(String message){
            display1.setText(message);
    }
}
