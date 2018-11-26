/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Riku_L
 */
public class YatzyService {

    private final ArrayList<Player> playerList;
    private final DiceCollection dices;
    private int throwsUsed;

    public YatzyService() {
        this.playerList = new ArrayList<>();
        this.dices = new DiceCollection();
        this.throwsUsed = 0;
    }

    public YatzyService(int nDies, int biggestEye) {
        this.playerList = new ArrayList<>();
        this.dices = new DiceCollection(nDies, biggestEye);
        this.throwsUsed = 0;
    }

    /**
     * Get players as an ArrayList.
     *
     * @return ArrayList of players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }

    /**
     * Add player to player list. If playerlist is empty, added player will be
     * set to start the game.
     *
     * @param name Name of the player.
     */
    public void addPlayer(String name) {
        Player p = new Player(name);
        if (this.playerList.isEmpty()) {
            p.setTurn(true);
        }
        this.playerList.add(p);
    }

    /**
     * Get player with turn.
     *
     * @return Player with turn. If there are no players, null is returned.
     */
    public Player getPlayerWithTurn() {
        // only one player has turn
        for (int i = 0; i < this.playerList.size(); i++) {
            if (this.playerList.get(i).getTurn()) {
                return this.playerList.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param i
     * @return 
     */
    public int getDice(int i) {
        return this.dices.getDies()[i];
    }

    /**
     * 
     * @return 
     */
    public int getThrowsUsed() {
        return this.throwsUsed;
    }

    /**
     * 
     * @param number 
     */
    public void setThrowsUsed(int number) {
        this.throwsUsed = number;
    }

    /**
     * 
     * @param selected 
     */
    public void throwDies(boolean[] selected) {
        if (this.throwsUsed < 3) {
            this.dices.rollDies(selected);
        }

        this.throwsUsed++;
    }

    /**
     * 
     */
    public void changeTurn() {

        Player p = getPlayerWithTurn();
        p.setTurn(false);
        int index = this.playerList.indexOf(p) + 1;
        if (index >= this.playerList.size()) {
            index = 0;
        }
        this.playerList.get(index).setTurn(true);
        // Set dies to zero
        this.dices.setDies(new int[this.dices.getDies().length]);
        this.throwsUsed = 0;
    }

    /**
     * 
     * @return 
     */
    public String getScoreboard() {

        String spaces = "              ";
        String scoreBoard = "Combinations    | " + this.playerList.stream()
                .map(p -> (p.getName() + spaces).substring(0, p.getName().length() + 2)).collect(Collectors.joining(" | ")) + " |\n\n";

        String[] combinations = {"Ones", "Twos", "Threes", "Fours", "Fives",
            "Sixes", "One pair", "Two pairs", "Three of a kind",
            "Four of a kind", "Small straight", "Big straight",
            "Full house", "Chance", "Yatzy", "Total"};

        for (String comb : combinations) {
            String row = (comb + spaces).substring(0, 16) + "| ";
            for (Player p : this.playerList) {
                int score = p.getScorecard().get(comb);
                if (score == -1) {
                    row += (" " + "-" + spaces).substring(0, p.getName().length() + 2) + " | ";
                } else {
                    row += (" " + score + spaces).substring(0, p.getName().length() + 2) + " | ";
                }
            }
            scoreBoard += row + "\n";
        }

        return scoreBoard;
    }

    /**
     * 
     * @return 
     */
    public int[] getDies() {
        return this.dices.getDies();
    }

    /**
     * 
     * @param set 
     */
    public void setDies(int[] set) {
        this.dices.setDies(set);
    }

    /**
     * 
     */
    public void throwAllDies() {
        if (this.throwsUsed < 3) {
            this.dices.rollAllDies();
        }

        this.throwsUsed++;
    }

    /**
     * 
     * @param playerWithTurn
     * @param combination 
     */
    public void setScore(Player playerWithTurn, String combination) {
        playerWithTurn.setPoints(combination, getDies());
        changeTurn();
    }

    /**
     * 
     * @param playerWithTurn
     * @param combination
     * @return 
     */
    public int getScore(Player playerWithTurn, String combination) {
        return playerWithTurn.getScorecard().get(combination);
    }
}
