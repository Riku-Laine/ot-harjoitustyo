/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author Riku_L
 */
public class Scorecard {

    private final LinkedHashMap<String, Integer> scoretable;
    private final String[] combinations = {"Ones", "Twos", "Threes", "Fours", "Fives",
            "Sixes", "One pair", "Two pairs", "Three of a kind",
            "Four of a kind", "Small straight", "Big straight",
            "Full house", "Chance", "Yatzy"};

    public Scorecard() {
        this.scoretable = new LinkedHashMap<>();
        initializeScoretable();
    }

    public LinkedHashMap<String, Integer> getPlayersScoretable() {
        getTotal();
        return this.scoretable;
    }

    /**
     * Set points for a combination in the scorecard.
     *
     * @param combination Name of the combination. If name is not valid,
     * IllegalArgumentException is thrown.
     * @param dies Integeer array of dies.
     */
    public void setPointsForCombination(String combination, int[] dies) {
        int score = 0;
        if (null != combination) {
            if (combination.equals("Ones")) {
                score = checkForPointValues(dies, 1);
            } else if (combination.equals("Twos")) {
                score = checkForPointValues(dies, 2);
            } else if (combination.equals("Threes")) {
                score = checkForPointValues(dies, 3);
            } else if (combination.equals("Fours")) {
                score = checkForPointValues(dies, 4);
            } else if (combination.equals("Fives")) {
                score = checkForPointValues(dies, 5);
            } else if (combination.equals("Sixes")) {
                score = checkForPointValues(dies, 6);
            } else if (combination.equals("One pair")) {
                score = checkForMultiplesOfSizeN(dies, 1, 2);
            } else if (combination.equals("Two pairs")) {
                score = checkForMultiplesOfSizeN(dies, 2, 2);
            } else if (combination.equals("Three of a kind")) {
                score = checkForMultiplesOfSizeN(dies, 1, 3);
            } else if (combination.equals("Four of a kind")) {
                score = checkForMultiplesOfSizeN(dies, 1, 4);
            } else if (combination.equals("Small straight")) {
                score = checkForStraight(dies, "small");
            } else if (combination.equals("Big straight")) {
                score = checkForStraight(dies, "big");
            } else if (combination.equals("Full house")) {
                score = checkForFullHouse(dies);
            } else if (combination.equals("Chance")) {
                score = checkForChance(dies);
            } else if (combination.equals("Yatzy")) {  // Yahtzee??
                score = checkForYatzy(dies);
            } else {
                throw new IllegalArgumentException("Invalid combination argument!");
            }
        }

        // TODO Make these to errors
        if (this.scoretable.get(combination) == -1) {
            this.scoretable.replace(combination, score);
            System.out.println("Score of " + score + " was put for combination " + combination);
        } else {
            System.out.println("Score for combination " + combination + " was already found on scoreboard.");
        }
    }

    /**
     * Check for single point values, e.g. ones, twos, threes etc.
     *
     * @param dies Integer array of dice values
     * @param which Integer of which single point to inspect for.
     * @return Points for this combination.
     */
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
     * integer array and return the corresponding score (their sum).
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

        // Check that all multiples have been found
        if (multiplesFound == howManyMultiples) {
            return score;
        }
        return 0;
    }

    /**
     * Returns points for big or small straight. Defined as (2, 3, 4, 5 and 6)
     * and (1, 2, 3, 4 and 5) correspondingly.
     *
     * @param dies Integer array of dies.
     * @param type String; "big" for big straight, "small" for small straight.
     * @return If straight is found, returns (int) 20 for big straight and 15
     * for small straight.
     */
    public int checkForStraight(int[] dies, String type) {
        Arrays.sort(dies);
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] != i + 1 & type.equals("small")) {
                return 0;
            }
            if (dies[i] != i + 2 & type.equals("big")) {
                return 0;
            }
        }
        if (type.equals("small")) {
            return 15;
        }
        if (type.equals("big")) {
            return 20;
        }

        throw new IllegalArgumentException("Type wrong");
    }

    /**
     * Calculate points for full house (a pair and a triplet).
     *
     * @param dies Integer array of dies.
     * @return
     */
    public int checkForFullHouse(int[] dies) {
        int pair = checkForMultiplesOfSizeN(dies, 1, 2);
        int triplet = checkForMultiplesOfSizeN(dies, 1, 3);

        // TODO Make better fix
        int four = checkForMultiplesOfSizeN(dies, 1, 4);

        if (pair != 0 & triplet != 0 & four == 0) {
            return pair + triplet;
        }
        return 0;
    }

    /**
     * Calculate points for chance (sum of eyes).
     *
     * @param dies Integer array of dice values.
     * @return Points for chance.
     */
    public int checkForChance(int[] dies) {
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            score += dies[i];
        }

        return score;
    }

    /**
     * Initialize scorecard's LinkedHashMap with scores of -1 to differentiate
     * between zero score and score not set.
     */
    private void initializeScoretable() {
        for (String combination : combinations) {
            this.scoretable.put(combination, -1);
        }

        this.scoretable.put("Total", 0);
    }

    /**
     * Calculates total of points for the scorecard.
     */
    private void getTotal() {
        int total = this.scoretable.keySet().stream().mapToInt(key -> {
            if (this.scoretable.get(key) >= 0 & !key.equals("Total")) {
                return this.scoretable.get(key);
            }
            return 0;
        }).sum();

        this.scoretable.replace("Total", total);

    }

    /**
     * Calculates points for Yatzy (five same).
     *
     * @param dies Integer array of dies.
     * @return 0 for no Yatzy 50 if there is Yatzy.
     */
    private Integer checkForYatzy(int[] dies) {
        int eye = dies[0];
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] != eye) {
                return 0;
            }
        }
        return 50;
    }
    
    public String[] getCombinations(){
        return this.combinations;
    }

}
