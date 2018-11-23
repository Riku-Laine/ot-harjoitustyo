/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private YatzyService game;
    private HashMap<Integer, ImageView> eyeImageViews;
    private HBox diceButtons;
    private Label scoreTable;
    private Label playerWithTurn;
    private Label numberOfThrows;

    private Node reDrawGameArea() {

        scoreTable.setText(game.getScoreboard());

        playerWithTurn.setText("Throwing: " + game.getPlayerWithTurn().getName());

        numberOfThrows.setText("Throws used: " + game.getThrowsUsed());
        //diceButtons.getChildren().clear();
//        for(int i =0; i<1;i++){
//            diceButtons.getChildren().add(new ToggleButton(game.getDice(i)+ ""));
//        }
        return null;
    }

    @Override
    public void init() {

        eyeImageViews = new HashMap<>();

        ImageView oneEye = new ImageView(new Image("file:dice-1.png"));
        ImageView twoEye = new ImageView(new Image("file:dice-2.png"));
        ImageView threeEye = new ImageView(new Image("file:dice-3.png"));
        ImageView fourEye = new ImageView(new Image("file:dice-4.png"));
        ImageView fiveEye = new ImageView(new Image("file:dice-5.png"));
        ImageView sixEye = new ImageView(new Image("file:dice-6.png"));

        eyeImageViews.put(1, oneEye);
        eyeImageViews.put(2, twoEye);
        eyeImageViews.put(3, threeEye);
        eyeImageViews.put(4, fourEye);
        eyeImageViews.put(5, fiveEye);
        eyeImageViews.put(6, sixEye);
    }

    @Override
    public void start(Stage window) {
        game = new YatzyService();
        window.setTitle("Play Yatzy!");

        //**************
        // Start Scene
        //**************
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

        startLayout.setHgap(10);
        startLayout.setVgap(10);
        startLayout.setPadding(new Insets(10, 10, 10, 10));
        Scene startScene = new Scene(startLayout);

        Button exitButton = new Button("Exit to start screen");

        //************************
        // one player name scene
        //************************
        Label onePlayerGiveNameLabel = new Label(" Give your name!\n Name can't be empty.");
        TextField onePlayerNameText = new TextField();
        Button onePlayerPlayButton = new Button("Begin");

        VBox onePlayerElements = new VBox();
        onePlayerElements.getChildren().addAll(onePlayerGiveNameLabel,
                onePlayerNameText, onePlayerPlayButton, exitButton);

        onePlayerElements.setSpacing(10);
        onePlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene onePlayerBeginScene = new Scene(onePlayerElements);

        //***********************
        // two player name scene
        //***********************
        Label twoPlayerGiveNameLabel = new Label(" Give your names!\n Names can't be empty.");
        TextField twoPlayerNameOneText = new TextField();
        TextField twoPlayerNameTwoText = new TextField();
        Button twoPlayerPlayButton = new Button("Begin");

        VBox twoPlayerElements = new VBox();
        twoPlayerElements.getChildren().addAll(twoPlayerGiveNameLabel,
                twoPlayerNameOneText, twoPlayerNameTwoText, twoPlayerPlayButton, exitButton);

        twoPlayerElements.setSpacing(10);
        twoPlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene twoPlayerBeginScene = new Scene(twoPlayerElements);

        //**************
        // Game scene
        //**************
        Label title = new Label("YATZY!");
        playerWithTurn = new Label(null);
        numberOfThrows = new Label(null);
        ToggleButton dice1 = new ToggleButton("-");
        ToggleButton dice2 = new ToggleButton("-");
        ToggleButton dice3 = new ToggleButton("-");
        ToggleButton dice4 = new ToggleButton("-");
        ToggleButton dice5 = new ToggleButton("-");
        Button throwSelectedButton = new Button("Throw selected!");
        Button throwAllButton = new Button("Throw all!");
        
        diceButtons = new HBox();
        diceButtons.getChildren().addAll(dice1, dice2, dice3, dice4, dice5);

        title.setFont(Font.font(20));
        
        VBox combinationButtons = new VBox();
        combinationButtons.setSpacing(5);
        combinationButtons.setPadding(new Insets(10, 10, 10, 10));
        for(int i = 0; i<16; i++){
            ToggleButton btn = new ToggleButton("Combination " + i);
            combinationButtons.getChildren().add(btn);
        }
        
        scoreTable = new Label();
        scoreTable.setFont(Font.font("Monospaced"));

        GridPane gameButtonArea = new GridPane();

        gameButtonArea.add(title, 0, 0);
        gameButtonArea.add(playerWithTurn, 0, 1);
        gameButtonArea.add(diceButtons, 0, 2);
        gameButtonArea.add(throwSelectedButton, 0, 3);
        gameButtonArea.add(throwAllButton, 0, 4);
        gameButtonArea.add(numberOfThrows, 0, 5);

        gameButtonArea.setHgap(10);
        gameButtonArea.setVgap(10);
        gameButtonArea.setPadding(new Insets(10, 10, 10, 10));

        BorderPane onePlayerScene = new BorderPane();
        onePlayerScene.setLeft(gameButtonArea);
        onePlayerScene.setCenter(combinationButtons);
        onePlayerScene.setRight(scoreTable);
        onePlayerScene.setBottom(exitButton);
        onePlayerScene.setPadding(new Insets(10, 10, 10, 10));

        Scene gameScene = new Scene(onePlayerScene);

        //**************
        //Button events
        //**************
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
        // TODO: for N players
        onePlayerPlayButton.setOnAction((event) -> {
            // Ei asserttaa tyhjää nimeä
            String name = onePlayerNameText.getText();
            if (!name.isEmpty()) {
                game.addPlayer(name);
                scoreTable.setText(game.getScoreboard());
                playerWithTurn.setText(name);
                window.setScene(gameScene);
            }

        });

        twoPlayerPlayButton.setOnAction((event) -> {
            if (!twoPlayerNameOneText.getText().isEmpty() & !twoPlayerNameTwoText.getText().isEmpty()) {
                game.addPlayer(twoPlayerNameOneText.getText());
                game.addPlayer(twoPlayerNameTwoText.getText());
                scoreTable.setText(game.getScoreboard());
                playerWithTurn.setText(twoPlayerNameOneText.getText());
                window.setScene(gameScene);
            }

        });

        throwSelectedButton.setOnAction((event) -> {

            //diceButtons.getChildren().stream().
            boolean[] selected = {dice1.isSelected(), dice2.isSelected(),
                dice3.isSelected(), dice4.isSelected(), dice5.isSelected()};

            if (!areAllFalse(selected)) {
                
                game.throwDies(selected);

                reDrawGameArea();
                dice1.setSelected(false);
                dice2.setSelected(false);
                dice3.setSelected(false);
                dice4.setSelected(false);
                dice5.setSelected(false);
                dice1.setText(game.getDice(0) + "");
                dice2.setText(game.getDice(1) + "");
                dice3.setText(game.getDice(2) + "");
                dice4.setText(game.getDice(3) + "");
                dice5.setText(game.getDice(4) + "");
                
                if (game.getThrowsUsed() == 3) {
                    throwSelectedButton.setDisable(true);
                    throwAllButton.setDisable(true);
                }
                
                window.setScene(gameScene);
            }
        });

        // TODOremove this copypaste
        throwAllButton.setOnAction((event) -> {

            //diceButtons.getChildren().stream().
            boolean[] selected = {true, true, true, true, true};

            if (!areAllFalse(selected)) {
                
                game.throwDies(selected);

                reDrawGameArea();
                dice1.setSelected(false);
                dice2.setSelected(false);
                dice3.setSelected(false);
                dice4.setSelected(false);
                dice5.setSelected(false);
                dice1.setText(game.getDice(0) + "");
                dice2.setText(game.getDice(1) + "");
                dice3.setText(game.getDice(2) + "");
                dice4.setText(game.getDice(3) + "");
                dice5.setText(game.getDice(4) + "");
                
                if (game.getThrowsUsed() == 3) {
                    throwSelectedButton.setDisable(true);
                    throwAllButton.setDisable(true);
                }
                
                window.setScene(gameScene);
            }
        });
        
        exitButton.setOnAction((event) -> {
            game.getPlayers().clear();
            game.setThrowsUsed(0);
            window.setScene(startScene);
        });

        window.setScene(startScene);
        window.show();
    }

    public static boolean areAllFalse(boolean[] array) {
        for (boolean b : array) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(YatzyUi.class);
    }

}
