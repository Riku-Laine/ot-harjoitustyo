/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.ui;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import yatzy.domain.YatzyService;

/**
 *
 * @author Riku_L
 */
public class YatzyUi extends Application {

    public static void main(String[] args) {
        launch(YatzyUi.class);
    }

    @Override
    public void start(Stage window) {

        YatzyService game = new YatzyService();

        window.setTitle("Play Yatzy!");

        // Start Scene
        Button singlePlayerButton = new Button("1 player ");
        Button twoPlayerButton = new Button("2 players");
        Label adminLabel = new Label("Log in as administrator:");
        Label passwordLabel = new Label("Password: ");
        TextField passwordText = new TextField();
        Button logInAsAdminButton = new Button("Log in");

        singlePlayerButton.setFont(Font.font("Monospaced", 20));
        twoPlayerButton.setFont(Font.font("Monospaced", 20));

        GridPane startLayout = new GridPane();

        startLayout.add(singlePlayerButton, 1, 0);
        startLayout.add(twoPlayerButton, 1, 1);
        startLayout.add(adminLabel, 0, 2);
        startLayout.add(passwordLabel, 0, 3);
        startLayout.add(passwordText, 1, 3);
        startLayout.add(logInAsAdminButton, 0, 4);

        Scene startScene = new Scene(startLayout);

        Button exitButton = new Button("Exit to start screen");

        // one player name scene
        Label onePlayerGiveNameLabel = new Label(" Give your name!\n Name can't be empty.");
        TextField onePlayerNameText = new TextField();
        Button onePlayerPlayButton = new Button("Begin");
        
        VBox onePlayerElements = new VBox();
        onePlayerElements.getChildren().addAll(onePlayerGiveNameLabel, 
                onePlayerNameText, onePlayerPlayButton, exitButton);
        
        Scene onePlayerBeginScene = new Scene(onePlayerElements);
        
        // two player name scene
        Label twoPlayerGiveNameLabel = new Label(" Give your names!\n Names can't be empty.");
        TextField twoPlayerNameOneText = new TextField();
        TextField twoPlayerNameTwoText = new TextField();
        Button twoPlayerPlayButton = new Button("Begin");
        
        VBox twoPlayerElements = new VBox();
        twoPlayerElements.getChildren().addAll(twoPlayerGiveNameLabel, 
                twoPlayerNameOneText, twoPlayerNameTwoText, twoPlayerPlayButton, exitButton);
        
        Scene twoPlayerBeginScene = new Scene(twoPlayerElements);
        
        // Game scene
        Label title = new Label("YATZY!");
        Label playerWithTurn = new Label(null);
        Label diceValues = new Label(game.getDiceValues());
        CheckBox dice1 = new CheckBox("Dice 1");
        CheckBox dice2 = new CheckBox("Dice 2");
        CheckBox dice3 = new CheckBox("Dice 3");
        CheckBox dice4 = new CheckBox("Dice 4");
        CheckBox dice5 = new CheckBox("Dice 5");
        Button throwButton = new Button("Throw!");

        HBox diceButtons = new HBox();
        diceButtons.getChildren().addAll(dice1, dice2, dice3, dice4, dice5);

        title.setFont(Font.font(20));

        TableView scoreTable = new TableView();
        scoreTable.setEditable(false);
        TableColumn combinationsCol = new TableColumn("Combinations");
        TableColumn pointsCol = new TableColumn("Points");

        // Create column in the scoretable for each player
        game.getPlayers().forEach((player) -> {
            pointsCol.getColumns().add(new TableColumn(player.getName()));
        });

        scoreTable.getColumns().addAll(combinationsCol, pointsCol);

        // Disable indeterminate choice for dies.
        dice1.setIndeterminate(false);
        dice2.setIndeterminate(false);
        dice3.setIndeterminate(false);
        dice4.setIndeterminate(false);
        dice5.setIndeterminate(false);
        
        GridPane gameButtonArea = new GridPane();

        gameButtonArea.add(title, 0, 0);
        gameButtonArea.add(playerWithTurn, 0, 1);
        gameButtonArea.add(diceValues, 0, 2);
        gameButtonArea.add(diceButtons, 0, 3);
        gameButtonArea.add(throwButton, 0, 4);
        gameButtonArea.add(exitButton, 0, 5);

        BorderPane onePlayerScene = new BorderPane();
        onePlayerScene.setLeft(gameButtonArea);
        onePlayerScene.setRight(scoreTable);

        Scene gameScene = new Scene(onePlayerScene);

        // Button events
        singlePlayerButton.setOnAction((event) -> {
            // Start one player game and move to "game arena"
            window.setScene(onePlayerBeginScene);
            System.out.println("One player game initiated.");
        });

        twoPlayerButton.setOnAction((event) -> {
            // Start two player game and move to "game arena"
            window.setScene(twoPlayerBeginScene);
            System.out.println("Two player game initiated.");
        });

        logInAsAdminButton.setOnAction((event) -> {
            // Show admin window
            if (passwordText.getText().equals("salasana")) {
                System.out.println("Admin logged in");
                // window.setScene(adminScene);
            }
        });
        
        // Add player(s) and set beginning player
        onePlayerPlayButton.setOnAction((event) -> {
            // Ei asserttaa tyhjää nimeä
            String name = onePlayerNameText.getText();
            if(!name.isEmpty()){
                game.addPlayer(name);
                playerWithTurn.setText(name);
                window.setScene(gameScene);
            }

        });
        
        twoPlayerPlayButton.setOnAction((event) -> {
            if(!twoPlayerNameOneText.getText().isEmpty() & !twoPlayerNameTwoText.getText().isEmpty()){
                game.addPlayer(twoPlayerNameOneText.getText());
                game.addPlayer(twoPlayerNameTwoText.getText());
                playerWithTurn.setText(twoPlayerNameOneText.getText());
                window.setScene(gameScene);
            }

        });

        throwButton.setOnAction((event) -> {

            boolean[] selected = {dice1.isSelected(), dice2.isSelected(), 
                dice3.isSelected(), dice4.isSelected(), dice5.isSelected()};

            if(!areAllFalse(selected)){
                dice1.setSelected(false);
                dice2.setSelected(false);
                dice3.setSelected(false);
                dice4.setSelected(false);
                dice5.setSelected(false);
                game.throwDies(selected);
                diceValues.setText(game.getDiceValues());
                scoreTable.refresh();
                window.setScene(gameScene);
            }
        });

        exitButton.setOnAction((event) -> {
            window.setScene(startScene);
        });

        window.setScene(startScene);
        window.show();
    }
    
    public static boolean areAllFalse(boolean[] array){
        for(boolean b : array){
            if(b)
                return false;
        }
        return true;
    }



}
