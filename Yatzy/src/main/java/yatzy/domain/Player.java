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
    public boolean hasTurn;

    public Player() {
        this.hasTurn = false;
    }
    
    public Player(String name) {
        this.name = name;
        this.hasTurn = false;
    }

    public void setName(String name) {
        this.name = name;
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
}
