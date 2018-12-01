package yatzy.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void nameSetRight() {
        assertEquals("test", p.getName());
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
        DiceCollection dc = new DiceCollection();
        dc.setDies(dies);
        p.setPoints("Yatzy", dc);
        int score = p.getScorecard().get("Yatzy");
        assertEquals(50, score);
    }

}
