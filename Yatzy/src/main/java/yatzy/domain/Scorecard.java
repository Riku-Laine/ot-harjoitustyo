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
    
    private Player player;
    private HashMap<String, Integer> scoretable;
    
    public Scorecard(Player player){
        this.player = player;
        this.scoretable = new HashMap<>();
        initializeScoretable();
    }

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
                case "triplet":     // Three Of A Kind
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

        int[] freqs = new int[6]; // could be generalised max number of eye
        for (int i = 0; i < dies.length; i++) {
            freqs[dies[i] - 1]++;  // array of freqs
        }

        int score = 0;
        int pairsFound = 0;
        for (int i = 5; i >= 0; i--) {
            if (freqs[i] >= 2 & pairsFound < numberOfPairs) {
                score += 2 * (i + 1);
                pairsFound++;
            }
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
    
    public void printScoretable(){
        System.out.println("|----------------------|");
        System.out.println("Player: " + player.getName());
        System.out.println("|----------------------|");
        System.out.println("| Combination | Score  |");
        scoretable.keySet().iterator().forEachRemaining(key -> {
            if(scoretable.get(key)==-1)
                System.out.println("| " + key + " | " + " - |" );
            else {
                System.out.println("| " + key + " | " + scoretable.get(key) + " |" );
            }
        });
        System.out.println("|----------------------|");
    }
}
