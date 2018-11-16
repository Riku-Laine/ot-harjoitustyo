/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Random;

/**
 *
 * @author Riku_L
 */
public class Turn {

    private int[] dies;
    private Random random;

    public Turn() {
        this.dies = new int[5];
        this.random = new Random();
    }

    public int[] rollDices(int diesToThrow) {

        int[] result = new int[diesToThrow];

        for (int i = 0; i < diesToThrow; i++) {
            result[i] = (int) Math.round(random.nextDouble() * 6 + 0.5);
        }

        return result;
    }

}
