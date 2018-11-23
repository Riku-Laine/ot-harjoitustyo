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

/**
 *
 * @author Riku_L
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    private Player p;
    
    @Before
    public void setUp() {
        p = new Player("test");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initialTurnIsFalse() {
        assertFalse(p.getTurn());
    }
    
    @Test
    public void canChangeTurn() {
        p.setTurn(true);
        assertTrue(p.getTurn());
    }
    
    @Test
    public void scorecardCreatedInStartup() {
        assertFalse(p.getScorecard().isEmpty());
    }
    
    @Test
    public void canSetPoints() {
        int[] dies = {6, 6, 6, 6, 6};
        p.setPoints("Yatzy", dies);
        int score = p.getScorecard().get("Yatzy");
        assertEquals(50, score);
    }
    
}
