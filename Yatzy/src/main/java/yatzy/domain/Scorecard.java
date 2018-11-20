/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Riku_L
 */
public class Scorecard {

    private Player player;
    private HashMap<String, Integer> scoretable;

    public Scorecard(Player player) {
        this.player = player;
        this.scoretable = new HashMap<>();
        initializeScoretable();
    }
    
    public HashMap getPlayersScoretable(){
        getTotal();
        return this.scoretable;
    }

    public void setPointsForCombination(String combination, int[] dies) {
 
        if (null != combination) {
            switch (combination) {
                case "ones":
                    this.scoretable.replace(combination, checkForPointValues(dies, 1));
                case "twos":
                    this.scoretable.replace(combination, checkForPointValues(dies, 2));
                case "threes":
                    this.scoretable.replace(combination, checkForPointValues(dies, 3));
                case "fours":
                    this.scoretable.replace(combination, checkForPointValues(dies, 4));
                case "fives":
                    this.scoretable.replace(combination, checkForPointValues(dies, 5));
                case "sixes":
                    this.scoretable.replace(combination, checkForPointValues(dies, 6));
                case "one pair":
                    this.scoretable.replace(combination, checkForMultiplesOfSizeN(dies, 1, 2));
                case "two pairs":
                    this.scoretable.replace(combination, checkForMultiplesOfSizeN(dies, 2, 2));
                case "triplet":     // Three Of A Kind
                    this.scoretable.replace(combination, checkForMultiplesOfSizeN(dies, 1, 3));
                case "quadruplet":  // Four Of A Kind
                    this.scoretable.replace(combination, checkForMultiplesOfSizeN(dies, 1, 4));
                case "small straight":
                    this.scoretable.replace(combination, checkForStraight(dies, "small"));
                case "big straight":
                    this.scoretable.replace(combination, checkForStraight(dies, "big"));
                case "full house":
                    this.scoretable.replace(combination, checkForFullHouse(dies));
                case "chance":
                    this.scoretable.replace(combination, checkForChance(dies));
                case "yatzy":  // Yahtzee??
                    this.scoretable.replace(combination, checkForMultiplesOfSizeN(dies, 1, 5));
                default:
                    break;
            }
        }

        // No match, return error
        throw new IllegalArgumentException("No such combination!");
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

    public int checkForStraight(int[] dies, String type) {
        Arrays.sort(dies);
        for(int i = 0; i < dies.length; i++){
            if(dies[i] != i+1 & type.equals("small"))
                return 0;
            if(dies[i] != i+2 & type.equals("big"))
                return 0;
        }
        if(type.equals("small"))
            return 15;
        if(type.equals("big"))
            return 20;
        
        throw new IllegalArgumentException("Type wrong");
    }

    public int checkForFullHouse(int[] dies) {
        int pair = checkForMultiplesOfSizeN(dies, 1, 2);
        int triplet = checkForMultiplesOfSizeN(dies, 1, 3);
        if(pair != 0 & triplet != 0)
            return pair + triplet;
        return 0;
    }

    public int checkForChance(int[] dies) {
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            score += dies[i];
        }

        return score;
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
    
    private void getTotal(){
        int total = this.scoretable.keySet().stream().mapToInt((key) -> {
            if(scoretable.get(key) != -1)
                return scoretable.get(key);
            else
                return 0;
        }).sum();
        this.scoretable.replace("Total", total);
    }

}
