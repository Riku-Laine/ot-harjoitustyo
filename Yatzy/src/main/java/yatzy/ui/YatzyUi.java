/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import yatzy.domain.YatzyService;

/**
 *
 * @author Riku_L
 */
public class YatzyUi extends Application {
    
    private YatzyService yatzyservice;
    
    @Override
    public void start(Stage window) {
        
        
        window.setTitle("Play Yatzy!");
        
        BorderPane windowLayout = new BorderPane(); 
        
        VBox playerButtons = new VBox();
        playerButtons.setSpacing(10);
        
        Button singlePlayer = new Button("1 player ");
        Button twoPlayers = new Button("2 players");
        
        singlePlayer.setFont(Font.font("Monospaced", 20));
        twoPlayers.setFont(Font.font("Monospaced", 20));
        
        singlePlayer.setOnAction((event) -> {
            // Start one player game
            yatzyservice.startOnePlayerGame();
            System.out.println("One player!");
        });
        
        twoPlayers.setOnAction((event) -> {
            // Start two player game
            System.out.println("Two players!");
        });
        
        playerButtons.getChildren().add(singlePlayer);
        playerButtons.getChildren().add(twoPlayers);
        
        windowLayout.setTop(playerButtons);
        windowLayout.setPadding(new Insets(10));
        
        Scene scene = new Scene(windowLayout);

	window.setScene(scene);
        
        window.show();
    }
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
}
