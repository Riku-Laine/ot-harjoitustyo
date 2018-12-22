/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain.scorecards;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import yatzy.domain.DiceCollection;

/**
 *
 * @author Riku_L
 */
public class AmericanScorecardTest {

    public AmericanScorecardTest() {
    }

    private AmericanScorecard sc;
    private DiceCollection dc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sc = new AmericanScorecard();
        dc = new DiceCollection();
    }

    @Test
    public void returnsZeroWhenNoPairs() {
        int[] dies = {1, 2, 3, 4, 6};
        dc.setDies(dies);
        assertEquals(0, sc.checkForKMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void findsPairWhenTriplet() {
        int[] dies = {3, 4, 4, 6, 4};
        dc.setDies(dies);
        assertEquals(8, sc.checkForKMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void findsTwoPairs() {
        int[] dies = {3, 4, 3, 1, 4};
        dc.setDies(dies);
        assertEquals(14, sc.checkForKMultiplesOfSizeN(dc, 2, 2));
    }

    @Test
    public void sumIsRight() {
        int[] dies = {1, 4, 3, 3, 4};
        dc.setDies(dies);
        assertEquals(15, sc.getSumOfDies(dc));
    }

    @Test
    public void findsShortStraight() {
        int[] dies = {3, 4, 2, 2, 5};
        dc.setDies(dies);
        sc.setPointsForCombination("Short straight", dc);
        assertEquals(30, (int) sc.getPlayersScoretable().get("Short straight"));
    }

    @Test
    public void findsLargeStraight() {
        int[] dies = {3, 4, 2, 6, 5};
        dc.setDies(dies);
        sc.setPointsForCombination("Long straight", dc);
        assertEquals(40, (int) sc.getPlayersScoretable().get("Long straight"));
    }

    @Test
    public void returnsZeroWhenNoStraight() {
        int[] dies = {3, 4, 2, 2, 5};
        dc.setDies(dies);
        assertEquals(0, sc.checkForSequentialNumbers(dc, 1, 5, 15));
    }

    @Test
    public void returnsZeroWhenNoLargeStraight() {
        int[] dies = {3, 4, 1, 2, 5};
        dc.setDies(dies);
        assertEquals(0, sc.checkForSequentialNumbers(dc, 2, 6, 15));
    }

    @Test
    public void findsAmericanFullHouse() {
        int[] dies = {3, 3, 4, 4, 3};
        dc.setDies(dies);
        assertEquals(25, sc.checkForFullHouse(dc));
    }

    @Test
    public void returnsZeroWhenNoFullHouse() {
        int[] dies = {3, 3, 3, 4, 3};
        dc.setDies(dies);
        assertEquals(0, sc.checkForFullHouse(dc));

        int[] dies2 = {3, 3, 4, 3, 6};
        dc.setDies(dies2);
        assertEquals(0, sc.checkForFullHouse(dc));
    }

    @Test
    public void returnsZeroWhenNoOnes() {
        int[] dies = {3, 3, 3, 4, 3};
        dc.setDies(dies);
        assertEquals(0, sc.checkForPointValues(dc, 1));
    }

    @Test
    public void findsOneOnes() {
        int[] dies = {1, 3, 3, 4, 3};
        dc.setDies(dies);
        assertEquals(1, sc.checkForPointValues(dc, 1));
    }

    @Test
    public void findsFiveOnes() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        assertEquals(5, sc.checkForPointValues(dc, 1));
    }

    // Subclass specific tests.
    @Test
    public void canUpdateScorecard() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        sc.setPointsForCombination("Aces", dc);
        int score = sc.getPlayersScoretable().get("Aces");
        assertEquals(5, score);
    }

    @Test
    public void totalIsRight() {
        int[] dies = {1, 2, 3, 4, 5};
        dc.setDies(dies);
        sc.setPointsForCombination("Twos", dc);
        int total = sc.getPlayersScoretable().get("Total");
        assertEquals(2, total);

        sc.setPointsForCombination("Chance", dc);
        int total2 = sc.getPlayersScoretable().get("Total");
        assertEquals(2 + 15, total2);
    }

    @Test
    public void cantPutSameCombinationTwice() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        sc.setPointsForCombination("Aces", dc);
        try {
            sc.setPointsForCombination("Aces", dc);
            fail();
        } catch (Error e) {

        }
    }

    @Test
    public void falseCombinationThrowsException() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        try {
            sc.setPointsForCombination("Fake", dc);
            fail();
        } catch (Exception e) {

        }
    }
}
