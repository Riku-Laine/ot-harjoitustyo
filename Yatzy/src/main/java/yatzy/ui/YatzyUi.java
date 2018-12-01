/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.ui;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
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
    private Label scoreTable;
    private Label recordTable;
    private Label playerWithTurn;
    private Label numberOfThrows;
    private ArrayList<ToggleButton> diceButtonList;
    private ArrayList<Button> combinationButtonList;

    /**
     * Redraw game area: update scoreboard, recordboard, name of player throwing
     * and number of throws used. Additionally if the first throw of the turn is
     * not yet thrown, disable combination selection buttons.
     */
    private void reDrawGameArea() {

        // Update scoreboard text.
        scoreTable.setText(game.getScoreboard());

        // Update records.
        recordTable.setText(game.getRecordboard());

        // Update text for player in turn.
        if (game.getPlayerWithTurn() != null) {
            playerWithTurn.setText("Throwing: " + game.getPlayerWithTurn().getName());
        }

        // Update text for throws used
        numberOfThrows.setText("Throws used: " + game.getThrowsUsed());

        for (int i = 0; i < diceButtonList.size(); i++) {
            diceButtonList.get(i).setSelected(false);
            diceButtonList.get(i).setText(game.getDies()[i] + "");
        }

        // If first throw not thrown, disable combination selection buttons.
        if (game.getThrowsUsed() == 0) {
            this.combinationButtonList.stream().forEach(btn -> {
                btn.setDisable(true);
            });
        } else {
            this.combinationButtonList.stream().forEach(btn -> {
                btn.setDisable(false);
            });
        }
    }

    @Override
    public void init() throws SQLException, ClassNotFoundException {

        eyeImageViews = new HashMap<>();
        diceButtonList = new ArrayList<>();
        combinationButtonList = new ArrayList<>();
        game = new YatzyService();

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
    public void start(Stage window) throws SQLException {

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

        Button exitWithoutSaveButton = new Button("Exit to start screen");
        Button saveAndExitButton = new Button("Save score and exit");
        //************************
        // One player name scene
        //************************
        Label onePlayerGiveNameLabel = new Label(" Give your name!\n Name can't be empty.");
        TextField onePlayerNameText = new TextField();
        Button onePlayerPlayButton = new Button("Begin");

        VBox onePlayerElements = new VBox();
        onePlayerElements.getChildren().addAll(onePlayerGiveNameLabel,
                onePlayerNameText, onePlayerPlayButton, exitWithoutSaveButton);

        onePlayerElements.setSpacing(10);
        onePlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene onePlayerBeginScene = new Scene(onePlayerElements);

        //***********************
        // Two player name scene
        //***********************
        Label twoPlayerGiveNameLabel = new Label(" Give your names!\n Names can't be empty.");
        TextField twoPlayerNameOneText = new TextField();
        TextField twoPlayerNameTwoText = new TextField();
        Button twoPlayerPlayButton = new Button("Begin");

        VBox twoPlayerElements = new VBox();
        twoPlayerElements.getChildren().addAll(twoPlayerGiveNameLabel,
                twoPlayerNameOneText, twoPlayerNameTwoText, twoPlayerPlayButton, exitWithoutSaveButton);

        twoPlayerElements.setSpacing(10);
        twoPlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene twoPlayerBeginScene = new Scene(twoPlayerElements);

        //**************
        // Game scene
        //**************
        Label title = new Label("YATZY!");
        playerWithTurn = new Label();
        numberOfThrows = new Label();

        for (int i = 0; i < game.getDies().length; i++) {
            ToggleButton dice = new ToggleButton("0");
            diceButtonList.add(dice);
        }
        Button throwSelectedButton = new Button("Throw selected!");
        throwSelectedButton.setDisable(true);
        Button throwAllButton = new Button("Throw all!");

        HBox diceButtonBox = new HBox();

        diceButtonList.stream().forEach(btn -> diceButtonBox.getChildren().add((ToggleButton) btn));

        title.setFont(Font.font(20));

        VBox combinationButtonsBox = new VBox();
        combinationButtonsBox.setSpacing(5);
        combinationButtonsBox.setPadding(new Insets(10, 10, 10, 10));

        combinationButtonsBox.getChildren().add(new Label("Select combination:"));

        String[] combinations = {"Ones", "Twos", "Threes", "Fours", "Fives",
            "Sixes", "One pair", "Two pairs", "Three of a kind",
            "Four of a kind", "Small straight", "Big straight",
            "Full house", "Chance", "Yatzy"}; //, "Total"};

        for (String combination : combinations) {
            Button btn = new Button(combination);
            btn.setPrefWidth(150);
            combinationButtonsBox.getChildren().add(btn);
            combinationButtonList.add(btn);
            btn.setOnMouseClicked(event -> {
                if (game.getScore(game.getPlayerWithTurn(), combination) == -1) {
                    game.setScore(game.getPlayerWithTurn(), combination);
                    throwSelectedButton.setDisable(true);
                    throwAllButton.setDisable(false);
                    reDrawGameArea();
                }
            });
        }

        scoreTable = new Label();
        scoreTable.setFont(Font.font("Monospaced"));

        recordTable = new Label();
        recordTable.setFont(Font.font("Monospaced"));

        VBox scoresAndRecords = new VBox();
        scoresAndRecords.setSpacing(10);
        scoresAndRecords.getChildren().addAll(scoreTable, recordTable);

        GridPane gameButtonArea = new GridPane();

        gameButtonArea.add(title, 0, 0);
        gameButtonArea.add(playerWithTurn, 0, 1);
        gameButtonArea.add(diceButtonBox, 0, 2);
        gameButtonArea.add(throwSelectedButton, 0, 3);
        gameButtonArea.add(throwAllButton, 0, 4);
        gameButtonArea.add(numberOfThrows, 0, 5);

        gameButtonArea.setHgap(10);
        gameButtonArea.setVgap(10);
        gameButtonArea.setPadding(new Insets(10, 10, 10, 10));

        HBox exitButtons = new HBox();
        exitButtons.setSpacing(10);
        exitButtons.getChildren().addAll(exitWithoutSaveButton, saveAndExitButton);

        BorderPane onePlayerScene = new BorderPane();
        onePlayerScene.setLeft(gameButtonArea);
        onePlayerScene.setCenter(combinationButtonsBox);
        onePlayerScene.setRight(scoresAndRecords);
        onePlayerScene.setBottom(exitButtons);
        onePlayerScene.setPadding(new Insets(10, 10, 10, 10));

        Scene gameScene = new Scene(onePlayerScene);

        //**************
        // Button events
        //**************
        singlePlayerButton.setOnAction((event) -> {
            // Move to one player name giving scene.
            window.setScene(onePlayerBeginScene);
        });

        twoPlayerButton.setOnAction((event) -> {
            // Move to two player name giving scene.
            window.setScene(twoPlayerBeginScene);
        });

        logInAsAdminButton.setOnAction((event) -> {
            // Show admin window
            if (passwordText.getText().equals("salasana")) {
                // window.setScene(adminScene);
            }
        });

        // Add player(s) and set beginning player
        // TODO: for N players
        onePlayerPlayButton.setOnAction((event) -> {
            String name = onePlayerNameText.getText();
            if (!name.isEmpty()) {
                game.addPlayer(name);
                reDrawGameArea();
                window.setScene(gameScene);
            }

        });

        twoPlayerPlayButton.setOnAction((event) -> {
            if (!twoPlayerNameOneText.getText().isEmpty() & !twoPlayerNameTwoText.getText().isEmpty()) {
                game.addPlayer(twoPlayerNameOneText.getText());
                game.addPlayer(twoPlayerNameTwoText.getText());
                reDrawGameArea();
                window.setScene(gameScene);
            }

        });

        throwSelectedButton.setOnAction((event) -> {

            boolean[] selected = new boolean[diceButtonList.size()];
            for (int i = 0; i < diceButtonList.size(); i++) {
                selected[i] = diceButtonList.get(i).isSelected();
            }

            if (!areAllFalse(selected)) {
                game.throwSelectedDies(selected);
                reDrawGameArea();

                if (game.getThrowsUsed() == 3) {
                    throwSelectedButton.setDisable(true);
                    throwAllButton.setDisable(true);
                } else {
                    throwSelectedButton.setDisable(false);
                    throwAllButton.setDisable(false);
                }

                window.setScene(gameScene);
            }
        });

        throwAllButton.setOnAction((event) -> {
            game.throwAllDies();
            reDrawGameArea();

            if (game.getThrowsUsed() == 3) {
                throwSelectedButton.setDisable(true);
                throwAllButton.setDisable(true);
            } else {
                throwSelectedButton.setDisable(false);
                throwAllButton.setDisable(false);
            }

            window.setScene(gameScene);
        });

        exitWithoutSaveButton.setOnAction((event) -> {
            game.reset();
            reDrawGameArea();
            window.setScene(startScene);
        });

        saveAndExitButton.setOnAction((event) -> {
            game.updateRecords();
            game.reset();
            reDrawGameArea();
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
