/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Riku_L
 */
public class DiceCollection {

    private final Random random;
    private int[] dies;

    /**
     * Create new dice collection of size 5.
     */
    public DiceCollection() {
        this.random = new Random();
        this.dies = new int[5];
    }

    /**
     * Create new dice collection of given size.
     *
     * @param sizeOfCollection Size of dice collection, number of dies.
     */
    public DiceCollection(int sizeOfCollection) {
        this.random = new Random();
        this.dies = new int[sizeOfCollection];
    }

    /**
     * Rolls all dies.
     */
    public void rollAllDies() {
        boolean[] bools = new boolean[this.dies.length];
        Arrays.fill(bools, true);
        rollDies(bools);
    }

    /**
     * Roll specified dies. Specify with boolean array.
     *
     * @param diesToThrow Boolean array to specify which dies to throw.
     */
    public void rollDies(boolean[] diesToThrow) {
        for (int i = 0; i < dies.length; i++) {
            if (diesToThrow[i]) {
                this.dies[i] = (int) Math.round(random.nextDouble() * 6 + 0.5);
            }
        }
    }

    public int[] getDies() {
        return this.dies;
    }

    public void setDies(int[] dies) {
        this.dies = dies;
    }

}
