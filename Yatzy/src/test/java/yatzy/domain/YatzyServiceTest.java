package yatzy.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Riku_L
 */
public class YatzyServiceTest {

    public YatzyServiceTest() {
    }

    private YatzyService s;
    private YatzyService s2;

    @Before
    public void setUp() throws ClassNotFoundException {
        s = new YatzyService();
        s2 = new YatzyService(20, 9, 3);
    }

    @Test
    public void playerListIsInitialized() {
        assertTrue(s.getPlayers() != null);
    }

    @Test
    public void diceCollectionIsInitialized() {
        assertTrue(s.getDies() != null);
    }

    @Test
    public void zeroThrowsInBeginning() {
        assertEquals(0, s.getThrowsUsed());
    }

    @Test
    public void canAddPlayers() {
        s.addPlayer("name");
        assertFalse(s.getPlayers().isEmpty());
    }

    @Test
    public void emptyPlayerListDoesntHavePlayerInTurn() {
        assertEquals(null, s.getPlayerWithTurn());
    }

    @Test
    public void addedPlayerHasTurn() {
        s.addPlayer("name");
        assertEquals("name", s.getPlayerWithTurn().getName());
    }

    @Test
    public void onlyFirstAddedPlayerHasTurn() {
        s.addPlayer("1");
        s.addPlayer("2");
        assertTrue(s.getPlayers().get(0).getTurn());
        assertFalse(s.getPlayers().get(1).getTurn());
        assertEquals("1", s.getPlayerWithTurn().getName());
    }
}
