package yatzy.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Riku_L
 */
public class YatzyServiceTest {

    public YatzyServiceTest() {
    }

    private YatzyService s;
    private YatzyService s2;
    private File dbFile;

    @Rule
    public TemporaryFolder dbFolder = new TemporaryFolder();

    @Before
    public void setUp() throws ClassNotFoundException, IOException {
        dbFile = dbFolder.newFile("recordTestDatabase.db");

        s = new YatzyService("jdbc:sqlite:" + dbFile.getAbsolutePath());
        s2 = new YatzyService(20, 9, 3, "jdbc:sqlite:" + dbFile.getAbsolutePath());
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
        s.addPlayer("name", "Scandinavian");
        assertFalse(s.getPlayers().isEmpty());
    }

    @Test
    public void emptyPlayerListDoesntHavePlayerInTurn() {
        assertEquals(null, s.getPlayerWithTurn());
    }

    @Test
    public void addedPlayerHasTurn() {
        s.addPlayer("name", "Scandinavian");
        assertEquals("name", s.getPlayerWithTurn().getName());
    }

    @Test
    public void onlyFirstAddedPlayerHasTurn() {
        s.addPlayer("1", "Scandinavian");
        s.addPlayer("2", "Scandinavian");
        assertTrue(s.getPlayers().get(0).getTurn());
        assertFalse(s.getPlayers().get(1).getTurn());
        assertEquals("1", s.getPlayerWithTurn().getName());
    }

    @Test
    public void canChangeTurn() {
        s.addPlayer("1", "Scandinavian");
        s.addPlayer("2", "Scandinavian");

        assertEquals("1", s.getPlayerWithTurn().getName());
        s.throwAllDies();

        s.setScoreAndChangeTurn(s.getPlayerWithTurn(), "Chance");

        assertEquals("2", s.getPlayerWithTurn().getName());
        assertEquals(0, s.getThrowsUsed());
        Assert.assertArrayEquals(new int[5], s.getDies());
    }

    @Test
    public void throwingIncreasesNumberOfThrows() {
        s.throwAllDies();
        assertEquals(1, s.getThrowsUsed());
    }

    @Test
    public void canResetGame() {
        s.addPlayer("1", "Scandinavian");
        s.throwAllDies();
        s.reset();
        assertTrue(s.getPlayers().isEmpty());
        assertEquals(null, s.getPlayerWithTurn());
        assertEquals(0, s.getThrowsUsed());
    }

    @Test
    public void throwSelectedThrowsSelected() {
        boolean[] toThrow = {true, false, true, false, true};

        // Dies are initiated at zeroes.
        Assert.assertArrayEquals(new int[5], s.getDies());

        // Throwing should remove the zeroes.
        s.throwSelectedDies(toThrow);
        int[] dies = s.getDies();
        for (int i = 0; i < dies.length; i++) {
            if (toThrow[i]) {
                assertNotEquals(0, dies[i]);
            } else {
                assertEquals(0, dies[i]);
            }
        }
    }

    @Test
    public void throwAllThrowsAll() {
        // Dies are initiated at zeroes.
        s.throwAllDies();
        int[] dies = s.getDies();
        for (int i = 0; i < dies.length; i++) {
            if (dies[i] == 0) {
                fail();
            }
        }
        s2.throwAllDies();
        int[] dies_long = s.getDies();
        for (int i = 0; i < dies_long.length; i++) {
            if (dies_long[i] == 0) {
                fail();
            }
        }
    }

    @Test
    public void canSetScore() {
        s.addPlayer("1", "Scandinavian");
        s.throwAllDies();
        s.setScoreAndChangeTurn(s.getPlayerWithTurn(), "Chance");
        assertNotEquals(0, s.getScore(s.getPlayerWithTurn(), "Chance"));
    }

    @Test
    public void cantSetScoreForGameWithoutPlayers() {
        s.throwAllDies();
        try {
            s.setScoreAndChangeTurn(s.getPlayerWithTurn(), "Chance");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void canAddAmericanCard() {
        s.addPlayer("1", "American");
        assertEquals("American", s.getPlayerWithTurn().getScorecard().getType());
    }

    @Test
    public void canSetNthToBegin() {
        for (int i = 0; i < 10; i++) {
            s.addPlayer(i + "", "Scandinavian");
        }

        s.setToBegin(5);
        assertEquals("5", s.getPlayerWithTurn().getName());
    }

    @Test
    public void canSetAmericanPoints() {
        int[] dies = {1, 1, 1, 1, 1};
        s.addPlayer("1", "American");
        s.setDies(dies);
        s.setScoreAndChangeTurn(s.getPlayerWithTurn(), "Aces");
        assertEquals(5, s.getPlayerWithTurn().getScorecard().getPointsFor("Aces"));
    }

    @Test
    public void americanPointsInScandinavianCardThrowsError() {
        int[] dies = {1, 1, 1, 1, 1};
        s.addPlayer("1", "Scandinavian");
        s.setDies(dies);
        try {
            s.setScoreAndChangeTurn(s.getPlayerWithTurn(), "Aces");
            fail();
        } catch (Exception e) {
        }
    }

}
