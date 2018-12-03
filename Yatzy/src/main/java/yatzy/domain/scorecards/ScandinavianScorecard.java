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
public class ScandinavianScorecard extends Scorecard {

    public ScandinavianScorecard() {
        super("Scandinavian scorecard");

        // TODO Fix this awful inheritance.
        ArrayList scandinavianCombinations = new ArrayList();
        scandinavianCombinations.addAll(Arrays.asList("Ones", "Twos", "Threes", "Fours",
                "Fives", "Sixes", "One pair", "Two pairs", "Three of a kind",
                "Four of a kind", "Small straight", "Big straight",
                "Full house", "Chance", "Yatzy"));

        this.combinations = scandinavianCombinations;
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
            if (combination.equals("Ones")) {
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
            } else if (combination.equals("One pair")) {
                score = checkForMultiplesOfSizeN(dies, 1, 2);
            } else if (combination.equals("Two pairs")) {
                score = checkForMultiplesOfSizeN(dies, 2, 2);
            } else if (combination.equals("Three of a kind")) {
                score = checkForMultiplesOfSizeN(dies, 1, 3);
            } else if (combination.equals("Four of a kind")) {
                score = checkForMultiplesOfSizeN(dies, 1, 4);
            } else if (combination.equals("Small straight")) {
                score = checkForSequentialNumbers(dies, 1, 5, 15);
            } else if (combination.equals("Big straight")) {
                score = checkForSequentialNumbers(dies, 2, 6, 20);
            } else if (combination.equals("Full house")) {
                score = checkForFullHouse(dies);
            } else if (combination.equals("Chance")) {
                score = getSumOfDies(dies);
            } else if (combination.equals("Yatzy")) {  // Yahtzee??
                score = checkForAllTheSame(dies, 50);
            } else {
                throw new IllegalArgumentException("Invalid combination argument!");
            }
        }

        if (this.scoretable.get(combination) == -1) {
            this.scoretable.replace(combination, score);
        } else {
            throw new Error("Combination valid, score already in scorecard!");
        }
    }

    /**
     * Calculate points for full house (a pair and a triplet).
     *
     * @param dc Integer array of dies.
     * @return
     */
    public int checkForFullHouse(DiceCollection dc) {
        int pair = checkForMultiplesOfSizeN(dc, 1, 2);
        int triplet = checkForMultiplesOfSizeN(dc, 1, 3);

        // TODO Make better fix
        int four = checkForMultiplesOfSizeN(dc, 1, 4);

        if (pair != 0 & triplet != 0 & four == 0) {
            return pair + triplet;
        }
        return 0;
    }
}
