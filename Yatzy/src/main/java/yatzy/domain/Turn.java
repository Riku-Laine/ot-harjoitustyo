/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Riku_L
 */
public class Turn {

    private final Random random;

    public Turn() {
        this.random = new Random();
    }

    /**
     * This method plays a turn. Rolls dies.
     *
     * @param player
     * @param reader To be deprecated??
     * @return
     */
    public int[] playTurn(Player player, Scanner reader) {

        System.out.println("Heitä noppaa painamalla enter!");
        reader.nextLine();

        while (true) {

            int[] taul = rollDies(5);
            printDies(taul);
            System.out.println("\nKirjoita 'heitto' valitaksesi uudelleen heitettävät nopat, "
                    + "'merkitse' valitaksesi yhdistelmä merkattavaksi pöytäkirjaan ");

            String command = reader.nextLine();

            if (command.equals("merkitse")) {
                return taul;
            } else if (command.equals("heitto")) {

            }
//            for(int i=0; i< selections.length; i++){
//                int[] choices = selections[i];
//            }
//            System.out.println(selections);
        }
    }

    public int[] rollDies(int diesToThrow) {

        int[] result = new int[diesToThrow];

        for (int i = 0; i < diesToThrow; i++) {
            result[i] = (int) Math.round(random.nextDouble() * 6 + 0.5);
        }

        return result;
    }

    public void printDies(int[] dies) {
        for (int i = 0; i < dies.length; i++) {
            System.out.print(dies[i] + " ");
        }
    }

}
