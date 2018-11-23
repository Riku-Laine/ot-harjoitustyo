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
import yatzy.domain.Scorecard;

/**
 *
 * @author Riku_L
 */
public class ScorecardTest {

    public ScorecardTest() {
    }

    private Scorecard sc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        Player nullPlayer = new Player();
//        ArrayList players = new ArrayList();
//        players.add(nullPlayer);
        sc = new Scorecard();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void errorIfInvalidCommand() {
        try {
            sc.setPointsForCombination("non_existing", new int[1]);
            fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void findsPair() {
        int[] dies = {1, 2, 3, 3, 4};
        assertEquals(6, sc.checkForMultiplesOfSizeN(dies, 1, 2));
    }

    @Test
    public void findsBiggestPair() {
        int[] dies = {1, 3, 3, 4, 4};
        assertEquals(8, sc.checkForMultiplesOfSizeN(dies, 1, 2));
    }

    @Test
    public void returnsZeroWhenNoPairs() {
        int[] dies = {1, 2, 3, 4, 6};
        assertEquals(0, sc.checkForMultiplesOfSizeN(dies, 1, 2));
    }

    @Test
    public void findsPairWhenTriplet() {
        int[] dies = {3, 4, 4, 6, 4};
        assertEquals(8, sc.checkForMultiplesOfSizeN(dies, 1, 2));
    }

    @Test
    public void findsTwoPairs() {
        int[] dies = {3, 4, 3, 1, 4};
        assertEquals(14, sc.checkForMultiplesOfSizeN(dies, 2, 2));
    }

    @Test
    public void chanceIsRight() {
        int[] dies = {1, 4, 3, 3, 4};
        assertEquals(15, sc.checkForChance(dies));
    }

    @Test
    public void findsSmallStraight() {
        int[] dies = {3, 4, 2, 1, 5};
        assertEquals(15, sc.checkForStraight(dies, "small"));
    }

    @Test
    public void findsBigStraight() {
        int[] dies = {3, 4, 2, 6, 5};
        assertEquals(20, sc.checkForStraight(dies, "big"));
    }

    @Test
    public void returnsZeroWhenNoSmallStraight() {
        int[] dies = {3, 4, 2, 2, 5};
        assertEquals(0, sc.checkForStraight(dies, "small"));
    }

    @Test
    public void returnsZeroWhenNoBigStraight() {
        int[] dies = {3, 4, 2, 2, 5};
        assertEquals(0, sc.checkForStraight(dies, "big"));
    }

    @Test
    public void findsFullHouse() {
        int[] dies = {3, 3, 4, 4, 3};
        assertEquals(3 * 3 + 2 * 4, sc.checkForFullHouse(dies));
    }

    @Test
    public void returnsZeroWhenNoFullHouse() {
        int[] dies = {3, 3, 3, 4, 3};
        assertEquals(0, sc.checkForFullHouse(dies));
    }

    @Test
    public void returnsZeroWhenNoOnes() {
        int[] dies = {3, 3, 3, 4, 3};
        assertEquals(0, sc.checkForPointValues(dies, 1));
    }

    @Test
    public void findsOneOnes() {
        int[] dies = {1, 3, 3, 4, 3};
        assertEquals(1, sc.checkForPointValues(dies, 1));
    }

    @Test
    public void findsFiveOnes() {
        int[] dies = {1, 1, 1, 1, 1};
        assertEquals(5, sc.checkForPointValues(dies, 1));
    }
    
    @Test
    public void sc() {
        int[] dies = {1, 1, 1, 1, 1};
        sc.setPointsForCombination("Ones", dies);
        int score = sc.getPlayersScoretable().get("Ones");
        assertEquals(5, score);
    }
    
}
