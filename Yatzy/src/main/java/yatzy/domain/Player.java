/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

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
        this.scorecard = new ScandinavianScorecard(); // With chosen type.
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
}
