/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import yatzy.domain.scorecards.AmericanScorecard;
import yatzy.domain.scorecards.ScandinavianScorecard;

/**
 *
 * @author Riku_L
 */
public class Player {

    private final String name;
    private boolean hasTurn;
    private final Scorecard scorecard;

    public Player(String name) {
        this.name = name;
        this.scorecard = new ScandinavianScorecard();
        this.hasTurn = false;
    }

    public Player(String name, String scorecardType) {
        this.name = name;
        if (scorecardType.equals("Scandinavian")) {
            this.scorecard = new ScandinavianScorecard();
        } else {
            this.scorecard = new AmericanScorecard();
        }
        this.hasTurn = false;
    }

    public String getName() {
        return this.name;
    }

    public void setTurn(boolean turn) {
        this.hasTurn = turn;
    }

    public boolean getTurn() {
        return this.hasTurn;
    }

    public void setPoints(String combination, DiceCollection dc) {
        this.scorecard.setPointsForCombination(combination, dc);
    }

    public Scorecard getScorecard() {
        return this.scorecard;
    }

    /**
     * Check if all combiations have been filled, i.e. that the scorecard is
     * full.
     *
     * @return true if full, false otherwise.
     */
    public boolean isScorecardFull() {
        for (String combination : this.scorecard.getCombinations()) {
            if (this.scorecard.getPointsFor(combination) == -1) {
                return false;
            }
        }
        return true;
    }
}
