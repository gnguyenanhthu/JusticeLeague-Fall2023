package com.example.testui.view;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class GameView extends Pane {

    //Creating a Grid Pane
    GridPane gridPane = new GridPane();
    StackPane stackPane = new StackPane();
    private Text display1 = new Text("");
    private TextField commandBox = new TextField();
    private TextField answerBox = new TextField();
    private TextField fightBox = new TextField();
    private GridPane inventory = new GridPane();
    private Text invColumn1 = new Text("");
    private Text invColumn2 = new Text("");

    private Button fightButton = new Button("Fight");
    private Button ignoreButton = new Button("Ignore");

    public Text getDisplay1() { return display1; }

    public TextField getCommandBox() { return commandBox; }

    public TextField getAnswerBox() { return answerBox; }

    public TextField getFightBox() { return fightBox; }

    public Button getFightButton() { return fightButton; }

    public Button getIgnoreButton() { return ignoreButton; }

    public void setDisplay1(Text display1) { this.display1 = display1; }

    public void setCommandBox(TextField commandBox) { this.commandBox = commandBox; }

    public GameView() {
    }

    public void testView() {

        //Display everything
        display1.setFill(Color.WHITE);
        display1.setFont(Font.font("null", 20));
        display1.setWrappingWidth(450);

        //Display inventory
        invColumn1.setFill(Color.LAWNGREEN);
        invColumn1.setFont(Font.font("null", 20));
        invColumn2.setFill(Color.LAWNGREEN);
        invColumn2.setFont(Font.font("null", 20));
        inventory.setHgap(15);
        inventory.setPadding(new Insets(20, 20, 20, 20));
        inventory.add(invColumn1, 0,0);
        inventory.add(invColumn2,1,0);

        //Pane to display
        stackPane.setMinSize(550,350);
        stackPane.setMaxSize(550,350);
        stackPane.getChildren().add(display1);
        stackPane.getChildren().add(inventory);
        inventory.setVisible(false);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));

        //Please enter your command
        Text text1 = new Text("Please enter your command");
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("null", 20));
        text1.setTranslateX(140);

        //TextField commandBox
        commandBox.setBackground(Background.fill(Color.GAINSBORO));
        commandBox.setMaxWidth(250);
        commandBox.setFont(Font.font("null",15));
        commandBox.setPrefHeight(20);
        commandBox.setTranslateX(140);


        //Setting size for the pane
        gridPane.setMinSize(900, 700);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(reflectionTitle(), 0, 0);
        gridPane.add(stackPane,0,1);
        gridPane.add(text1, 0, 2);
        gridPane.add(commandBox, 0, 3);
        getChildren().add(gridPane);
    }

    public void enterPuzzle(){
        //Set up answerBox
        answerBox.setBackground(Background.fill(Color.GAINSBORO));
        answerBox.setMaxWidth(250);
        answerBox.setFont(Font.font("null",15));
        answerBox.setPrefHeight(20);
        answerBox.setTranslateX(140);

        //Replace commandBox with answerBox
        gridPane.getChildren().remove(commandBox);
        gridPane.add(answerBox,0,3);
    }

    public void exitPuzzle(){
        //Replace answerBox with commandBox
        gridPane.getChildren().remove(answerBox);
        gridPane.add(commandBox,0,3);
    }

//    public void mainPage() {
//
//        Text text1 = new Text("Please enter your name");
//        text1.setFill(Color.WHITE);
//        text1.setFont(Font.font("null", 20));
//
//        Text text2 = display1;
//        text2.setFill(Color.WHITE);
//        text2.setFont(Font.font("null", 20));
//
//        //Creating Text Filed for Name
//        TextField textField1 = new TextField();
//        textField1.setBackground(Background.fill(Color.GAINSBORO));
//        textField1.setFont(Font.font("null",15));
//        textField1.setPrefHeight(20);
//
//        //Creating Buttons
//        Button button1 = new Button("Enter");
//
//        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//
//        //Setting size for the pane
//        gridPane.setMaxSize(800, 600);
//
//        //Setting the padding
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//        //Setting the vertical and horizontal gaps between the columns
//        gridPane.setVgap(10);
//        gridPane.setHgap(10);
//
//        //Setting the Grid alignment
//        gridPane.setAlignment(Pos.CENTER);
//
//        //Arranging all the nodes in the grid
//        gridPane.add(reflectionTitle(), 1, 0);
//        gridPane.add(text1, 1, 1);
//        gridPane.add(textField1, 1, 2);
//        gridPane.add(button1, 2, 2);
//        gridPane.add(text2,1,5);
//        getChildren().add(gridPane);
//    }

    public Node reflectionTitle() {
        Text t = new Text("SCP DANGER");
        t.setX(10.0f);
        t.setY(50.0f);
        t.setCache(true);
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 55));

        Reflection r = new Reflection();
        r.setFraction(0.8f);
        t.setEffect(r);
        t.setTranslateY(-70);
        t.setTranslateX(100);
        return t;
    }

    public void updateView(String message) {
        if (!display1.isVisible()){
            inventory.setVisible(false);
            display1.setVisible(true);
        }
        display1.setText(message);
        if (message.contains("LC"))
            display1.setFill(Color.WHITE);
        else if (message.contains("HC"))
            display1.setFill(Color.YELLOW);
        else if (message.contains("EZ"))
            display1.setFill(Color.CYAN);
        else if (message.contains("SZ"))
            display1.setFill(Color.CRIMSON);
        else if (message.contains("inventory") || message.contains("Inventory"))
            display1.setFill(Color.LAWNGREEN);
        else if (message.contains("weapon") || message.contains("~"))
            display1.setFill(Color.ORANGE);
        else display1.setFill(Color.WHITE);
    }

    //Show inventory in 2 columns
    public void updateInventory(String message){
        String[] lines = message.split("\r\n|\r|\n");
        String column1 = lines[0];
        String column2 ="";
        for (int i = 1; i <= 9 ;i++){
            column1 = column1 + "\n" + lines[i];
        }
        for (int i=10; i<lines.length;i++){
            column2 = column2 + "\n" + lines[i];
        }
        invColumn1.setText(column1);
        invColumn2.setText(column2);
        if (display1.isVisible()) {
            display1.setVisible(false);
            inventory.setVisible(true);
        }
    }

    public void enterExMonster(String message){
        //Show monster info
        updateView(message);

        //Set up fightButton
        fightButton.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        fightButton.setTranslateX(-100);
        fightButton.setTranslateY(100);

        //Set up ignoreButton
        ignoreButton.setStyle("-fx-font: 20 arial; -fx-base: #115eee;");
        ignoreButton.setTranslateX(100);
        ignoreButton.setTranslateY(100);

        //Display 2 buttons
        stackPane.getChildren().add(fightButton);
        stackPane.getChildren().add(ignoreButton);

        //Hide commandBox
        gridPane.getChildren().get(2).setVisible(false);
        gridPane.getChildren().get(3).setVisible(false);

    }
    public void exitExMonster(){
        stackPane.getChildren().remove(fightButton);
        stackPane.getChildren().remove(ignoreButton);
        gridPane.getChildren().get(2).setVisible(true);
        gridPane.getChildren().get(3).setVisible(true);
    }

    public void enterFight(){
        //Set up fightBox
        fightBox.setBackground(Background.fill(Color.GAINSBORO));
        fightBox.setMaxWidth(250);
        fightBox.setFont(Font.font("null",15));
        fightBox.setPrefHeight(20);
        fightBox.setTranslateX(140);

        //Replace commandBox with answerBox
        gridPane.getChildren().remove(commandBox);
        gridPane.add(fightBox,0,3);
    }

    public void exitFight(){
        //Replace fightBox with commandBox
        gridPane.getChildren().remove(fightBox);
        gridPane.add(commandBox,0,3);
    }
}
