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
import yatzy.domain.Player;
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
        Player nullPlayer = new Player();
//        ArrayList players = new ArrayList();
//        players.add(nullPlayer);
        sc = new Scorecard(nullPlayer);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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
}
