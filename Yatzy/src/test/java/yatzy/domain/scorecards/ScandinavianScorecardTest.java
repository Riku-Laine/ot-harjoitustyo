package yatzy.domain.scorecards;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.domain.DiceCollection;

/**
 *
 * @author Riku_L
 */
public class ScandinavianScorecardTest {

    public ScandinavianScorecardTest() {
    }

    private ScandinavianScorecard sc;
    private DiceCollection dc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sc = new ScandinavianScorecard();
        dc = new DiceCollection();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findsPair() {
        int[] dies = {1, 2, 3, 3, 4};
        dc.setDies(dies);
        assertEquals(6, sc.checkForMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void findsBiggestPair() {
        int[] dies = {1, 3, 3, 4, 4};
        dc.setDies(dies);
        assertEquals(8, sc.checkForMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void returnsZeroWhenNoPairs() {
        int[] dies = {1, 2, 3, 4, 6};
        dc.setDies(dies);
        assertEquals(0, sc.checkForMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void findsPairWhenTriplet() {
        int[] dies = {3, 4, 4, 6, 4};
        dc.setDies(dies);
        assertEquals(8, sc.checkForMultiplesOfSizeN(dc, 1, 2));
    }

    @Test
    public void findsTwoPairs() {
        int[] dies = {3, 4, 3, 1, 4};
        dc.setDies(dies);
        assertEquals(14, sc.checkForMultiplesOfSizeN(dc, 2, 2));
    }

    @Test
    public void sumIsRight() {
        int[] dies = {1, 4, 3, 3, 4};
        dc.setDies(dies);
        assertEquals(15, sc.getSumOfDies(dc));
    }

    @Test
    public void findsSmallStraight() {
        int[] dies = {3, 4, 2, 1, 5};
        dc.setDies(dies);
        assertEquals(15, sc.checkForSequentialNumbers(dc, 1, 5, 15));
    }

    @Test
    public void findsBigStraight() {
        int[] dies = {3, 4, 2, 6, 5};
        dc.setDies(dies);
        assertEquals(20, sc.checkForSequentialNumbers(dc, 2, 6, 20));
    }

    @Test
    public void returnsZeroWhenNoStraight() {
        int[] dies = {3, 4, 2, 2, 5};
        dc.setDies(dies);
        assertEquals(0, sc.checkForSequentialNumbers(dc, 1, 5, 15));
    }

    @Test
    public void returnsZeroWhenNoBigStraight() {
        int[] dies = {3, 4, 1, 2, 5};
        dc.setDies(dies);
        assertEquals(0, sc.checkForSequentialNumbers(dc, 2, 6, 15));
    }

    @Test
    public void findsFullHouse() {
        int[] dies = {3, 3, 4, 4, 3};
        dc.setDies(dies);
        assertEquals(3 * 3 + 2 * 4, sc.checkForFullHouse(dc));
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
    public void sc() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        sc.setPointsForCombination("Ones", dc);
        int score = sc.getPlayersScoretable().get("Ones");
        assertEquals(5, score);
    }

    @Test
    public void totalIsRight() {
        int[] dies = {1, 1, 1, 1, 1};
        dc.setDies(dies);
        sc.setPointsForCombination("Ones", dc);
        int total = sc.getPlayersScoretable().get("Total");
        assertEquals(5, total);

        sc.setPointsForCombination("Yatzy", dc);
        int total2 = sc.getPlayersScoretable().get("Total");
        assertEquals(5 + 50, total2);
    }
}
