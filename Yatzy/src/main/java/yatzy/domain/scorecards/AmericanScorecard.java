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

    public AmericanScorecard() {
        super("American scorecard");

        this.combinations = new ArrayList(Arrays.asList("Aces", "Twos", "Threes",
                "Fours", "Fives", "Sixes", "Three of a kind", "Four of a kind",
                "Full house", "Small straight", "Large straight", "Yahtzee",
                "Chance"));

        initializeScoretable();

    }

    @Override
    public void setPointsForCombination(String combination, DiceCollection dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
