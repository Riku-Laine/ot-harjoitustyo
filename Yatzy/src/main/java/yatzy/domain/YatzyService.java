/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Riku_L
 */
public class YatzyService {

    public static void startOnePlayerGame() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Peli alkaa");

        Turn turns = new Turn();
        Player player = new Player();
        Scorecard scorecard = new Scorecard(player);
        
        System.out.println("Anna pelaajan nimi tai paina Enter:");
        String name = reader.nextLine();
        
        if(!name.isEmpty())
            player.setName(name);
        
        while(true){
            turns.playTurn(player, reader);
            System.out.println("kirjoita jotain lopettaaksesi pelin, jatkaaksesi paina Enter");
            String command = reader.nextLine();
            if(!command.isEmpty())
                break;
        }
        
        scorecard.printScoretable();
        System.out.println("Kiitos pelistä!");
    }

    public static void main(String[] args) {
        startOnePlayerGame();
    }

}
