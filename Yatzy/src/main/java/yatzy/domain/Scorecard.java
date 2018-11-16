/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Riku_L
 */
public class Scorecard {

    private ArrayList<Player> playerList;
    private HashMap<String, Integer> scoretable;

    public Scorecard() {
        this.playerList = new ArrayList<>();
        this.scoretable = new HashMap<>();
        initializeScoretable();
    }

    public void setPlayers(ArrayList players) {
        this.playerList = players;
    }

    public int getPointsForCombination(String combination, int[] dies) {

        if (null != combination) {
            switch (combination) {
                case "ones":
                    return checkForPointValues(dies, 1);
                case "twos":
                    return checkForPointValues(dies, 2);
                case "threes":
                    return checkForPointValues(dies, 3);
                case "fours":
                    return checkForPointValues(dies, 4);
                case "fives":
                    return checkForPointValues(dies, 5);
                case "sixes":
                    return checkForPointValues(dies, 6);
                case "one pair":
                    return checkForMultiplesOfSizeN(dies, 1, 2);
                case "two pairs":
                    return checkForMultiplesOfSizeN(dies, 2, 2);
                case "triplet":     // Three Of A Kind
                    return checkForMultiplesOfSizeN(dies, 1, 3);
                case "quadruplet":  // Four Of A Kind
                    return checkForMultiplesOfSizeN(dies, 1, 4);
                case "small straight":
                    return checkForStraight(dies, "small");
                case "big straight":
                    return checkForStraight(dies, "big");
                case "full house":
                    return checkForFullHouse(dies);
                case "chance":
                    return checkForChance(dies);
                case "yatzy":  // Yahtzee??
                    return checkForYatzy(dies);
                default:
                    break;
            }
        }

        // No match, return -1
        return -1;
    }

    public int checkForPointValues(int[] dies, int which) {
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] == which) {
                score += dies[i];
            }
        }
        return score;
    }

    /**
     * This method counts multiples (pairs, triples, quadruplets etc.) from an
     * integer array.
     *
     * @param dies The eyes of the dies.
     * @param howManyMultiples How many multiples? (e.g. 2 or 7 pairs)
     * @param whatMultiple Pair (2), triplet (3) quadruplet(4) etc.
     * @return Number of points.
     */
    public int checkForMultiplesOfSizeN(int[] dies, int howManyMultiples, int whatMultiple) {

        int[] freqs = new int[6]; // could be generalised max number of eye
        for (int i = 0; i < dies.length; i++) {
            freqs[dies[i] - 1]++;  // array of freqs
        }

        int score = 0;
        int multiplesFound = 0;
        for (int i = 5; i >= 0; i--) {
            if (freqs[i] >= whatMultiple & multiplesFound < howManyMultiples) {
                score += whatMultiple * (i + 1);
                multiplesFound++;
            }
        }

        if (multiplesFound == howManyMultiples) // have all multiples been found
        {
            return score;
        }
        return 0;
    }

    private int checkForStraight(int[] dies, String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForFullHouse(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForChance(int[] dies) {
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            score += dies[i];
        }

        return score;
    }

    private int checkForYatzy(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeScoretable() {
        String[] combinations = {"Ones", "Twos", "Threes", "Fours", "Fives",
            "Sixes", "One pair", "Two pairs", "Three of a kind",
            "Four of a kind", "Small straight", "Big straight",
            "Full house", "Chance", "Yatzy", "Total"};

        for (String combination : combinations) {
            // initialize with -1 to differentiate between zero score and score
            // not set
            this.scoretable.put(combination, -1);
        }

    }

}
