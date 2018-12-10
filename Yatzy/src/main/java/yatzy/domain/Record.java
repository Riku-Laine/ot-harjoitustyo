/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

/**
 * Class for records to show in the user interface and save in the database.
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

    /**
     * Constructor for a new record.
     *
     * @param player A Player object of the player.
     * @param scorecardType Type of the scorecard used in recording the record.
     * @param diceAmount Amount of dice that was used in the game.
     * @param maxEyeNumber Maximum eye number of the dies used in the game.
     * @param maxThrows Amount of throws in turnused in the game.
     * @param points Points of the record.
     */
    public Record(Player player, String scorecardType, int diceAmount, int maxEyeNumber, int maxThrows, int points) {
        this.player = player;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Check null
        if (o == null) {
            return false;
        }
        // Check type and cast
        if (getClass() != o.getClass()) {
            return false;
        }

        Record record = (Record) o;
        // field comparison
        return this.player.getName().equals(record.getPlayer().getName())
                && this.scorecardType.equals(record.getScorecardType())
                && this.diceAmount == record.getDiceAmount()
                && this.maxEyeNumber == record.getMaxEyeNumber()
                && this.throwAmount == record.getThrowAmount()
                && this.points == record.getPoints();

    }
}
