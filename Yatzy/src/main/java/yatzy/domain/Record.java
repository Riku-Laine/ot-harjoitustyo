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
    private final int points;

    public Record(Player p, String scorecardType, int recordPoints) {
        this.player = p;
        this.scorecardType = scorecardType;
        this.points = recordPoints;
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

}
