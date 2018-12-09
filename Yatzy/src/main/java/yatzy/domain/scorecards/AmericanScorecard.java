/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain.scorecards;

import java.util.ArrayList;
import java.util.Arrays;
import yatzy.domain.DiceCollection;
import yatzy.domain.Scorecard;

/**
 *
 * @author Riku_L
 */
public class AmericanScorecard extends Scorecard {

    /**
     * Constructs an American score card for Yatzy. Ie. Yatzy is named Yahtzee
     * and e.g. small straight is four digits long.
     */
    public AmericanScorecard() {
        super("American");

        this.combinations = new ArrayList(Arrays.asList("Aces", "Twos", "Threes",
                "Fours", "Fives", "Sixes", "Three of a kind", "Four of a kind",
                "Full house", "Short straight", "Long straight", "Yahtzee",
                "Chance"));

        initializeScoretable();

    }

    @Override
    public void setPointsForCombination(String combination, DiceCollection dies) {
        int score = 0;
        if (null != combination) {
            if (combination.equals("Aces")) {
                score = checkForPointValues(dies, 1);
            } else if (combination.equals("Twos")) {
                score = checkForPointValues(dies, 2);
            } else if (combination.equals("Threes")) {
                score = checkForPointValues(dies, 3);
            } else if (combination.equals("Fours")) {
                score = checkForPointValues(dies, 4);
            } else if (combination.equals("Fives")) {
                score = checkForPointValues(dies, 5);
            } else if (combination.equals("Sixes")) {
                score = checkForPointValues(dies, 6);
            } else if (combination.equals("Three of a kind")) {
                if (checkForKMultiplesOfSizeN(dies, 1, 3) != 0) {
                    score = getSumOfDies(dies);
                }
            } else if (combination.equals("Four of a kind")) {
                if (checkForKMultiplesOfSizeN(dies, 1, 4) != 0) {
                    score = getSumOfDies(dies);
                }
            } else if (combination.equals("Short straight")) {
                score = Math.max(checkForSequentialNumbers(dies, 1, 4, 30),
                        Math.max(checkForSequentialNumbers(dies, 2, 5, 30),
                                checkForSequentialNumbers(dies, 3, 6, 30)));
            } else if (combination.equals("Long straight")) {
                score = Math.max(checkForSequentialNumbers(dies, 1, 5, 40),
                        checkForSequentialNumbers(dies, 2, 6, 40));
            } else if (combination.equals("Full house")) {
                score = checkForFullHouse(dies);
            } else if (combination.equals("Chance")) {
                score = getSumOfDies(dies);
            } else if (combination.equals("Yahtzee")) {
                if (checkForKMultiplesOfSizeN(dies, 1, 5) != 0) {
                    score = 50;
                }
            } else {
                throw new IllegalArgumentException("Invalid combination argument!");
            }
        }

        if (this.scoretable.get(combination) == -1) {
            this.scoretable.replace(combination, score);
        } else {
            throw new Error("Combination valid, but score already in scorecard!");
        }
        calculateTotal();
    }

    /**
     * Calculate points for full house (a pair and a triplet). Desinged for
     * convetional Yatzy of five six-sided dies.
     *
     * @param dc Integer array of dies.
     * @return 0 if there is not full house, 25 otherwise.
     */
    public int checkForFullHouse(DiceCollection dc) {
        int[] dies = dc.getDies();
        Arrays.sort(dies);

        int[] freqs = new int[dc.getBiggestEyeNumber() + 1];
        for (int i = 0; i < dies.length; i++) {
            freqs[dies[i]]++;
        }
        boolean pairFound = false;
        boolean tripletFound = false;

        for (int i = dc.getBiggestEyeNumber(); i > 0; i--) {
            if (!pairFound & freqs[i] == 2) {
                pairFound = true;
            } else if (!tripletFound & freqs[i] == 3) {
                tripletFound = true;
            }
        }
        if (pairFound & tripletFound) {
            return 25;
        }
        return 0;
    }
}
