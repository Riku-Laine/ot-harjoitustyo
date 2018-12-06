/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author Riku_L
 */
public abstract class Scorecard {

    protected final LinkedHashMap<String, Integer> scoretable;
    protected ArrayList<String> combinations;
    protected final String type;

    public Scorecard(String name) {
        this.scoretable = new LinkedHashMap<>();
        this.combinations = new ArrayList<>();
        this.type = name;
    }

    /**
     * Subclasses must implement metod to set points for a combination.
     *
     * @param combination Combination for which the points are to be set.
     * @param dies DiceCollection of the dies in the situation.
     */
    public abstract void setPointsForCombination(String combination, DiceCollection dies);

    public LinkedHashMap<String, Integer> getPlayersScoretable() {
        calculateTotal();
        return this.scoretable;
    }

    /**
     * Check for single point values, e.g. ones, twos, threes etc.
     *
     * @param dies Integer array of dice values
     * @param which Integer of which single point to inspect for.
     * @return Points for this combination, i.e. sum of the point values.
     */
    public int checkForPointValues(DiceCollection dies, int which) {
        int score = 0;
        for (int i = 0; i < dies.getDies().length; i++) {
            if (dies.getDies()[i] == which) {
                score += dies.getDies()[i];
            }
        }
        return score;
    }

    /**
     * This method counts multiples (pairs, triples, quadruplets etc.) from an
     * integer array and return the corresponding score (their sum).
     *
     * @param dc The dies.
     * @param howManyMultiples How many multiples? (e.g. 2 or 7 pairs)
     * @param whatMultiple Pair (2), triplet (3) quadruplet(4) etc.
     * @return Sum of eye values of the dies belonging in that multiple.
     */
    public int checkForMultiplesOfSizeN(DiceCollection dc, int howManyMultiples, int whatMultiple) {

        int[] dies = dc.getDies();
        int[] freqs = new int[dc.getBiggestEyeNumber()];

        for (int i = 0; i < dies.length; i++) {
            freqs[dies[i] - 1]++;  // array of freqs
        }

        int score = 0;
        int multiplesFound = 0;
        for (int i = freqs.length - 1; i >= 0; i--) {
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
     * Returns points for sequential numbers. E.g. if fromEyeNumber is 2 and
     * toEyeNumber is 6, method searches for numbers 2, 3, 4, 5, and 6 in the
     * dies and if they are found, awards the player with pointsToGive points.
     *
     * @param dc DiceCollection of dies.
     * @param fromEyeNumber First eye number of the sequence, inclusive.
     * @param toEyeNumber Last eye number of the sequence, inclusive.
     * @param pointsToGive Points to give if sequence is found.
     * @return If sequence is found return pointsToGive, else return 0.
     */
    public int checkForSequentialNumbers(DiceCollection dc, int fromEyeNumber, int toEyeNumber, int pointsToGive) {
        if (dc == null || fromEyeNumber >= toEyeNumber || pointsToGive <= 0) {
            throw new IllegalArgumentException();
        }

        // TODO Make betterments.
        int[] dies = dc.getDies();
        Arrays.sort(dies);
        int found = fromEyeNumber;
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] == found) {
                found++;
            }
        }
        // If sequence is found, return pointsToGive.
        if (found == toEyeNumber + 1) {
            return pointsToGive;
        } else {
            return 0;
        }
    }

    /**
     * Calculate sum of eyes also known as chance.
     *
     * @param dc DiceCollection of dies.
     * @return Points for the sum of dies.
     */
    public int getSumOfDies(DiceCollection dc) {
        int[] dies = dc.getDies();
        int score = 0;
        for (int i = 0; i < dies.length; i++) {
            score += dies[i];
        }

        return score;
    }

    /**
     * Initialize scorecard's LinkedHashMap with scores of -1 for combinations
     * and 0 for total. -1 is set to differentiate between zero score and score
     * not set. Can not be overriden by subclasses.
     */
    final protected void initializeScoretable() {
        getCombinations().forEach((combination) -> {
            this.scoretable.put(combination, -1);
        });
        this.combinations.add("Total");
        this.scoretable.put("Total", 0);
    }

    /**
     * Calculates total of points for the scorecard.
     */
    private void calculateTotal() {
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
     * @param dc DiceCollection of dies.
     * @param pointsToGive Points to give if Yatzy found.
     * @return pointsToGive if all are the same, zero otherwise.
     */
    public Integer checkForAllTheSame(DiceCollection dc, int pointsToGive) {
        int[] dies = dc.getDies();
        int eye = dies[0];
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] != eye) {
                return 0;
            }
        }
        return pointsToGive;
    }
    
    /**
     * Method to check if the DiceCollection is of the right kind for this 
     * scorecard. ENTÄ JOS HALUAA VAIN PELATA 100 NOPALLA NORMI YATZYA??!?!?!?
     * @param dc
     * @return 
     */
    private boolean isValid(DiceCollection dc){
        return false;
    }

    public ArrayList<String> getCombinations() {
        return this.combinations;
    }

    public String getType() {
        return this.type;
    }

    int getPointsFor(String combination) {
        return this.scoretable.get(combination);
    }

}
