/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.ArrayList;

/**
 *
 * @author Riku_L
 */
public class YatzyService {

    public ArrayList<Player> playerList;

    public YatzyService() {
        this.playerList = new ArrayList<>();
    }

    public void startOnePlayerGame() {

        Turn turns = new Turn();
        this.playerList.add(new Player());

        Scorecard scorecard = new Scorecard();

        while (true) {
            int[] dies = turns.rollDies(5);
            break;
        }

    }

    public void setPlayerName(Player player, String name) {
        if (!name.isEmpty()) {
            playerList.get(0).setName(name);
        } else {
            throw new IllegalArgumentException("Empty name not allowed!");
        }
    }

    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }

    public Player getPlayerWithTurn() {
        // only one player has turn
        for (int i = 0; i < this.playerList.size(); i++) {
            if (this.playerList.get(i).hasTurn) {
                return this.playerList.get(i);
            }
        }
        return null;
    }

    public String getDiceValues() {
        return "9 9 9 9 9";
    }

    public void throwDies(boolean[] selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
