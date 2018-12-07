package yatzy.ui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
 * Class responsible for updating the user interface.
 *
 * @author Riku_L
 */
public class YatzyUi extends Application {

    private YatzyService game;
    private HashMap<Integer, String> eyeImageURLs;
    private Label scoreTable;
    private Label highScoreTable;
    private Label playerWithTurn;
    private Label numberOfThrows;
    private ArrayList<ToggleButton> diceButtonList;
    private ArrayList<Button> combinationButtonList;
    private VBox combinationButtonsBox;
    private Button throwSelectedButton;
    private Button throwAllButton;
    private TextField adminToRemoveFromHighScoresField;
    private Label adminHighScoreTable;

    /**
     * Redraw game area: update scoreboard, recordboard, name of player throwing
     * and number of throws used. Additionally if the first throw of the turn is
     * not yet thrown, disable combination selection buttons.
     */
    private void redrawGameArea() {

        // Update scoreboard text.
        scoreTable.setText(game.getScoreboard());

        // Update high scores.
        highScoreTable.setText(game.getHighScores());

        // Update text for player in turn.
        if (game.getPlayerWithTurn() != null) {
            playerWithTurn.setText("Throwing: " + game.getPlayerWithTurn().getName());
        }

        // Update text for throws used
        numberOfThrows.setText("Throws used: " + game.getThrowsUsed());

        redrawDiceButtons();

        redrawThrowButtons();

        redrawCombinationButtons();

    }

    /**
     * Redraw combination buttons according to combinations in the scorecard of
     * the player in turn. If a combination has been selected for the player or
     * no throws have been thrown, button is disabled.
     */
    private void redrawCombinationButtons() {

        combinationButtonsBox.getChildren().clear();
        combinationButtonList.clear();
        combinationButtonsBox.getChildren().add(new Label("Select combination:"));

        ArrayList<String> combinations = game.getPlayerWithTurn().getScorecard().getCombinations();

        for (int i = 0; i < combinations.size(); i++) {
            String combination = combinations.get(i);
            if (combination.equals("Total")) {
                break;
            }
            Button btn = new Button(combination);
            btn.setPrefWidth(150);
            combinationButtonsBox.getChildren().add(btn);
            combinationButtonList.add(btn);
            if (game.getScore(game.getPlayerWithTurn(), combination) != -1
                    || game.getThrowsUsed() == 0) {
                btn.setDisable(true);
            }
            btn.setOnMouseClicked(event -> {
                game.setScoreAndChangeTurn(game.getPlayerWithTurn(), combination);
                redrawThrowButtons();
                redrawGameArea();
            });
        }
    }

    /**
     * Enable or disable throw buttons according to the number of throws left.
     */
    private void redrawThrowButtons() {
        int throwsUsed = game.getThrowsUsed();
        if (throwsUsed == 0) {
            throwSelectedButton.setDisable(true);
            throwAllButton.setDisable(false);
        } else if (throwsUsed == 3) {
            throwSelectedButton.setDisable(true);
            throwAllButton.setDisable(true);
        } else {
            throwSelectedButton.setDisable(false);
            throwAllButton.setDisable(false);
        }

    }

    /**
     * Redraw dice buttons.
     */
    private void redrawDiceButtons() {
        // Unselect the dice buttons and update their text.
        for (int i = 0; i < diceButtonList.size(); i++) {
            diceButtonList.get(i).setSelected(false);
            // Get dice values as pictures
            if (game.getDies()[i] != 0) {
                diceButtonList.get(i).setText(null);
                ImageView iv = new ImageView(new Image(eyeImageURLs.get(game.getDies()[i])));
                diceButtonList.get(i).setGraphic(iv);
            }
        }
    }

    /**
     * Redraw fields in the admin window.
     */
    private void reDrawAdminScene() {
        adminToRemoveFromHighScoresField.setText("");
        adminHighScoreTable.setText(game.getHighScores());
    }

    @Override
    public void init() throws ClassNotFoundException {

        eyeImageURLs = new HashMap<>();
        diceButtonList = new ArrayList<>();
        combinationButtonList = new ArrayList<>();
        combinationButtonsBox = new VBox();
        game = new YatzyService();
        highScoreTable = new Label(game.getHighScores());
        highScoreTable.setFont(Font.font("Monospaced"));

        String oneEye = "file:./src/main/resources/dice-1.png";
        String twoEye = "file:./src/main/resources/dice-2.png";
        String threeEye = "file:./src/main/resources/dice-3.png";
        String fourEye = "file:./src/main/resources/dice-4.png";
        String fiveEye = "file:./src/main/resources/dice-5.png";
        String sixEye = "file:./src/main/resources/dice-6.png";

        eyeImageURLs.put(1, oneEye);
        eyeImageURLs.put(2, twoEye);
        eyeImageURLs.put(3, threeEye);
        eyeImageURLs.put(4, fourEye);
        eyeImageURLs.put(5, fiveEye);
        eyeImageURLs.put(6, sixEye);
    }

    @Override
    public void start(Stage window) {

        window.setTitle("Play Yatzy!");

        //**************
        // Start Scene
        //**************
        Button singlePlayerButton = new Button("1 player ");
        Button twoPlayerButton = new Button("2 players");
        Label adminLabel = new Label("Log in as administrator:");
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordText = new PasswordField();
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

        Button saveAndExitButton = new Button("Save score and exit");

        //************************
        // Admin scene
        //************************
        Label adminInstructionLabel = new Label("Give name of the player you \n"
                + "wish to remove from the scoreboard!");
        adminToRemoveFromHighScoresField = new TextField("Player name");
        Button adminRemoveGivenNameButton = new Button("Remove player!");
        Button adminRemoveAllButton = new Button("Remove all!");
        Button adminExitButton = new Button("Exit to start screen");

        HBox removeButtons = new HBox();
        removeButtons.getChildren().addAll(adminRemoveGivenNameButton, adminRemoveAllButton);
        removeButtons.setSpacing(10);

        VBox adminElements = new VBox();
        adminElements.getChildren().addAll(adminInstructionLabel,
                adminToRemoveFromHighScoresField, removeButtons, adminExitButton);

        adminElements.setSpacing(10);
        adminElements.setPadding(new Insets(10, 10, 10, 10));

        BorderPane adminPane = new BorderPane();

        adminPane.setPadding(new Insets(10, 10, 10, 10));

        adminHighScoreTable = new Label(game.getHighScores());
        adminHighScoreTable.setFont(Font.font("Monospaced"));

        adminPane.setLeft(adminElements);
        adminPane.setRight(adminHighScoreTable);

        Scene adminScene = new Scene(adminPane);

        //************************
        // One player name scene
        //************************
        Label onePlayerGiveNameLabel = new Label("Give your name!");
        TextField onePlayerNameText = new TextField("Player 1");
        Button onePlayerPlayButton = new Button("Begin");
        Button onePlayerExitButton = new Button("Exit to start screen");

        VBox onePlayerElements = new VBox();
        onePlayerElements.getChildren().addAll(onePlayerGiveNameLabel,
                onePlayerNameText, onePlayerPlayButton, onePlayerExitButton);

        onePlayerElements.setSpacing(10);
        onePlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene onePlayerBeginScene = new Scene(onePlayerElements);

        //***********************
        // Two player name scene
        //***********************
        Label twoPlayerGiveNameLabel = new Label(" Give your names!\n "
                + "Names can't be empty.\n Player 1 begins.");
        TextField twoPlayerNameOneText = new TextField("Player 1");
        TextField twoPlayerNameTwoText = new TextField("Player 2");
        Button twoPlayerPlayButton = new Button("Begin");
        Button twoPlayerExitButton = new Button("Exit to start screen");

        VBox twoPlayerElements = new VBox();
        twoPlayerElements.getChildren().addAll(twoPlayerGiveNameLabel,
                twoPlayerNameOneText, twoPlayerNameTwoText, twoPlayerPlayButton,
                twoPlayerExitButton);

        twoPlayerElements.setSpacing(10);
        twoPlayerElements.setPadding(new Insets(10, 10, 10, 10));

        Scene twoPlayerBeginScene = new Scene(twoPlayerElements);

        //**************
        // Game scene
        //**************
        Label title = new Label("YATZY!");
        playerWithTurn = new Label();
        numberOfThrows = new Label();
        Button gameExitWithoutSaveButton = new Button("Exit and reset game");

        for (int i = 0; i < game.getDies().length; i++) {
            ImageView iv = new ImageView(new Image(eyeImageURLs.get(6)));
            ToggleButton dice = new ToggleButton(null, iv);
            diceButtonList.add(dice);
        }
        throwSelectedButton = new Button("Throw selected!");
        throwAllButton = new Button("Throw all!");
        redrawThrowButtons();

        HBox diceButtonsHBox = new HBox();

        diceButtonList.stream().forEach(btn -> {
            diceButtonsHBox.getChildren().add((ToggleButton) btn);
        });

        title.setFont(Font.font(20));

        combinationButtonsBox = new VBox();
        combinationButtonsBox.setSpacing(5);
        combinationButtonsBox.setPadding(new Insets(10, 10, 10, 10));

        scoreTable = new Label();
        scoreTable.setFont(Font.font("Monospaced"));

        VBox scoresAndRecords = new VBox();
        scoresAndRecords.setSpacing(10);
        scoresAndRecords.getChildren().addAll(scoreTable, highScoreTable);

        GridPane gameButtonArea = new GridPane();

        gameButtonArea.add(title, 0, 0);
        gameButtonArea.add(playerWithTurn, 0, 1);
        gameButtonArea.add(diceButtonsHBox, 0, 2);
        gameButtonArea.add(throwSelectedButton, 0, 3);
        gameButtonArea.add(throwAllButton, 0, 4);
        gameButtonArea.add(numberOfThrows, 0, 5);

        gameButtonArea.setHgap(10);
        gameButtonArea.setVgap(10);
        gameButtonArea.setPadding(new Insets(10, 10, 10, 10));

        HBox exitButtons = new HBox();
        exitButtons.setSpacing(10);
        exitButtons.getChildren().addAll(gameExitWithoutSaveButton, saveAndExitButton);

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
                window.setScene(adminScene);
            }
        });

        // Add player(s) and set beginning player
        // TODO: for N players
        onePlayerPlayButton.setOnAction((event) -> {
            String name = onePlayerNameText.getText();
            if (!name.isEmpty()) {
                game.addPlayer(name);
                redrawGameArea();
                window.setScene(gameScene);
            }
        });

        twoPlayerPlayButton.setOnAction((event) -> {
            if (!twoPlayerNameOneText.getText().isEmpty() & !twoPlayerNameTwoText.getText().isEmpty()) {
                game.addPlayer(twoPlayerNameOneText.getText());
                game.addPlayer(twoPlayerNameTwoText.getText());
                redrawGameArea();
                window.setScene(gameScene);
            }
        });

        // Throw the selected dies if there are selections.
        throwSelectedButton.setOnAction((event) -> {
            boolean[] selected = new boolean[diceButtonList.size()];
            for (int i = 0; i < diceButtonList.size(); i++) {
                selected[i] = diceButtonList.get(i).isSelected();
            }
            if (!areAllFalse(selected)) {
                game.throwSelectedDies(selected);
                redrawGameArea();
                window.setScene(gameScene);
            }
        });

        // Throw all dies.
        throwAllButton.setOnAction((event) -> {
            game.throwAllDies();
            redrawGameArea();
            window.setScene(gameScene);
        });

        gameExitWithoutSaveButton.setOnAction((event) -> {
            game.reset();
            window.setScene(startScene);
        });

        adminExitButton.setOnAction((event) -> {
            window.setScene(startScene);
        });

        onePlayerExitButton.setOnAction((event) -> {
            window.setScene(startScene);
        });

        twoPlayerExitButton.setOnAction((event) -> {
            window.setScene(startScene);
        });

        // Save scores to high scores, reset game and exit.
        saveAndExitButton.setOnAction((event) -> {
            game.addPlayersInTheGameToRecords();
            game.reset();
            reDrawAdminScene();
            window.setScene(startScene);
        });

        // Remove record by the given name.
        adminRemoveGivenNameButton.setOnAction((event) -> {
            if (!adminToRemoveFromHighScoresField.getText().isEmpty()) {
                game.removeRecord(adminToRemoveFromHighScoresField.getText());
                reDrawAdminScene();
                window.setScene(adminScene);
            }
        });

        // Remove all records.
        adminRemoveAllButton.setOnAction((event) -> {
            game.removeAllRecords();
            reDrawAdminScene();
            window.setScene(adminScene);
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
