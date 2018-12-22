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
 * Scandinavian scorecard for Yatzy game. Contains combinationAndBonusNames such
 * as 'One pair' which are not included in the US version of the game. Also Big
 * Straight is defined as numbers from 2 to 6 and is worth 20 points.
 *
 * @author Riku_L
 */
public class ScandinavianScorecard extends Scorecard {

    private final ArrayList<String> upperSection;
    private final ArrayList<String> lowerSection;

    /**
     * Constructs scandinavian scorecard. Assigns name of "Scandinavian" and
     * inserts combination names.
     */
    public ScandinavianScorecard() {
        super("Scandinavian");

        this.combinationAndBonusNames = new ArrayList(Arrays.asList("Ones", "Twos", "Threes",
                "Fours", "Fives", "Sixes", "Upper Bonus", "One pair", "Two pairs",
                "Three of a kind", "Four of a kind", "Small straight",
                "Big straight", "Full house", "Chance", "Yatzy", "Total"));

        this.combinationNames = new ArrayList(Arrays.asList("Ones", "Twos", "Threes",
                "Fours", "Fives", "Sixes", "One pair", "Two pairs",
                "Three of a kind", "Four of a kind", "Small straight",
                "Big straight", "Full house", "Chance", "Yatzy"));

        this.upperSection = new ArrayList(Arrays.asList("Ones", "Twos", "Threes",
                "Fours", "Fives", "Sixes"));

        this.lowerSection = new ArrayList(Arrays.asList("One pair", "Two pairs",
                "Three of a kind", "Four of a kind", "Small straight",
                "Big straight", "Full house", "Chance", "Yatzy"));

        initializeScoretable();
    }

    /**
     * Set points for a combination in the scorecard.
     *
     * @param combination Name of the combination. If type is not valid,
     * IllegalArgumentException is thrown.
     * @param dies DiceCollection of dies.
     */
    @Override
    public void setPointsForCombination(String combination, DiceCollection dies) {
        int score = 0;
        if (null != combination) {
            if (this.upperSection.contains(combination)) {
                score = upperSectionPoints(combination, dies);
                checkForUpperSectionBonus();
            } else if (this.lowerSection.contains(combination)) {
                score = lowerSectionPoints(combination, dies);
            }
        } else {
            throw new IllegalArgumentException("Invalid combination argument!");
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
     * @return 0 if there is not full house, sum of dies otherwise.
     */
    public int checkForFullHouse(DiceCollection dc) {

        int[] freqs = new int[dc.getBiggestEyeNumber() + 1];
        for (int i = 0; i < dc.getDies().length; i++) {
            freqs[dc.getDies()[i]]++;
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
            return getSumOfDies(dc);
        }
        return 0;
    }

    private int upperSectionPoints(String combination, DiceCollection dies) {
        if (combination.equals("Ones")) {
            return checkForPointValues(dies, 1);
        } else if (combination.equals("Twos")) {
            return checkForPointValues(dies, 2);
        } else if (combination.equals("Threes")) {
            return checkForPointValues(dies, 3);
        } else if (combination.equals("Fours")) {
            return checkForPointValues(dies, 4);
        } else if (combination.equals("Fives")) {
            return checkForPointValues(dies, 5);
        } else if (combination.equals("Sixes")) {
            return checkForPointValues(dies, 6);
        }
        throw new Error();
    }

    private int lowerSectionPoints(String combination, DiceCollection dies) {
        if (combination.equals("One pair")) {
            return checkForKMultiplesOfSizeN(dies, 1, 2);
        } else if (combination.equals("Two pairs")) {
            return checkForKMultiplesOfSizeN(dies, 2, 2);
        } else if (combination.equals("Three of a kind")) {
            return checkForKMultiplesOfSizeN(dies, 1, 3);
        } else if (combination.equals("Four of a kind")) {
            return checkForKMultiplesOfSizeN(dies, 1, 4);
        } else if (combination.equals("Small straight")) {
            return checkForSequentialNumbers(dies, 1, 5, 15);
        } else if (combination.equals("Big straight")) {
            return checkForSequentialNumbers(dies, 2, 6, 20);
        } else if (combination.equals("Full house")) {
            return checkForFullHouse(dies);
        } else if (combination.equals("Chance")) {
            return getSumOfDies(dies);
        } else if (combination.equals("Yatzy")) {
            return checkForAllTheSame(dies, 50);
        }
        throw new Error();
    }

    /**
     * If player has 63 points or more in the upper section of their game, they
     * are awarded 50 extra points.
     */
    private void checkForUpperSectionBonus() {
        int upperSum = 0;
        for (String upperCombination : this.upperSection) {
            upperSum += this.scoretable.get(upperCombination);
        }
        if (upperSum >= 63) {
            this.scoretable.replace("Upper Bonus", 50);
        }
    }
}
