package com.example.testui.view;

import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Stack;

public class GameView extends Pane {

    //Creating a Grid Pane
    private GridPane gridPane = new GridPane();
    private StackPane stackPane = new StackPane();
    private StackPane mainPane = new StackPane();
    private Text display1 = new Text("");

    private TextField nameBox = new TextField();
    private TextField commandBox = new TextField();
    private TextField answerBox = new TextField();
    private TextField fightBox = new TextField();
    private GridPane inventory = new GridPane();
    private Text invColumn1 = new Text("");
    private Text invColumn2 = new Text("");

    private Button startButton = new Button("New Game");
    private Button loadButton = new Button("Load Game");
    private Button fightButton = new Button("Fight");
    private Button ignoreButton = new Button("Ignore");

    public Text getDisplay1() { return display1; }

    public TextField getNameBox() { return nameBox; }

    public TextField getCommandBox() { return commandBox; }

    public TextField getAnswerBox() { return answerBox; }

    public TextField getFightBox() { return fightBox; }

    public Button getFightButton() { return fightButton; }

    public Button getIgnoreButton() { return ignoreButton; }

    public Button getLoadButton() { return loadButton; }

    public void setDisplay1(Text display1) { this.display1 = display1; }

    public void setCommandBox(TextField commandBox) { this.commandBox = commandBox; }

    public GameView() {
    }

    public void mainPage() {
        //Set background for main page
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:images/Logo.png", 789, 762, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(backgroundImage));

        Rotate rotate = new Rotate();

        //setting properties for the rotate object.
        rotate.setAngle(18);
        rotate.setPivotX(90);
        rotate.setPivotY(100);

        //Set up startButton
        startButton.setStyle("-fx-font: 20 arial; -fx-base: #000000;");
        startButton.setTranslateX(-50);
        startButton.setTranslateY(80);
        startButton.getTransforms().add(rotate);

        //Set up loadButton
        loadButton.setStyle("-fx-font: 20 arial; -fx-base: #000000;");
        loadButton.setTranslateX(100);
        loadButton.setTranslateY(128);
        loadButton.getTransforms().add(rotate);

        //Display 2 buttons
        mainPane.getChildren().add(startButton);
        mainPane.getChildren().add(loadButton);
        mainPane.setMinSize(900,700);
        getChildren().add(mainPane);

        startButton.setOnAction(e -> {
            newGame();
        });
    }

    public void newGame(){
        mainPane.getChildren().remove(startButton);
        mainPane.getChildren().remove(loadButton);
        StackPane subPane = new StackPane();
        subPane.setBackground(Background.fill(Color.DODGERBLUE));
        subPane.setMinSize(280,70);
        subPane.setMaxSize(280,70);

        Text text1 = new Text("Please enter your name");
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //Creating Text Filed for Name
        nameBox.setBackground(Background.fill(Color.WHITE));
        nameBox.setMaxWidth(280);
        nameBox.setFont(Font.font("null",20));
        nameBox.setPrefHeight(20);
        nameBox.setTranslateY(55);
        nameBox.setAlignment(Pos.CENTER);

        subPane.setTranslateY(-100);
        subPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
        subPane.getChildren().addAll(text1,nameBox);
        mainPane.getChildren().add(subPane);
    }

    public void loadGame(){ getChildren().remove(mainPane);
        getChildren().add(gridPane);}

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
        //Hide inventory grid pane, show main screen
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

        //Show inventory grid pane
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

    public void viewGallery() {
        String[] scpList = {"SCP-049", "SCP-049-2", "SCP-079",
                "SCP-096", "SCP-106", "SCP-173", "SCP-714", "SCP-914",
                "SCP-939", "SCP-4051", "COM-15 Sidearm", "Micro H.I.D"};

        // Declare an ImageView array for the SCP
        ImageView[] ImageViews = {
                new ImageView("file:images/SCP-049.PNG"),
                new ImageView("file:images/SCP-049-2.PNG"),
                new ImageView("file:images/SCP-079.png"),
                new ImageView("file:images/SCP-096.PNG"),
                new ImageView("file:images/SCP-106.PNG"),
                new ImageView("file:images/SCP-173.PNG"),
                new ImageView("file:images/SCP-714.PNG"),
                new ImageView("file:images/SCP-914.PNG"),
                new ImageView("file:images/SCP-939.PNG"),
                new ImageView("file:images/SCP-4051.PNG"),
                new ImageView("file:images/COM-15.PNG"),
                new ImageView("file:images/Micro H.I.D.PNG"),
        };

        Stage primaryStage = new Stage();
        ListView<String> lv = new ListView<>
                (FXCollections.observableArrayList(scpList));
        lv.setPrefSize(200, 350);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create a pane to hold image views
        FlowPane imagePane = new FlowPane(10, 10);
        ScrollPane scrollImage = new ScrollPane();
        scrollImage.setFitToWidth(true);
        scrollImage.setFitToHeight(true);
        scrollImage.setContent(imagePane);

        BorderPane pane = new BorderPane();
        pane.setLeft(new ScrollPane(lv));
        pane.setCenter(scrollImage);


        lv.getSelectionModel().selectedItemProperty().addListener(
                ov -> {
                    imagePane.getChildren().clear();
                    for (Integer i: lv.getSelectionModel().getSelectedIndices()) {
                        imagePane.getChildren().add(ImageViews[i]);
                    }
                });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 800,500);
        primaryStage.setTitle("SCP Gallery"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public Node menuTitle(){
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        Text t = new Text();
        t.setEffect(is);
        t.setX(20);
        t.setY(100);
        t.setText("SCP DANGER HELP");
        t.setFill(Color.YELLOW);
        t.setFont(Font.font(null, FontWeight.BOLD, 50));

        return t;
    }

    public void viewHelp(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Help Menu");

        // Create title label
        Label titleLabel = new Label("SCP DANGER HELP MENU");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-font-weight: bold;");

        // Create labels for shortcuts and commands
        Label shortcutsLabel = new Label("Commands");
        shortcutsLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20;");
        Label commandsLabel = new Label("Description");
        commandsLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20;");

        // Create Label to display commands
        Label label1 = new Label("North / N");
        Label label2 = new Label("East / E");
        Label label3 = new Label("South / S");
        Label label4 = new Label("West / W");
        Label label5 = new Label("Location / L");
        Label label6 = new Label("Info");
        Label label7 = new Label("Stats");
        Label label8 = new Label("Pickup + ItemID");
        Label label9 = new Label("Drop + ItemID");
        Label label10 = new Label("Inspect + ItemID");
        Label label11 = new Label("Inventory / I");
        Label label12 = new Label("Equip + ItemID");
        Label label13 = new Label("Unequip + ItemID");
        Label label14 = new Label("Use + ItemID");
        Label label15 = new Label("Weapon");
        Label label16 = new Label("Combine");
        Label label17 = new Label("Solve");
        Label label18 = new Label("Ex monster");
        Label label19 = new Label("Help");
        Label label20 = new Label("Gallery");
        Label label21 = new Label("Save");
        Label label22 = new Label("Load");
        Label label23 = new Label("Exit");

        Label dlabel1 = new Label("Move North");
        Label dlabel2 = new Label("Move East");
        Label dlabel3 = new Label("Move South");
        Label dlabel4 = new Label("Move West");
        Label dlabel5 = new Label("Display current room description");
        Label dlabel6 = new Label("Display player's name and HP");
        Label dlabel7 = new Label("Display items that you have equipped");
        Label dlabel8 = new Label("Pickup an item in a room");
        Label dlabel9 = new Label("Drop an item to a room");
        Label dlabel10 = new Label("Display item's description");
        Label dlabel11 = new Label("Display all items in the inventory");
        Label dlabel12 = new Label("Equip an item");
        Label dlabel13 = new Label("Unequip an item");
        Label dlabel14 = new Label("Use an item (consumable/key)");
        Label dlabel15 = new Label("Display all weapons in the inventory");
        Label dlabel16 = new Label("Combine 2 key cards");
        Label dlabel17 = new Label("Start solving a puzzle");
        Label dlabel18 = new Label("Inspect a monster in a room");
        Label dlabel19 = new Label("Display help menu");
        Label dlabel20 = new Label("View all creatures' images");
        Label dlabel21 = new Label("Save game to a text file");
        Label dlabel22 = new Label("Load a saved game from text file");
        Label dlabel23 = new Label("Quit the game");

        label1.setTextFill(Color.WHITE);
        label1.setFont(new Font("null", 15));

        dlabel1.setTextFill(Color.WHITE);
        dlabel1.setFont(new Font("null", 15));

        label2.setTextFill(Color.WHITE);
        label2.setFont(new Font("null", 15));

        dlabel2.setTextFill(Color.WHITE);
        dlabel2.setFont(new Font("null", 15));

        label3.setTextFill(Color.WHITE);
        label3.setFont(new Font("null", 15));

        dlabel3.setTextFill(Color.WHITE);
        dlabel3.setFont(new Font("null", 15));

        label4.setTextFill(Color.WHITE);
        label4.setFont(new Font("null", 15));

        dlabel4.setTextFill(Color.WHITE);
        dlabel4.setFont(new Font("null", 15));

        label5.setTextFill(Color.WHITE);
        label5.setFont(new Font("null", 15));

        dlabel5.setTextFill(Color.WHITE);
        dlabel5.setFont(new Font("null", 15));

        label6.setTextFill(Color.WHITE);
        label6.setFont(new Font("null", 15));

        dlabel6.setTextFill(Color.WHITE);
        dlabel6.setFont(new Font("null", 15));

        label7.setTextFill(Color.WHITE);
        label7.setFont(new Font("null", 15));

        dlabel7.setTextFill(Color.WHITE);
        dlabel7.setFont(new Font("null", 15));

        label8.setTextFill(Color.WHITE);
        label8.setFont(new Font("null", 15));

        dlabel8.setTextFill(Color.WHITE);
        dlabel8.setFont(new Font("null", 15));

        label9.setTextFill(Color.WHITE);
        label9.setFont(new Font("null", 15));

        dlabel9.setTextFill(Color.WHITE);
        dlabel9.setFont(new Font("null", 15));

        label10.setTextFill(Color.WHITE);
        label10.setFont(new Font("null", 15));

        dlabel10.setTextFill(Color.WHITE);
        dlabel10.setFont(new Font("null", 15));

        label11.setTextFill(Color.WHITE);
        label11.setFont(new Font("null", 15));

        dlabel11.setTextFill(Color.WHITE);
        dlabel11.setFont(new Font("null", 15));

        label12.setTextFill(Color.WHITE);
        label12.setFont(new Font("null", 15));

        dlabel12.setTextFill(Color.WHITE);
        dlabel12.setFont(new Font("null", 15));

        label13.setTextFill(Color.WHITE);
        label13.setFont(new Font("null", 15));

        dlabel13.setTextFill(Color.WHITE);
        dlabel13.setFont(new Font("null", 15));

        label14.setTextFill(Color.WHITE);
        label14.setFont(new Font("null", 15));

        dlabel14.setTextFill(Color.WHITE);
        dlabel14.setFont(new Font("null", 15));

        label15.setTextFill(Color.WHITE);
        label15.setFont(new Font("null", 15));

        dlabel15.setTextFill(Color.WHITE);
        dlabel15.setFont(new Font("null", 15));

        label16.setTextFill(Color.WHITE);
        label16.setFont(new Font("null", 15));

        dlabel16.setTextFill(Color.WHITE);
        dlabel16.setFont(new Font("null", 15));

        label17.setTextFill(Color.WHITE);
        label17.setFont(new Font("null", 15));

        dlabel17.setTextFill(Color.WHITE);
        dlabel17.setFont(new Font("null", 15));

        label18.setTextFill(Color.WHITE);
        label18.setFont(new Font("null", 15));

        dlabel18.setTextFill(Color.WHITE);
        dlabel18.setFont(new Font("null", 15));

        label19.setTextFill(Color.WHITE);
        label19.setFont(new Font("null", 15));

        dlabel19.setTextFill(Color.WHITE);
        dlabel19.setFont(new Font("null", 15));

        label20.setTextFill(Color.WHITE);
        label20.setFont(new Font("null", 15));

        dlabel20.setTextFill(Color.WHITE);
        dlabel20.setFont(new Font("null", 15));

        label21.setTextFill(Color.WHITE);
        label21.setFont(new Font("null", 15));

        dlabel21.setTextFill(Color.WHITE);
        dlabel21.setFont(new Font("null", 15));

        label22.setTextFill(Color.WHITE);
        label22.setFont(new Font("null", 15));

        dlabel22.setTextFill(Color.WHITE);
        dlabel22.setFont(new Font("null", 15));

        label23.setTextFill(Color.WHITE);
        label23.setFont(new Font("null", 15));

        dlabel23.setTextFill(Color.WHITE);
        dlabel23.setFont(new Font("null", 15));

        // Create GridPane to organize labels into two columns
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        // Set background color to black
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);
        gridPane.setHgap(100);

        // Add labels to the GridPane
        gridPane.add(menuTitle(), 0, 0, 2, 1);
        gridPane.add(shortcutsLabel, 0, 1);
        gridPane.add(commandsLabel, 1, 1);

        gridPane.add(label1, 0, 2);
        gridPane.add(dlabel1, 1, 2);

        gridPane.add(label2, 0, 3);
        gridPane.add(dlabel2, 1, 3);

        gridPane.add(label3, 0, 4);
        gridPane.add(dlabel3, 1, 4);

        gridPane.add(label4, 0, 5);
        gridPane.add(dlabel4, 1, 5);

        gridPane.add(label5, 0, 6);
        gridPane.add(dlabel5, 1, 6);

        gridPane.add(label6, 0, 7);
        gridPane.add(dlabel6, 1, 7);

        gridPane.add(label7, 0, 8);
        gridPane.add(dlabel7, 1, 8);

        gridPane.add(label8, 0, 9);
        gridPane.add(dlabel8, 1, 9);

        gridPane.add(label9, 0, 10);
        gridPane.add(dlabel9, 1, 10);

        gridPane.add(label10, 0, 11);
        gridPane.add(dlabel10, 1, 11);

        gridPane.add(label11, 0, 12);
        gridPane.add(dlabel11, 1, 12);

        gridPane.add(label12, 0, 13);
        gridPane.add(dlabel12, 1, 13);

        gridPane.add(label13, 0, 14);
        gridPane.add(dlabel13, 1, 14);

        gridPane.add(label14, 0, 15);
        gridPane.add(dlabel14, 1, 15);

        gridPane.add(label15, 0, 16);
        gridPane.add(dlabel15, 1, 16);

        gridPane.add(label16, 0, 17);
        gridPane.add(dlabel16, 1, 17);

        gridPane.add(label17, 0, 18);
        gridPane.add(dlabel17, 1, 18);

        gridPane.add(label18, 0, 19);
        gridPane.add(dlabel18, 1, 19);

        gridPane.add(label19, 0, 20);
        gridPane.add(dlabel19, 1, 20);

        gridPane.add(label20, 0, 21);
        gridPane.add(dlabel20, 1, 21);

        gridPane.add(label21, 0, 22);
        gridPane.add(dlabel21, 1, 22);

        gridPane.add(label22, 0, 23);
        gridPane.add(dlabel22, 1, 23);

        gridPane.add(label23, 0, 24);
        gridPane.add(dlabel23, 1, 24);

        // Create layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(gridPane);
        vbox.setBackground(Background.fill(Color.BLACK));

        // Create Scene
        Scene scene = new Scene(vbox,550,750);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
