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
        Scorecard scorecard = new Scorecard();

        System.out.println(turns.rollDices(5).toString());
        while (true) {
            System.out.println("Heitä noppaa painamalla enter!");
            reader.nextLine();
            int[] taul = turns.rollDices(5);
            for (int i = 0; i < 5; i++) {
                System.out.print(taul[i] + " ");
            }
            System.out.println("\nKirjoita yhdistelmä:");
            String combination = reader.nextLine();
            System.out.print("Saamasi pisteet: " + scorecard.getScore(combination, taul) + "\n");
//            for(int i=0; i< selections.length; i++){
//                int[] choices = selections[i];
//            }
//            System.out.println(selections);
        }
    }

    public static void main(String[] args) {
        startOnePlayerGame();
    }

}
