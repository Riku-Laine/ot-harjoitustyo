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
    private final DiceCollection dices;
    
    public YatzyService() {
        this.playerList = new ArrayList<>();
        this.dices = new DiceCollection();
    }

    public void setPlayerToBegin(Player player) {
        player.setTurn(true);
    }

    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }
    
    public void addPlayer(String name) {
        Player p = new Player(name);
        if(this.playerList.isEmpty())
            p.setTurn(true);
        this.playerList.add(p);
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
        return this.dices.toString();
    }

    public void throwDies(boolean[] selected) {
        this.dices.rollDies(selected);
    }

}
