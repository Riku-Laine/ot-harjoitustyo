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
public class Record implements Comparable<Record> {

    private final Player player;
    private final String scorecardType;
    private final int diceAmount;
    private final int maxEyeNumber;
    private final int throwAmount;
    private final int points;

    public Record(Player p, String scorecardType, int diceAmount, int maxEyeNumber, int maxThrows, int points) {
        this.player = p;
        this.scorecardType = scorecardType;
        this.diceAmount = diceAmount;
        this.maxEyeNumber = maxEyeNumber;
        this.throwAmount = maxThrows;
        this.points = points;
    }

    public int getDiceAmount() {
        return diceAmount;
    }

    public int getMaxEyeNumber() {
        return maxEyeNumber;
    }

    public int getThrowAmount() {
        return throwAmount;
    }

    public Player getPlayer() {
        return player;
    }

    public String getScorecardType() {
        return scorecardType;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(Record r) {
        return r.getPoints() - this.points;
    }

    @Override
    public String toString() {
        return scorecardType + " (" + diceAmount + "d" + maxEyeNumber + ", " + throwAmount + " throws) " + player.getName() + ", " + points + " points";
    }

}
