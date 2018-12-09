package yatzy.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import yatzy.domain.Record;
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
    private Scene nameGivingScene;
    private Scene gameScene;
    private Scene startScene;
    private Button beginGameButton;
    private Button nameGivingExit;
    private HBox diceButtonsHBox;
    private VBox adminRemovableRecordsButtonsBox;
    private ArrayList<CheckBox> adminRemovableRecordsCheckBoxList;

    @Override
    public void init() throws ClassNotFoundException {

        eyeImageURLs = new HashMap<>();
        diceButtonList = new ArrayList<>();
        combinationButtonList = new ArrayList<>();
        combinationButtonsBox = new VBox();
        game = new YatzyService();
        highScoreTable = new Label(game.getHighScores());
        highScoreTable.setFont(Font.font("Monospaced"));
        nameGivingScene = new Scene(new BorderPane());
        beginGameButton = new Button("Begin");
        nameGivingExit = new Button("Return to start screen");
        gameScene = new Scene(new BorderPane());
        startScene = new Scene(new BorderPane());
        scoreTable = new Label();
        adminRemovableRecordsButtonsBox = new VBox();
        adminRemovableRecordsButtonsBox.setSpacing(10);
        adminRemovableRecordsCheckBoxList = new ArrayList<>();

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
        Button singlePlayerButton = new Button("1-player quick game");
        Button twoPlayerButton = new Button("2-player quick game");
        Label adminLabel = new Label("Log in as administrator:");
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordText = new PasswordField();
        Button logInAsAdminButton = new Button("Log in");

        Slider numberOfPlayersSlider = createSettingSlider(1, 10, 1, 1, 0);
        Slider numberOfDiesSlider = createSettingSlider(1, 10, 5, 1, 0);
        Slider biggestNumberSlider = createSettingSlider(1, 20, 6, 2, 1);
        Slider maxNumberOfThrowsSlider = createSettingSlider(1, 10, 3, 1, 0);

        ToggleGroup scorecardTypeToggleGroup = new ToggleGroup();
        RadioButton american = createRadioButton("American", "American", scorecardTypeToggleGroup, false);
        RadioButton scandinavian = createRadioButton("Scandinavian", "Scandinavian", scorecardTypeToggleGroup, true);

        Label playerNumberLabel = new Label("Number of players:");
        Label diceNumberLabel = new Label("Number of dies:");
        Label biggestEyeNumberLabel = new Label("Biggest eye number:");
        Label maxThrowNumberLabel = new Label("Number of throws in turn:");
        Label scorecardTypeLabel = new Label("Type of scorecard:");

        Button customGameStartButton = new Button("FUN GAME");

        singlePlayerButton.setFont(Font.font("Monospaced", 20));
        twoPlayerButton.setFont(Font.font("Monospaced", 20));

        GridPane startLayout = new GridPane();

        startLayout.add(singlePlayerButton, 1, 0);
        startLayout.add(twoPlayerButton, 1, 1);
        startLayout.add(adminLabel, 0, 2);
        startLayout.add(passwordLabel, 0, 3);
        startLayout.add(passwordText, 1, 3);
        startLayout.add(logInAsAdminButton, 0, 4);
        startLayout.add(numberOfPlayersSlider, 0, 5);
        startLayout.add(playerNumberLabel, 1, 5);
        startLayout.add(numberOfDiesSlider, 0, 6);
        startLayout.add(diceNumberLabel, 1, 6);
        startLayout.add(biggestNumberSlider, 0, 7);
        startLayout.add(biggestEyeNumberLabel, 1, 7);
        startLayout.add(maxNumberOfThrowsSlider, 0, 8);
        startLayout.add(maxThrowNumberLabel, 1, 8);
        startLayout.add(scorecardTypeLabel, 0, 9);
        startLayout.add(american, 1, 9);
        startLayout.add(scandinavian, 2, 9);
        startLayout.add(customGameStartButton, 0, 10);

        startLayout.setHgap(10);
        startLayout.setVgap(10);
        startLayout.setPadding(new Insets(10, 10, 10, 10));

        startScene = new Scene(startLayout);

        Button saveAndExitButton = new Button("Save score and exit");

        //************************
        // Admin scene
        //************************
        redrawAdminButtons();

        Button adminRemoveSelectedButton = new Button("Remove selected!");
        Button adminRemoveAllButton = new Button("Remove all!");
        Button adminExitButton = new Button("Exit to start screen");

        HBox removeButtons = new HBox();
        removeButtons.getChildren().addAll(adminRemoveAllButton, adminRemoveSelectedButton);
        removeButtons.setSpacing(10);

        VBox adminElements = new VBox();
        adminElements.getChildren().addAll(removeButtons, adminExitButton);

        adminElements.setSpacing(10);
        adminElements.setPadding(new Insets(10, 10, 10, 10));

        BorderPane adminPane = new BorderPane();
        adminPane.setPadding(new Insets(10, 10, 10, 10));

        adminPane.setLeft(adminElements);
        adminPane.setRight(adminRemovableRecordsButtonsBox);

        Scene adminScene = new Scene(adminPane);

        //**************
        // Game scene
        //**************
        Label title = new Label("YATZY!");
        title.setFont(Font.font(20));

        playerWithTurn = new Label();
        numberOfThrows = new Label();
        Button gameExitWithoutSaveButton = new Button("Exit and reset game");

        throwSelectedButton = new Button("Throw selected!");
        throwAllButton = new Button("Throw all!");
        redrawThrowButtons();

        diceButtonsHBox = new HBox();

        redrawGameArea(window);

        combinationButtonsBox.setSpacing(5);
        combinationButtonsBox.setPadding(new Insets(10, 10, 10, 10));

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

        BorderPane gameLayout = new BorderPane();
        gameLayout.setLeft(gameButtonArea);
        gameLayout.setCenter(combinationButtonsBox);
        gameLayout.setRight(scoresAndRecords);
        gameLayout.setBottom(exitButtons);
        gameLayout.setPadding(new Insets(10, 10, 10, 10));

        gameScene = new Scene(gameLayout);

        //**************
        // Button events
        //**************
        singlePlayerButton.setOnAction((event) -> {
            // Move to one player name giving scene.
            drawNameGivingScene(1, 5, 6, 3, window, "Scandinavian");
            window.setScene(nameGivingScene);
        });

        twoPlayerButton.setOnAction((event) -> {
            // Move to two player name giving scene.
            drawNameGivingScene(2, 5, 6, 3, window, "Scandinavian");
            window.setScene(nameGivingScene);
        });

        logInAsAdminButton.setOnAction((event) -> {
            // Show admin window
            if (passwordText.getText().equals("salasana")) {
                redrawAdminButtons();
                window.setScene(adminScene);
                passwordText.setText("");
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
                redrawGameArea(window);
                window.setScene(gameScene);
            }
        });

        // Throw all dies.
        throwAllButton.setOnAction((event) -> {
            game.throwAllDies();
            redrawGameArea(window);
            window.setScene(gameScene);
        });

        gameExitWithoutSaveButton.setOnAction((event) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit game");
            alert.setHeaderText("You are exiting!");
            alert.setContentText("All progress will be lost. Continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                game.reset();
                window.setScene(startScene);
            }

        });

        adminExitButton.setOnAction((event) -> {
            window.setScene(startScene);
        });

        // Save scores to high scores, reset game and exit.
        saveAndExitButton.setOnAction((event) -> {
            game.addPlayersInTheGameToRecords();
            game.reset();
            redrawAdminButtons();
            window.setScene(startScene);
        });

        // Remove all records.
        adminRemoveAllButton.setOnAction((event) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Remove records");
            alert.setHeaderText("You are removing all records!");
            alert.setContentText("You are about to remove all records from the database. "
                    + "This action is irreversible. Confirm to proceed.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                game.removeAllRecords();
            }

            redrawAdminButtons();
            window.setScene(adminScene);
        });

        // Remove selected records.
        adminRemoveSelectedButton.setOnAction((event) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Remove records");
            alert.setHeaderText("You are removing records!");
            alert.setContentText("You are about to remove records from the database. "
                    + "This action is irreversible. Confirm to proceed.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                for (CheckBox box : adminRemovableRecordsCheckBoxList) {
                    if (box.isSelected()) {
                        game.removeRecord((Record) box.getUserData());
                    }
                }
            }

            redrawAdminButtons();
            window.setScene(adminScene);
        });

        customGameStartButton.setOnAction((event) -> {
            drawNameGivingScene((int) numberOfPlayersSlider.getValue(),
                    (int) numberOfDiesSlider.getValue(),
                    (int) biggestNumberSlider.getValue(),
                    (int) maxNumberOfThrowsSlider.getValue(),
                    window,
                    (String) scorecardTypeToggleGroup.getSelectedToggle().getUserData());
            window.setScene(nameGivingScene);
        });

        window.setScene(startScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(YatzyUi.class);
    }

    /**
     * Redraw game area: update scoreboard, recordboard, name of player throwing
     * and number of throws used. Additionally if the first throw of the turn is
     * not yet thrown, disable combination selection buttons.
     */
    private void redrawGameArea(Stage window) {

        // Update scoreboard text.
        scoreTable.setText(game.getScoreboard());

        // Update high scores.
        highScoreTable.setText(game.getHighScores());

        // Update text for player in turn.
        if (game.getPlayerWithTurn() != null) {
            playerWithTurn.setText("Throwing: " + game.getPlayerWithTurn().getName());
        }

        // Update text for throws used
        numberOfThrows.setText("Throws used: " + game.getThrowsUsed() + " out of " + game.getMaxThrows());

        redrawDiceButtons();

        redrawThrowButtons();

        redrawCombinationButtons(window);

    }

    /**
     * Redraw combination buttons according to combinations in the scorecard of
     * the player in turn. If a combination has been selected for the player or
     * no throws have been thrown, button is disabled.
     */
    private void redrawCombinationButtons(Stage window) {
        // Reset boxes and lists.
        combinationButtonsBox.getChildren().clear();
        combinationButtonList.clear();
        combinationButtonsBox.getChildren().add(new Label("Select combination:"));

        if (game.getPlayerWithTurn() == null) {
            return;
        }
        ArrayList<String> combinations = game.getPlayerWithTurn().getScorecard().getCombinations();

        for (int i = 0; i < combinations.size(); i++) {
            String combination = combinations.get(i);
            if (combination.equals("Total") || combination.contains("onus")) {
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
                redrawGameArea(window);
                if (game.hasEnded()) {
                    showEndGamePrompt(window);
                }
            });
        }
    }

    /**
     * Redraw throw buttons according to the number of throws left.
     */
    private void redrawThrowButtons() {
        int throwsUsed = game.getThrowsUsed();
        if (throwsUsed == 0) {
            throwSelectedButton.setDisable(true);
            throwAllButton.setDisable(false);
        } else if (throwsUsed == game.getMaxThrows()) {
            throwSelectedButton.setDisable(true);
            throwAllButton.setDisable(true);
        } else {
            throwSelectedButton.setDisable(false);
            throwAllButton.setDisable(false);
        }
    }

    /**
     * Redraw dice buttons. If maximum eye number is 6 or less, use dice
     * pictures, otherwise use text. As default, images are six-eyed dice and
     * text representations are with hyphen '-'.
     */
    private void redrawDiceButtons() {
        // Unselect the dice buttons and update their pictures.
        diceButtonList.clear();
        diceButtonsHBox.getChildren().clear();

        for (int i = 0; i < game.getDies().length; i++) {
            ToggleButton button = new ToggleButton();
            button.setSelected(false);

            if (game.getDies()[i] != 0 & game.getMaxNumber() <= 6) {
                button.setText(null);
                ImageView iv = new ImageView(new Image(eyeImageURLs.get(game.getDies()[i])));
                button.setGraphic(iv);
            } else if (game.getDies()[i] != 0) {
                button.setText(game.getDies()[i] + "");
            } else if (game.getMaxNumber() <= 6) {
                button.setText(null);
                ImageView iv = new ImageView(new Image(eyeImageURLs.get(6)));
                button.setGraphic(iv);
            } else {
                button.setText("-");
            }
            diceButtonList.add(button);
            diceButtonsHBox.getChildren().add(button);
        }
    }

    /**
     * Redraw buttons in the admin window.
     */
    private void redrawAdminButtons() {

        adminRemovableRecordsButtonsBox.getChildren().clear();
        adminRemovableRecordsCheckBoxList.clear();
        adminRemovableRecordsButtonsBox.getChildren().add(new Label("Select record(s) to remove:"));

        ArrayList<Record> records = game.getRecords();
        if (records == null) {
            System.out.println("tyj'");
            return;
        }
        for (int i = 0; i < records.size(); i++) {
            String recordString = records.get(i).toString();
            CheckBox box = new CheckBox(recordString);
            adminRemovableRecordsButtonsBox.getChildren().add(box);
            adminRemovableRecordsCheckBoxList.add(box);
            box.setUserData(records.get(i));
        }
    }

    /**
     * Draw name giving scene.
     *
     * @param numberOfPlayers Number of players: parameter to pass to the
     * initiated game.
     * @param numberOfDies Number of dies: parameter to pass to the initiated
     * game.
     * @param biggestEyeNumber Biggest eye number achievable with a dice:
     * parameter to pass to the initiated game.
     * @param maxNumberOfThrows Number of throws available in a turn: parameter
     * to pass to the initiated game.
     * @param window
     * @param typeOfScorecard Type of Scorecard: parameter to pass to the
     * initiated game.
     */
    private void drawNameGivingScene(int numberOfPlayers, int numberOfDies, int biggestEyeNumber, int maxNumberOfThrows, Stage window, String typeOfScorecard) {
        Label instruction = new Label("Give your names! \nNames can't be empty. \nChoose beginning player.");
        GridPane nameGivingLayout = new GridPane();
        nameGivingLayout.add(instruction, 0, 0);
        ToggleGroup beginnerSelection = new ToggleGroup();
        ArrayList<TextField> nameFields = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            TextField playerName = new TextField("Player " + (i + 1));
            nameFields.add(playerName);
            nameGivingLayout.add(playerName, 0, i + 1);
            RadioButton beginnerSelectionButton = createRadioButton("", i, beginnerSelection, false);
            if (i == 0) {
                beginnerSelectionButton.setSelected(true);
            }
            nameGivingLayout.add(beginnerSelectionButton, 1, i + 1);
        }

        nameGivingLayout.setHgap(10);
        nameGivingLayout.setVgap(10);
        nameGivingLayout.setPadding(new Insets(10, 10, 10, 10));

        beginGameButton.setOnAction((event) -> {
            // All fields must have content.
            if (nameFields.stream().noneMatch((field) -> (field.getText().isEmpty()))) {
                try {
                    game = new YatzyService(numberOfDies, biggestEyeNumber, maxNumberOfThrows);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Couldn't create new game!");
                }
                for (int i = 0; i < numberOfPlayers; i++) {
                    game.addPlayer(nameFields.get(i).getText(), typeOfScorecard);
                }
                game.setToBegin((int) beginnerSelection.getSelectedToggle().getUserData());
                redrawGameArea(window);
                window.setScene(gameScene);
            }
        });

        nameGivingExit.setOnAction((event) -> {
            window.setScene(startScene);
        });

        nameGivingLayout.add(beginGameButton, 0, numberOfPlayers + 1);
        nameGivingLayout.add(nameGivingExit, 1, numberOfPlayers + 1);

        nameGivingScene = new Scene(nameGivingLayout);
    }

    /**
     * Create setting sliders for customized game.
     *
     * @param min Minimum value
     * @param max Maximum value
     * @param startValue Default value where slider is set at the beginning.
     * @param tickSpacing "The unit distance between major tick marks."
     * @param minorTicks "The number of minor ticks to place between any two
     * major ticks."
     * @return Slider with the aforementioned properties.
     */
    private Slider createSettingSlider(int min, int max, int startValue, int tickSpacing, int minorTicks) {

        Slider slider = new Slider(min, max, startValue);

        slider.setMajorTickUnit(tickSpacing);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(minorTicks);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(400);

        return slider;
    }

    /**
     * Create RadioButton.
     *
     * @param text Text to display beside the button.
     * @param userData Button's property for later retrieval.
     * @param toggleGroup Group to set the button into. If null, not set into
     * group.
     * @param setSelected Boolean indicating should the button be set selected.
     * @return RadioButton with the above settings.
     */
    private RadioButton createRadioButton(String text, Object userData, ToggleGroup toggleGroup, boolean setSelected) {
        RadioButton button = new RadioButton(text);
        button.setUserData(userData);
        if (toggleGroup != null) {
            button.setToggleGroup(toggleGroup);
        }
        button.setSelected(setSelected);
        return button;
    }

    public static boolean areAllFalse(boolean[] array) {
        for (boolean b : array) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prompt to display at the end of the game.
     *
     * @param window
     */
    private void showEndGamePrompt(Stage window) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thank you!");
        alert.setHeaderText("Thank you for playing!");
        alert.setContentText("Thanks for playing! If you want to save scores press"
                + " 'OK'. Otherwise press 'Cancel'.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            game.addPlayersInTheGameToRecords();
            window.setScene(startScene);
        }

    }

}
