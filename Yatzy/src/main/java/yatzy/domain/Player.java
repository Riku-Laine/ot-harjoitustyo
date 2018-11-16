/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

/**
 *
 * @author Riku_L
 */
public class Player {

    public String name;
    public boolean isTurn;

    public Player() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTurn(boolean turn) {
        this.isTurn = turn;
    }

    public boolean getTurn() {
        return this.isTurn;
    }
}
