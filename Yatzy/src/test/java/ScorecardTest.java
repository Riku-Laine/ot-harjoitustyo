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
        sc = new Scorecard();
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
        assertEquals(6, sc.getScore("one pair", dies));
    }
}
