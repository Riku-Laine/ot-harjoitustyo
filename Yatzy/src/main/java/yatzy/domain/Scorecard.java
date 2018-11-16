/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.HashMap;

/**
 *
 * @author Riku_L
 */
public class Scorecard {

    public int getScore(String combination, int[] dies) {

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
                    return checkForNPairs(dies, 1);
                case "two pairs":
                    return checkForNPairs(dies, 2);
                case "triplet": // Three Of A Kind
                    return checkForTriplet(dies);
                case "quadruplet":  // Four Of A Kind
                    return checkForQuadruplet(dies);
                case "small straight":
                    return checkForSmallStraight(dies);
                case "big straight":
                    return checkForBigStraight(dies);
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

    public int checkForNPairs(int[] dies, int numberOfPairs) {
        // find highest pairs and return their values

        HashMap map = new HashMap<>();
        int[] diesWithPair = new int[numberOfPairs]; // rename better
        int pairsFound = 0;
        for (int i = 0; i < dies.length; i++) {

            // integer has been found, more pairs to be found
            // To do: 5 dies, 2 pairs, want highest pair!!
            if (map.get(dies[i]) != null & pairsFound < diesWithPair.length) {
                diesWithPair[pairsFound] = dies[i];
                pairsFound++;
            } else {
                map.put(dies[i], 1);
            }
        }

        int score = 0;
        for (int i = 0; i < diesWithPair.length; i++) {
            score = +2 * diesWithPair[i];
        }

        return score;
    }

    private int checkForTriplet(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForQuadruplet(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForSmallStraight(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForBigStraight(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForFullHouse(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkForChance(int[] dies) {
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            score = +dies[i];
        }

        return score;
    }

    private int checkForYatzy(int[] dies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
