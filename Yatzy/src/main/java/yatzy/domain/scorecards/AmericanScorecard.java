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
 * American scorecard for Yatzy (or Yahtzee) game.
 *
 * @author Riku_L
 */
public class AmericanScorecard extends Scorecard {

    private final ArrayList<String> upperSection;
    private final ArrayList<String> lowerSection;

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

        this.upperSection = new ArrayList(Arrays.asList("Aces", "Twos", "Threes",
                "Fours", "Fives", "Sixes"));

        this.lowerSection = new ArrayList(Arrays.asList("Three of a kind",
                "Four of a kind", "Full house", "Short straight",
                "Long straight", "Yahtzee", "Chance"));

        initializeScoretable();

    }

    @Override
    public void setPointsForCombination(String combination, DiceCollection dies) {
        int score = 0;
        if (null != combination) {
            if (this.upperSection.contains(combination)) {
                score = upperSectionPoints(combination, dies);
            } else if (this.lowerSection.contains(combination)) {
                score = lowerSectionPoints(combination, dies);
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

        boolean pairFound = false, tripletFound = false;

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

    private int upperSectionPoints(String combination, DiceCollection dies) {
        if (combination.equals("Aces")) {
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
        if (combination.equals("Three of a kind")) {
            return (checkForKMultiplesOfSizeN(dies, 1, 3) != 0 ? getSumOfDies(dies) : 0);
        } else if (combination.equals("Four of a kind")) {
            return (checkForKMultiplesOfSizeN(dies, 1, 4) != 0 ? getSumOfDies(dies) : 0);
        } else if (combination.equals("Short straight")) {
            return checkForAmericanStraight(dies, false);
        } else if (combination.equals("Long straight")) {
            return checkForAmericanStraight(dies, true);
        } else if (combination.equals("Full house")) {
            return checkForFullHouse(dies);
        } else if (combination.equals("Chance")) {
            return getSumOfDies(dies);
        } else if (combination.equals("Yahtzee")) {
            return (checkForKMultiplesOfSizeN(dies, 1, 5) != 0 ? 50 : 0);
        }
        throw new Error();
    }

    private int checkForAmericanStraight(DiceCollection dies, boolean isLong) {
        if (isLong) {
            return Math.max(checkForSequentialNumbers(dies, 1, 4, 30),
                    Math.max(checkForSequentialNumbers(dies, 2, 5, 30),
                            checkForSequentialNumbers(dies, 3, 6, 30)));
        } else {
            return Math.max(checkForSequentialNumbers(dies, 1, 5, 40),
                    checkForSequentialNumbers(dies, 2, 6, 40));
        }
    }
}
