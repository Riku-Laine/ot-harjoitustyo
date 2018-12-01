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
    private final int biggestEyeNumber;

    /**
     * Create a standard Yatzy dice collection, i.e. a new dice collection of
     * size five (5) with biggest eye number of six (6).
     */
    public DiceCollection() {
        this.random = new Random();
        this.dies = new int[5];
        this.biggestEyeNumber = 6;
    }

    /**
     * Create new dice collection with a specified size and biggest eye number.
     *
     * @param sizeOfCollection Size of dice collection, number of dies.
     * @param biggestEyeNumber Biggest eye number, i.e. biggest number you can
     * get from a dice.
     */
    public DiceCollection(int sizeOfCollection, int biggestEyeNumber) {
        if (sizeOfCollection <= 0 || biggestEyeNumber <= 0) {
            throw new IllegalArgumentException("Illegal size or biggest eye number specified!");
        }
        this.random = new Random();
        this.dies = new int[sizeOfCollection];
        this.biggestEyeNumber = biggestEyeNumber;
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
     * Roll specified dies. Specify which with boolean array.
     *
     * @param diesToThrow Boolean array to specify which dies to throw.
     */
    public void rollDies(boolean[] diesToThrow) {
        if (diesToThrow.length != this.dies.length) {
            throw new IllegalArgumentException("Wrong size of boolean array!");
        }
        for (int i = 0; i < dies.length; i++) {
            if (diesToThrow[i]) {
                this.dies[i] = (int) Math.round(random.nextDouble() * this.biggestEyeNumber + 0.5);
            }
        }
    }

    public int[] getDies() {
        return this.dies;
    }

    public void setDies(int[] dies) {
        if (dies.length != this.dies.length) {
            throw new IllegalArgumentException("Wrong size of integer array!");
        }
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] > this.biggestEyeNumber) {
                throw new IllegalArgumentException("A too big eye number specified!");
            }
        }
        this.dies = dies;
    }

    /**
     * Get the biggest possible eye number of the collection.
     *
     * @return The biggest possible eye number of the dies.
     */
    public int getBiggestEyeNumber() {
        return this.biggestEyeNumber;
    }

}
