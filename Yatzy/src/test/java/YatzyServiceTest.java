/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.domain.YatzyService;

/**
 *
 * @author Riku_L
 */
public class YatzyServiceTest {

    public YatzyServiceTest() {
    }

    private YatzyService s;

    @Before
    public void setUp() {
        s = new YatzyService();
    }

    @Test
    public void initialPlayerListEmpty() {
        assertTrue(s.getPlayers().isEmpty());
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
        assertTrue(s.getPlayers().get(0).getTurn());
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
